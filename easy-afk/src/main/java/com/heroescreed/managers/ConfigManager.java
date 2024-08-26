package com.heroescreed.managers;

import com.heroescreed.Plugin;

import lombok.RequiredArgsConstructor;

import org.bukkit.ChatColor;

@RequiredArgsConstructor
public class ConfigManager {
    private final Plugin plugin;

    public String getMessage(String messagepath){
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages." + messagepath)); // Get the message content from the constants file and add the correct colours
    }

    public boolean getSetting(String settingpath){
        return plugin.getConfig().getBoolean("settings." + settingpath);
    }

    public int getOption(String optionpath){
        return plugin.getConfig().getInt("options." + optionpath);
    }
}
