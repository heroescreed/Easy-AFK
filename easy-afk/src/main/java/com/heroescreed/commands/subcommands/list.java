package com.heroescreed.commands.subcommands;

import com.heroescreed.Plugin;
import com.heroescreed.objects.SubCommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class list extends SubCommand {
    public list(Plugin plugin){
        super(plugin);
    }

    @Override
    public boolean onCommand(Player player, String[] args){
        List<String> messages = new ArrayList<>();

        plugin.getAfkManager().getPlayerdatamap().forEach((key, value) -> {
            if(value.isAfk()) messages.add(plugin.getConfigManager().getMessage("afklist").replace("%playername%", Bukkit.getOfflinePlayer(key).getName()).replace("%reason%", value.getReason()));
        });

        if(messages.isEmpty()) player.sendMessage(plugin.getConfigManager().getMessage("noafkplayers"));
        else player.sendMessage(String.join("\n", messages));

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
