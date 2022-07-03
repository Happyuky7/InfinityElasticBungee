package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.MessageUtils;
import com.google.common.base.Joiner;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class GAlertCommand extends Command {

    private final InfinityElasticBungee plugin;

    public GAlertCommand(final InfinityElasticBungee plugin) {
        super("galert", "infinityelasticbungee.command.galert");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length <= 1) {
            sender.sendMessage(MessageUtils.getMsgColor("&aUsage: &f/galert &3<message>"));
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
