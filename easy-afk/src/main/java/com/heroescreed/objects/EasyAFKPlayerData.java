package com.heroescreed.objects;

import com.heroescreed.Plugin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@RequiredArgsConstructor
public class EasyAFKPlayerData {

    private final Plugin plugin;
    private final UUID uuid;
    private long lastinteraction = System.currentTimeMillis();
    @Getter
    private boolean afk = false;
    @Getter
    private String reason;

    public void onInteraction(){
        if(afk){
            afk = false;
            reason = null;
            Player player = Bukkit.getPlayer(uuid);
            if(player == null){
                plugin.getAfkManager().removePlayerData(uuid);
                return;
            }

            player.sendMessage(plugin.getConfigManager().getMessage("afkremoved"));
        }

        lastinteraction = System.currentTimeMillis();
    }

    public void setPlayerAfk(String reason){
        if(afk) return;

        this.reason = reason;
        afk = true;
        Player player = Bukkit.getPlayer(uuid);
        if(player == null){
            plugin.getAfkManager().removePlayerData(uuid);
            return;
        }

        if(plugin.getConfigManager().getSetting("vanishwhenafk")) player.setVisibleByDefault(false);
        if(!plugin.getConfigManager().getSetting("pickupitemswhileafk")) player.setCanPickupItems(false);
        if(plugin.getConfigManager().getSetting("immunetopvpwhileafk") && plugin.getConfigManager().getSetting("immunetopvewhileafk")) player.setInvulnerable(true);

        if(player.getScoreboard().getEntryTeam(player.getName()) != null) player.setPlayerListName(player.getScoreboard().getEntryTeam(player.getName()).getColor() + player.getName() + " " + plugin.getConfigManager().getMessage("playerlisttag"));
        else player.setPlayerListName(player.getName() + " " + plugin.getConfigManager().getMessage("playerlisttag"));

        if(plugin.getConfigManager().getSetting("broadcastafk")){
            for(Player play: plugin.getServer().getOnlinePlayers()){
                if(play != player) player.sendMessage(plugin.getConfigManager().getMessage("playergoneafk").replace("%playername%", player.getName()).replace("%reason%", reason));
            }
        }
    }

    public Runnable getafkCheck() {
        return () -> {
            if(afk) return;
            if(Bukkit.getOfflinePlayer(uuid).isOnline()){
                long afklength = (System.currentTimeMillis() - lastinteraction) / 1000;
                if(afklength >= plugin.getConfigManager().getOption("afklength") && plugin.getConfigManager().getSetting("autoafk")){
                    afk = true;
                    setPlayerAfk("AFK");

                    Bukkit.getPlayer(uuid).sendMessage(plugin.getConfigManager().getMessage("afkautoset"));
                }
            } else {
                plugin.getAfkManager().removePlayerData(uuid);
            }
        };
    }
}
