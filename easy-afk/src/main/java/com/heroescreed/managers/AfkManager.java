package com.heroescreed.managers;

import com.heroescreed.Plugin;

import com.heroescreed.objects.EasyAFKPlayerData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map;


@RequiredArgsConstructor
public class AfkManager {
    private final Plugin plugin;
    @Getter
    private final Map<UUID, EasyAFKPlayerData> playerdatamap = new HashMap<>();

    public void createPlayerData(UUID uuid){
        playerdatamap.put(uuid, new EasyAFKPlayerData(plugin, uuid));
    }

    public void removePlayerData(UUID uuid){
        playerdatamap.remove(uuid);
    }

    public void setPlayerAFK(UUID uuid, String reason){
        playerdatamap.get(uuid).setPlayerAfk(reason);
    }

    public void onPlayerInteraction(UUID uuid){
        playerdatamap.get(uuid).onInteraction();
    }

    public EasyAFKPlayerData getPlayerData(UUID uuid){
        return playerdatamap.get(uuid);
    }
}
