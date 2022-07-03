package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events;

public class ElasticPlayerEvent extends ElasticEvent {
    private final String username;

    public ElasticPlayerEvent(final String proxyID, final String username) {
        super(proxyID);
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}

