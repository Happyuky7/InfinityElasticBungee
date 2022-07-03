package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.redis.RedisMessage;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.redis.RedisSubscription;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.MessageUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class KickSync implements RedisSubscription {
    public final static String CHANNEL = "kick";

    private final InfinityElasticBungee plugin;

    public KickSync(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void kick(final String player, final String message) {
        this.plugin.getMessageBroker().publish(CHANNEL, player + ":" + message);
    }

    @Override
    public void onReceive(final RedisMessage message) {
        if (!message.getChannel().equals(CHANNEL)) {
            return;
        }

        final String[] parts = message.getContent().split(":", 2);
        final String username = parts[0];
        final String reason = parts[1];

        final ProxiedPlayer proxyPlayer = this.plugin.getProxy().getPlayer(username);

        if (proxyPlayer != null) {
            proxyPlayer.disconnect(MessageUtils.format(reason));
        }
    }
}