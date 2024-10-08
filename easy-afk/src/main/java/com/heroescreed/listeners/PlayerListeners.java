package com.heroescreed.listeners;

import com.heroescreed.Plugin;

import lombok.RequiredArgsConstructor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PlayerListeners implements Listener {
    private final Plugin plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        plugin.getAfkManager().createPlayerData(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        plugin.getAfkManager().removePlayerData(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(plugin.getConfigManager().getSetting("removeafkonchat")) plugin.getAfkManager().onPlayerInteraction(event.getPlayer().getUniqueId());

        List<String> messages = new ArrayList<>();

        plugin.getAfkManager().getPlayerdatamap().forEach((key, value) -> {
            if(event.getMessage().contains(Bukkit.getOfflinePlayer(key).getName())) {
                if(value.isAfk()) messages.add(plugin.getConfigManager().getMessage("afklist").replace("%playername%", Bukkit.getOfflinePlayer(key).getName()).replace("%reason%", value.getReason()));
            }
        });

        if(!messages.isEmpty()) event.getPlayer().sendMessage(String.join("\n", messages));
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if(plugin.getConfigManager().getSetting("removeafkonmove")) plugin.getAfkManager().onPlayerInteraction(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player) {
            if(plugin.getConfigManager().getSetting("removeafkonattack")) plugin.getAfkManager().onPlayerInteraction(player.getUniqueId());
            if(plugin.getConfigManager().getSetting("immunetopvpwhileafk") && plugin.getAfkManager().isPlayerAfk(event.getEntity().getUniqueId())) event.setCancelled(true);
        }
        if(!(event.getDamager() instanceof Player player) && plugin.getConfigManager().getSetting("immunetopvewhileafk") && plugin.getAfkManager().isPlayerAfk(event.getEntity().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(plugin.getConfigManager().getSetting("removeafkoncommand")) plugin.getAfkManager().onPlayerInteraction(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(plugin.getConfigManager().getSetting("removeafkoninteract")) plugin.getAfkManager().onPlayerInteraction(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent event) {
        if (plugin.getConfigManager().getSetting("removeafkonblockplace")) plugin.getAfkManager().onPlayerInteraction(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent event) {
        if (plugin.getConfigManager().getSetting("removeafkonblockbreak")) plugin.getAfkManager().onPlayerInteraction(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent event){
        if(event.getEntity() instanceof Player && !plugin.getConfigManager().getSetting("hungerwhileafk") && plugin.getAfkManager().isPlayerAfk(event.getEntity().getUniqueId())) event.setCancelled(true);
    }

}
