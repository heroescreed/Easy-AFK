package com.heroescreed.commands;

import com.heroescreed.Plugin;

import com.heroescreed.objects.SubCommand;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class afk implements CommandExecutor {

    private final Plugin plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getConfigManager().getMessage("notPlayer"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0){
            SubCommand cmd = plugin.getSubCommandManager().getCommands().get("help");

            if (!player.hasPermission(cmd.getPermission())) {
                player.sendMessage(plugin.getConfigManager().getMessage("noPermission"));
                return true;
            }

            if (!cmd.onCommand(player, args)) player.sendMessage(plugin.getConfigManager().getMessage("generalError"));

            return true;
        }

        if (!plugin.getSubCommandManager().getCommands().containsKey(args[0].toLowerCase())) {
            player.sendMessage(plugin.getConfigManager().getMessage("wrongUsage").replace("%usage%", command.getUsage()));
            return true;
        }

        SubCommand cmd = plugin.getSubCommandManager().getCommands().get(args[0].toLowerCase());

        if (!player.hasPermission(cmd.getPermission())) {
            player.sendMessage(plugin.getConfigManager().getMessage("noPermission"));
            return true;
        }

        if (!cmd.onCommand(player, args)) player.sendMessage(plugin.getConfigManager().getMessage("wrongUsage").replace("%usage%", cmd.getUsage()));

        return true;
    }
}