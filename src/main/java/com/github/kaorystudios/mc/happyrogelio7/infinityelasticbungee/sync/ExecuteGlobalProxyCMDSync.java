package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync;

/*
 * Code by HappyRogelio7.
 * Contributors: KaoryStudios. All rights reserved.
 * Copyright © 2022 KaoryStudios. All rights reserved.
 * Copyright © 2017 HappyRogelio7. All rights reserved.
 */

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.redis.RedisMessage;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.redis.RedisSubscription;

public class ExecuteGlobalProxyCMDSync implements RedisSubscription {
    public final static String CHANNEL = "send_all_cmd";

    private final InfinityElasticBungee plugin;

    public ExecuteGlobalProxyCMDSync(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void sendExecuteGlobalProxyCMDSync(final String command) {
        this.plugin.getMessageBroker().publish(CHANNEL, command);
    }

    @Override
    public void onReceive(final RedisMessage message) {
        if (!message.getChannel().equals(CHANNEL)) {
            return;
        }

        this.plugin.getProxy().getPluginManager().dispatchCommand(this.plugin.getProxy().getConsole(), message.getContent());
    }

}


