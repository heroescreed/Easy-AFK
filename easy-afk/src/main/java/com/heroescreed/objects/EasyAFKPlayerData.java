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
    @Getter
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

        player.sendMessage(plugin.getConfigManager().getMessage("afkset").replace("%reason%", reason));
    }

    public Runnable getafkCheck() {
        return () -> {
            if(afk) return;
            if(Bukkit.getOfflinePlayer(uuid).isOnline()){
                long afklength = (System.currentTimeMillis() - lastinteraction) / 1000;
                // 22/08/24 Adding config option has broken auto afk
                if(afklength >= plugin.getConfigManager().getOption("afklength") && plugin.getConfigManager().getSetting("autoafk")){
                    afk = true;
                    reason = "AFK";
                    Bukkit.getPlayer(uuid).sendMessage(plugin.getConfigManager().getMessage("afkautoset"));
                }
            } else {
                plugin.getAfkManager().removePlayerData(uuid);
            }
        };
    }
}
