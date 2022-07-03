package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.MessageUtils;
import com.google.common.base.Joiner;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class ExecuteGlobalProxyCMDCommand extends Command {

    private final InfinityElasticBungee plugin;

    public ExecuteGlobalProxyCMDCommand(final InfinityElasticBungee plugin) {
        super("egpcmd", "infinityelasticbungee.command.egpcmd");
        this.plugin = plugin;
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(MessageUtils.getMsgColor("&aUsage: &f/egpcmd &3<message>"));
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
