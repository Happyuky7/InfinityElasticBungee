package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.redis;

/*
 * Code by Sammwy and 2LStudios MC
 */

public interface RedisSubscription {
    public void onReceive(final RedisMessage message);
}
