package org.lumenk.onsky.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lumenk.onsky.utils.HomeUtil;
import org.lumenk.onsky.utils.TextUtil;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("이 명령어는 플레이어만 실행할 수 있습니다");
            return false;
        }

        Player player = (Player) sender;

        String homeName = args.length == 0 ? "home" : HomeUtil.makeHomeName(args);

        Location location = HomeUtil.getHome(player, homeName);
        if(location == null){
            player.sendMessage(TextUtil.toColor("귀하께서는 &6" + homeName + "&f을(를) 등록하지 않으셨습니다"));
            return false;
        }else {
            player.teleport(location);
            player.sendMessage(TextUtil.toColor("귀하의 집 &6" + homeName + "&f으로 이동하였습니다"));
        }
        return false;
    }

    @Override
    public String toString() {
        return "home";
    }
}
