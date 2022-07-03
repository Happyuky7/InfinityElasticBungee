package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events.player;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events.ElasticPlayerEvent;

public class PlayerJoinedEvent extends ElasticPlayerEvent {
    public PlayerJoinedEvent(final String proxyID, final String username) {
        super(proxyID, username);
    }
}
