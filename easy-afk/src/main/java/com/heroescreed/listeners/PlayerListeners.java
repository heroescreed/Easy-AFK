package com.heroescreed.listeners;

import com.heroescreed.Plugin;

import lombok.RequiredArgsConstructor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;

@RequiredArgsConstructor
public class PlayerListeners implements Listener {
    private final Plugin plugin;

    // 22/08/24 - Added config options has stopped afk removal

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
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        // Add distance check - 21/08/24
        if(plugin.getConfigManager().getSetting("removeafkonmove")) {
            plugin.getAfkManager().onPlayerInteraction(event.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        // Make afk players immune to damage from other players - 22/08/24
        if(event.getDamager() instanceof Player player && plugin.getConfigManager().getSetting("removeafkonattack")) {
            plugin.getAfkManager().onPlayerInteraction(player.getUniqueId());
        }
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

}
