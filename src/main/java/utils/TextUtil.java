package utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

public class TextUtil {
    public static String toColor(String in){
        return ChatColor.translateAlternateColorCodes('&', in);
    }
}
