package org.lumenk.onsky.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lumenk.onsky.utils.HomeUtil;
import org.lumenk.onsky.utils.TextUtil;

public class DeleteHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다");
            return false;
        }
        Player player = (Player)sender;
        String homeName = args.length == 0 ? "home" : HomeUtil.makeHomeName(args);
        if(HomeUtil.delHome(player, homeName)){
            player.sendMessage(TextUtil.toColor("집 &6" + homeName + "&f을(를) 목록에서 제거하였습니다."));
        }else{
            player.sendMessage(TextUtil.toColor("집 &6" + homeName + "&f은(는) 존재하지 않습니다"));
        }
        return true;
    }

    @Override
    public String toString() {
        return "delhome";
    }
}
