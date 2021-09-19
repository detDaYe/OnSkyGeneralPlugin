package org.lumenk.onsky.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lumenk.onsky.utils.HomeUtil;
import org.lumenk.onsky.utils.TextUtil;

import java.util.List;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("이 명령어는 플레이어만 실행할 수 있습니다.");
            return false;
        }

        Player player = (Player) sender;
        List<String> homeList = HomeUtil.getHomes(player);
        if(homeList.size() >= 25){
            player.sendMessage(TextUtil.toColor("&4너는 너무 많은 집을 가지고 있어. &c욕심 부리지 마렴"));
            return false;
        }

        String homeName = args.length == 0 ? "home" : HomeUtil.makeHomeName(args);

        if(HomeUtil.addHome(player, homeName)){
            player.sendMessage(TextUtil.toColor(TextUtil.toColor("집 &6" + homeName + "&f을(를) 등록하였습니다.")));
        }else{
            player.sendMessage(TextUtil.toColor("집 &6" + homeName + "&f은(는) 이미 등록되어 있습니다"));
        }

        return false;
    }

    @Override
    public String toString() {
        return "sethome";
    }
}
