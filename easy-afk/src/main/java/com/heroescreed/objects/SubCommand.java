package com.heroescreed.objects;

import com.heroescreed.Plugin;

import lombok.RequiredArgsConstructor;

import org.bukkit.entity.Player;

@RequiredArgsConstructor
public abstract class SubCommand{
    protected final Plugin plugin;

    public abstract boolean onCommand(Player player, String[] args);

    public abstract String getPermission();

    public abstract String getUsage();

    public abstract String getSubCommand();

    public abstract String getDescription();
}
