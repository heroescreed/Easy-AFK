package com.heroescreed.commands.subcommands;

import com.heroescreed.Plugin;
import com.heroescreed.objects.SubCommand;

import org.bukkit.entity.Player;

public class set extends SubCommand {
    public set(Plugin plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(Player player, String[] args){

        return true;
    }

    @Override
    public String getPermission(){
        return "easyafk.afk.set";
    }

    @Override
    public String getUsage(){
        return "/afk set [message]";
    }

    @Override
    public String getSubCommand(){
        return "set";
    }

    @Override
    public String getDescription() {
        return "Marks you as afk, with an optional message";
    }
}