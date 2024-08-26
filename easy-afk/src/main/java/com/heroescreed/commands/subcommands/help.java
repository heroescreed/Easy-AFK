package com.heroescreed.commands.subcommands;

import com.heroescreed.Plugin;
import com.heroescreed.objects.SubCommand;
import org.bukkit.entity.Player;

public class help extends SubCommand {
    public help(Plugin plugin){
        super(plugin);
    }

    @Override
    public boolean onCommand(Player player, String[] args){
        player.sendMessage(String.join("\n", plugin.getSubCommandManager().getHelpmessages()));
        return true;
    }

    @Override
    public String getPermission(){
        return "easyafk.afk";
    }

    @Override
    public String getUsage(){
        return "/afk help";
    }

    @Override
    public String getSubCommand(){
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows this message";
    }
}
