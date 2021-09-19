package org.lumenk.onsky.completers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.lumenk.onsky.utils.HomeUtil;

import java.util.ArrayList;
import java.util.List;

public class DeleteHomeCommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof Player)) return null;
        Player player = (Player)sender;
        String current = args.length == 0 ? "" : HomeUtil.makeHomeName(args);
        List<String> homeList = HomeUtil.getHomes(player);
        List<String> result = new ArrayList<>();
        homeList.forEach(h ->{
            if(h.startsWith(current))
                result.add(h);
        });
        return result;
    }

    @Override
    public String toString() {
        return "delhome";
    }
}
