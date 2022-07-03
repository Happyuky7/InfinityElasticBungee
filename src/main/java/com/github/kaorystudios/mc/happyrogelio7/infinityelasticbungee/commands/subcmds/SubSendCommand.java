package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.subcmds;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.MessageUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class SubSendCommand {

    private final InfinityElasticBungee plugin;

    public SubSendCommand(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length <= 1) {
            sender.sendMessage(new ComponentBuilder("Usage: /eb send <player> <server>").color(ChatColor.RED).create());
            return;
        }

        final String player = args[0];
        final String target = args[1];

        this.plugin.getSendSync().sendServer(player, target);
        MessageUtils.sendMessage(sender,
                "&ePlayer &b(" + player + ") &esend to &a" + target + " request has been sent to all nodes.");
    }

}
