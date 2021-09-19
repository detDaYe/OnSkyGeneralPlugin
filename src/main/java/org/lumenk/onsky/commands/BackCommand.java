package org.lumenk.onsky.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lumenk.onsky.utils.TextUtil;

import java.util.HashMap;
import java.util.UUID;

public class BackCommand implements CommandExecutor {

    private static final HashMap<UUID, Location> lastPositon = new HashMap<>();

    public static void updatePosition(Player player){
        lastPositon.put(player.getUniqueId(), player.getLocation());
    }

    public static void removePosition(Player player){
        lastPositon.remove(player.getUniqueId());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        Location previous = lastPositon.getOrDefault(player.getUniqueId(), null);
        if(previous == null){
            player.sendMessage(TextUtil.toColor("&c귀하의 이전 위치를 추적할 수 없습니다"));
            return false;
        }

        player.teleport(previous);
        return true;
    }

    @Override
    public String toString() {
        return "back";
    }
}
