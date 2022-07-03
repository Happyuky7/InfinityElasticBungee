package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.subcmds;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync.results.PlayerSyncResult;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.MessageUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class SubIPCommand {

    private final InfinityElasticBungee plugin;

    public SubIPCommand(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new ComponentBuilder("Usage: /eb ip <player>").color(ChatColor.RED).create());
            return;
        }

        final String username = args[0];
        final PlayerSyncResult player = this.plugin.getPlayerSync().getPlayer(username);

        if (player == null) {
            MessageUtils.sendMessage(sender, "&CThat user is not online");
        } else {
            MessageUtils.sendMessage(sender, "&AIP of " + username + " is &e" + player.getAddress());
        }
    }

}
