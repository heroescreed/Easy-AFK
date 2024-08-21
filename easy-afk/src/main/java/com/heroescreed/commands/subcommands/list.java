package com.heroescreed.commands.subcommands;

import com.heroescreed.Plugin;
import com.heroescreed.objects.SubCommand;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class list extends SubCommand {
    public list(Plugin plugin){
        super(plugin);
    }

    @Override
    public boolean onCommand(Player player, String[] args){

        return true;
    }

    @Override
    public String getPermission(){
        return "easyafk.afk.list";
    }

    @Override
    public String getUsage(){
        return "/afk list";
    }

    @Override
    public String getSubCommand(){
        return "list";
    }

    @Override
    public String getDescription() {
        return "List all afk players and their messages";
    }
}
