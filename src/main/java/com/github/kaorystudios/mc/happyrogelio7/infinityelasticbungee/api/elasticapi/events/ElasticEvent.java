package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.elasticapi.events;

import net.md_5.bungee.api.plugin.Event;

public class ElasticEvent extends Event {
    private final String proxyID;

    public ElasticEvent(final String proxyID) {
        this.proxyID = proxyID;
    }

    public String getProxyID() {
        return this.proxyID;
    }
}

