package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class MessageUtils {

    public static BaseComponent[] format(final String message) {
        return new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', message)).create();
    }

    public static void sendMessage(final CommandSender sender, final String message) {
        sender.sendMessage(format(message));
    }

    public static String getMsgColor(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
