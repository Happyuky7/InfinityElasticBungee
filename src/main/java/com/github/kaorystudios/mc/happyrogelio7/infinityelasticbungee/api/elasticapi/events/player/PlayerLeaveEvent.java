package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events.player;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events.ElasticPlayerEvent;

public class PlayerLeaveEvent extends ElasticPlayerEvent {
    public PlayerLeaveEvent(final String proxyID, final String username) {
        super(proxyID, username);
    }
}
