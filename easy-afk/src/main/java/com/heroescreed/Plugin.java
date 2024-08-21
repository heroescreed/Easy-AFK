package com.heroescreed;

import com.heroescreed.managers.ConfigManager;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

public class Plugin extends JavaPlugin{

    @Getter
    private static Plugin instance;
    @Getter
    private static ConfigManager configManager;
    @Getter
    private static SubCommandManager subCommandManager;

    public Plugin(){
        instance = this;
    }

    @Override
    public void onEnable(){
        saveDefaultConfig();

        configManager = new ConfigManager(this);
        subCommandManager = new SubCommandManager(this);

        getLogger().info("Easy AFK has been enabled!");
    }

    @Override
    public void onDisable(){
        getLogger().info("Easy AFK has been disabled!");
    }
}