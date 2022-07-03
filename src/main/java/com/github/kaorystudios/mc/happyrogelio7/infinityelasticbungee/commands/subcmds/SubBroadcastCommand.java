package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.subcmds;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class SubBroadcastCommand {

    private final InfinityElasticBungee plugin;

    public SubBroadcastCommand(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new ComponentBuilder("Usage: /eb broadcast <message>").color(ChatColor.RED).create());
            return;
        }

        String message = null;

        for (final String arg : args) {
            if (message == null) {
                message = arg;
            } else {
                message += " " + arg;
            }
        }

        this.plugin.getBroadcastSync().broadcast(message);
    }
}
