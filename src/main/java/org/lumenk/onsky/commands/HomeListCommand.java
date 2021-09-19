package org.lumenk.onsky.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lumenk.onsky.utils.HomeUtil;

import java.util.List;

public class HomeListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        List<String> homeList = HomeUtil.getHomes(player);
        if(homeList.size() == 0){
            player.sendMessage("당신은 집이 없습니다.");
            return true;
        }

        player.sendMessage("=======집 목록=======");
        homeList.forEach(player::sendMessage);
        player.sendMessage("====================");
        return false;
    }

    @Override
    public String toString() {
        return "homelist";
    }
}
