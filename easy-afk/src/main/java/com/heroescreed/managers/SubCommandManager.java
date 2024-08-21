package com.heroescreed.managers;

import com.heroescreed.Plugin;
import com.heroescreed.objects.SubCommand;

import org.reflections.Reflections;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SubCommandManager {
    private final Plugin plugin;
    @Getter
    private final Map<String, SubCommand> commands = new HashMap<>();
    @Getter
    private final List<String> helpmessages = new ArrayList<>();

    public SubCommandManager(Plugin plugin){
        this.plugin = plugin;
        registerSubCommands();
    }

    public void registerSubCommands(){
        for(Class<?> clazz : new Reflections("com.heroescreed.commands.subcommands").getSubTypesOf(SubCommand.class)){
            try{
                SubCommand subCommand = (SubCommand) clazz.getDeclaredConstructor(Plugin.class).newInstance(plugin);
                String name = subCommand.getSubCommand().toLowerCase();
                registerSubCommand(name, subCommand);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        generateHelpMessage();
    }

    private void registerSubCommand(String cmd, SubCommand subCommand) {
        commands.put(cmd, subCommand);
    }

    private void generateHelpMessage(){
        commands.forEach((key, value) -> helpmessages.add(value.getUsage() + " - " + value.getDescription()));
    }
}