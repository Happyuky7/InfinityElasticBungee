package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.subcmds;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.MessageUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class SubSendAllCommand {

    private final InfinityElasticBungee plugin;

    public SubSendAllCommand(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length <= 1) {
            sender.sendMessage(new ComponentBuilder("Usage: /eb send <source> <target>").color(ChatColor.RED).create());
            return;
        }

        final String source = args[0];
        final String target = args[1];

        this.plugin.getSendAllSync().sendAllServer(source, target);
        MessageUtils.sendMessage(sender,
                "&eAll players in &b(" + source + ") &esend to &a" + target + " request has been sent to all nodes.");
    }

}
