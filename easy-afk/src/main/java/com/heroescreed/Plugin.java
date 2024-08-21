package com.heroescreed;

import com.heroescreed.commands.afk;
import com.heroescreed.managers.AfkManager;
import com.heroescreed.managers.ConfigManager;

import com.heroescreed.managers.SubCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

public class Plugin extends JavaPlugin{

    @Getter
    private static Plugin instance;
    @Getter
    private static ConfigManager configManager;
    @Getter
    private static AfkManager afkManager;
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

        getCommand("afk").setExecutor(new afk(this));

        getLogger().info("Easy AFK has been enabled!");
    }

    @Override
    public void onDisable(){
        getLogger().info("Easy AFK has been disabled!");
    }
}