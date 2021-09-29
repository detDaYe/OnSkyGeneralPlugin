package org.lumenk.onsky.completers;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class OnSkyDummyCommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> result = new ArrayList<>();
        Bukkit.getServer().getOnlinePlayers().forEach(player -> result.add(player.getName()));
        return result;
    }

    @Override
    public String toString() {
        return "onskydummy";
    }
}
