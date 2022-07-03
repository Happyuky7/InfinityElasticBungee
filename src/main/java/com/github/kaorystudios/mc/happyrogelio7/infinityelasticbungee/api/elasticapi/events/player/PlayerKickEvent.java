package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events.player;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events.ElasticPlayerEvent;

public class PlayerKickEvent extends ElasticPlayerEvent {

    private final String reason;

    public PlayerKickEvent(final String proxyID, final String username, final String reason) {
        super(proxyID, username);
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }
}
