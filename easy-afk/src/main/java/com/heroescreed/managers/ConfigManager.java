package com.heroescreed.managers;

import com.heroescreed.Plugin;

import org.bukkit.ChatColor;

public class ConfigManager {
    private final Plugin plugin;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public String getMessage(String messagepath){
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages." + messagepath)); // Get the message content from the constants file and add the correct colours
    }
}
