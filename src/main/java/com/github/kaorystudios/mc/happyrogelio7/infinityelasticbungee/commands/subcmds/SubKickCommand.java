package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.subcmds;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.MessageUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class SubKickCommand {

    private final InfinityElasticBungee plugin;

    public SubKickCommand(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length <= 1) {
            sender.sendMessage(new ComponentBuilder("Usage: /eb kick <player> <reason>").color(ChatColor.RED).create());
            return;
        }

        String username = null;
        String reason = null;

        for (final String arg : args) {
            if (username == null) {
                username = arg;
            } else if (reason == null) {
                reason = arg;
            } else {
                reason += " " + arg;
            }
        }

        this.plugin.getKickSync().kick(username, reason);
        MessageUtils.sendMessage(sender, "&ePlayer &b(" + username + ") &ekick request has been sent to all nodes.");
    }

}
