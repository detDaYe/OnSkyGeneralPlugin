package org.lumenk.onsky.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import utils.TextUtil;

public class onSkyDummyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(TextUtil.toColor("&f안녕! 나는 &b창공&f의 서버를 위해 개발된 &eOnSky&6플러그인&f이라고 해!"));
        sender.sendMessage("아쉽지만 이 명령어는 아무 기능도 하지 않아! 히히");
        return false;
    }

    @Override
    public String toString() {
        return "onskydummy";
    }
}
