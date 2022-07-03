package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.redis.RedisMessage;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.redis.RedisSubscription;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class SendGlobalAlertProxySync implements RedisSubscription {

    public final static String CHANNEL = "alert_broadcast";

    private final InfinityElasticBungee plugin;

    public SendGlobalAlertProxySync(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void sendGlobalAlert(final String message) {
        this.plugin.getMessageBroker().publish(CHANNEL, message);
    }

    @Override
    public void onReceive(final RedisMessage message) {
        if (!message.getChannel().equals(CHANNEL)) {
            return;
        }

        this.plugin.getProxy().broadcast(
                new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',
                        this.plugin.getConfig().getString("messages.global-alert") + " "
                                + message.getContent())).create());
    }

}
