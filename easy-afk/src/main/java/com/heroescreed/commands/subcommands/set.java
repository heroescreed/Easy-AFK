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
        String reason = "AFK";
        if (args.length >= 2) {
            reason = "";
            for (int i = 1; i < args.length; i++) {
                reason = reason + args[i] + " ";
            }
            reason.trim();
        }

        plugin.getAfkManager().setPlayerAFK(player.getUniqueId(), reason);

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