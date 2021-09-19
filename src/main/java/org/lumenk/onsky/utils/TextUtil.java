package org.lumenk.onsky.utils;

import net.md_5.bungee.api.ChatColor;

public class TextUtil {
    public static String toColor(String in){
        return ChatColor.translateAlternateColorCodes('&', in);
    }
}
