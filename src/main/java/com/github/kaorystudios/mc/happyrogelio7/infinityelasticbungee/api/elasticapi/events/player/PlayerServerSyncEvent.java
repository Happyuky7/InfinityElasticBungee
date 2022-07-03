package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events.player;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events.ElasticPlayerEvent;

public class PlayerServerSyncEvent extends ElasticPlayerEvent {

    private final String server;

    public PlayerServerSyncEvent(final String proxyID, final String username, final String server) {
        super(proxyID, username);

        this.server = server;
    }

    public String getServer() {
        return this.server;
    }
}

