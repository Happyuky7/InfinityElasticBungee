package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.subcmds;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.google.common.base.Joiner;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public class SubExecuteGlobalProxyCMDCommand {

    private final InfinityElasticBungee plugin;

    public SubExecuteGlobalProxyCMDCommand(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
    }

    public void execute(CommandSender sender, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(new ComponentBuilder("Usage: /eb egpcmd <cmd>").color(ChatColor.RED).create());
            return;
        }

        if (args.length > 0) {
            String command = Joiner.on(" ").skipNulls().join(args);
            this.plugin.getExecuteGlobalProxyCMDSync().sendExecuteGlobalProxyCMDSync(command);
            TextComponent message = new TextComponent();
            message.setColor(ChatColor.GREEN);
            message.setText(this.plugin.getConfig().getString("messages.global-alert-sufficiently").replaceAll("%command%", command));
            sender.sendMessage(message);
        }

    }

}
