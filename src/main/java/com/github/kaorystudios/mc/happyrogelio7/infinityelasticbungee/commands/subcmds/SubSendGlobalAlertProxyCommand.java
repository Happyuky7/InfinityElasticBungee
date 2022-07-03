package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.subcmds;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.google.common.base.Joiner;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public class SubSendGlobalAlertProxyCommand {

    private final InfinityElasticBungee plugin;

    public SubSendGlobalAlertProxyCommand(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new ComponentBuilder("Usage: /eb galert <message>").color(ChatColor.RED).create());
            return;
        }

        if (args.length > 0) {
            String alertmsg = Joiner.on(" ").skipNulls().join(args);
            this.plugin.getBroadcastSync().broadcast(plugin.getConfig().getString("messages.global-alert") + " " + alertmsg);
            TextComponent message = new TextComponent();
            message.setColor(ChatColor.GREEN);
            message.setText(this.plugin.getConfig().getString("messages.global-alert-sufficiently").replaceAll("%message%", alertmsg));
            sender.sendMessage(message);
        }

    }

}
