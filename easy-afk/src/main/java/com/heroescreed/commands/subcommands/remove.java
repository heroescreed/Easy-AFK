package com.heroescreed.commands.subcommands;

import com.heroescreed.Plugin;
import com.heroescreed.objects.SubCommand;
import org.bukkit.entity.Player;

public class remove extends SubCommand {
    public remove(Plugin plugin){
        super(plugin);
    }

    @Override
    public boolean onCommand(Player player, String[] args){
        plugin.getAfkManager().onPlayerInteraction(player.getUniqueId());
        player.sendMessage(plugin.getConfigManager().getMessage("afkremoved"));

        return true;
    }

    @Override
    public String getPermission(){
        return "easyafk.afk";
    }

    @Override
    public String getUsage(){
        return "/afk remove";
    }

    @Override
    public String getSubCommand(){
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Removes your afk";
    }
}
