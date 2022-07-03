package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.subcmds.*;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.MessageUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;

public class InfinityElasticBungeeCommand extends Command {

    //START InfinityElasticBungee

    private final SubExecuteGlobalProxyCMDCommand executeGlobalProxyCMDCommand;

    private final SubSendGlobalAlertProxyCommand sendGlobalAlertProxyCommand;

    //END InfinityElasticBungee

    private final SubBroadcastCommand broadcastCommand;
    private final SubFindCommand findCommand;
    private final SubIPCommand ipCommand;
    private final SubKickCommand kickCommand;
    private final SubSendAllCommand sendAllCommand;
    private final SubSendCommand sendCommand;

    private final String version;

    public InfinityElasticBungeeCommand(final InfinityElasticBungee plugin) {
        super("infinityelasticbungee", "infinityelasticbungee.use", "ieb");

        //START InfinityElasticBungee
        this.executeGlobalProxyCMDCommand = new SubExecuteGlobalProxyCMDCommand(plugin);
        this.sendGlobalAlertProxyCommand = new SubSendGlobalAlertProxyCommand(plugin);
        //END InfinityElasticBungee

        this.broadcastCommand = new SubBroadcastCommand(plugin);
        this.findCommand = new SubFindCommand(plugin);
        this.ipCommand = new SubIPCommand(plugin);
        this.kickCommand = new SubKickCommand(plugin);
        this.sendAllCommand = new SubSendAllCommand(plugin);
        this.sendCommand = new SubSendCommand(plugin);

        this.version = plugin.getDescription().getVersion();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length == 0 || args[0].equalsIgnoreCase("help") || args[0].equals("?")) {
            MessageUtils.sendMessage(sender, "");
            MessageUtils.sendMessage(sender, "&a&lInfinity&6&lElastic&e&lBungee &8(" + this.version + ")");
            MessageUtils.sendMessage(sender, "");
            MessageUtils.sendMessage(sender, "&a/galert <message> &7- &eSend a global alert to all nodes.");
            MessageUtils.sendMessage(sender, "&a/egpcmd <command> &7- &eExecute a command on all nodes.");
            MessageUtils.sendMessage(sender, "");
            MessageUtils.sendMessage(sender, "&e/ieb help &8- &7Show this help.");
            MessageUtils.sendMessage(sender, "&c/ieb egpcmd <cmd> &8- &7send a command to all proxies.");
            MessageUtils.sendMessage(sender, "&c/ieb galert <message> &8- &7send a message alert to all proxies.");
            MessageUtils.sendMessage(sender, "&c/ieb broadcast <message> &8- &7send a message raw to all proxies.");
            MessageUtils.sendMessage(sender, "&c/ieb find <player> &8- &7Search to which server a user is connected.");
            MessageUtils.sendMessage(sender, "&c/ieb kick <player> &8- Kick a player.");
            MessageUtils.sendMessage(sender, "&c/ieb ip <player> &8- Get a player's ip.");
            MessageUtils.sendMessage(sender, "&c/ieb send <player> <server> &8- Send a player to a server.");
            MessageUtils.sendMessage(sender, "&c/ieb sendall <source> <target> &8- Send all players from one server to another.");
            MessageUtils.sendMessage(sender, "");
            return;
        }

        final String subCommand = args[0];
        final String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

        switch (subCommand) {
            case "egpcmd":
                this.executeGlobalProxyCMDCommand.execute(sender, subArgs);
                break;
            case "galert":
                this.sendGlobalAlertProxyCommand.execute(sender, subArgs);
                break;
            case "broadcast":
                this.broadcastCommand.execute(sender, subArgs);
                break;
            case "find":
                this.findCommand.execute(sender, subArgs);
                break;
            case "kick":
                this.kickCommand.execute(sender, subArgs);
                break;
            case "ip":
                this.ipCommand.execute(sender, subArgs);
                break;
            case "sendall":
                this.sendAllCommand.execute(sender, subArgs);
                break;
            case "send":
                this.sendCommand.execute(sender, subArgs);
                break;
            default:
                MessageUtils.sendMessage(sender, "&cError: Subcommand " + subCommand + "doesn't exist.");
        }

    }

}
