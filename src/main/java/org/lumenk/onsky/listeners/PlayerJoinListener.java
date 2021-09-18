package org.lumenk.onsky.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import utils.TextUtil;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event){
        //event.getPlayer().sendMessage("안녕하세요!");
        //event.getPlayer().sendMessage("Hello World!");
        String playerName = event.getPlayer().getName();

        event.setJoinMessage(TextUtil.toColor(
                "&b&l[&f&l+&b&l]&f " + playerName
        ));

        event.getPlayer().sendMessage(
                ChatColor.of("#7D0552") + "Hello"
        );
    }
}
