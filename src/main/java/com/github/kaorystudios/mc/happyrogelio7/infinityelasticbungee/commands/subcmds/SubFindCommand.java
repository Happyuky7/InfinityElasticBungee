package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.subcmds;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync.results.PlayerSyncResult;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class SubFindCommand {

    private final InfinityElasticBungee plugin;

    public SubFindCommand(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new ComponentBuilder("Usage: /eb find <player>").color(ChatColor.RED).create());
            return;
        }

        final String username = args[0];
        final PlayerSyncResult player = this.plugin.getPlayerSync().getPlayer(username);

        if (player == null) {
            sender.sendMessage(new ComponentBuilder("That user is not online.").color(ChatColor.RED).create());
        } else {
            sender.sendMessage(new ComponentBuilder(ChatColor.GREEN + username + ChatColor.RESET + " is online in "
                    + player.getServerName() + " " + ChatColor.AQUA + "(" + player.getProxyID() + ")").create());
        }
    }

}
