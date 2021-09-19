package org.lumenk.onsky.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.lumenk.onsky.Onsky;
import org.lumenk.onsky.utils.TextUtil;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event){
        //event.getPlayer().sendMessage("안녕하세요!");
        //event.getPlayer().sendMessage("Hello World!");
        String playerName = event.getPlayer().getName();

        event.setJoinMessage(TextUtil.toColor(
                "&b&l[&f&l+&b&l]&f " + playerName
        ));

        /*
        event.getPlayer().sendMessage(
                ChatColor.of("#7D0552") + "Hello"
        );
         */

        boolean needUpdate = false;
        String prevName = Onsky.getSaveFile().getString(event.getPlayer().getUniqueId() + ".name");
        if(prevName == null){
            event.getPlayer().sendMessage("안녕하세요! 업데이트 된 이후로는 처음 뵙습니다! - OnSky Plugin");
            needUpdate = true;
        }else if(!prevName.equals(playerName)){
            event.getPlayer().sendMessage("안녕하세요! " + prevName + "님, 아니 이제는 " + playerName + "님이시군요!");
            event.getPlayer().sendMessage("새 이름도 잘 어울리네요! - OnSky");
            needUpdate = true;
        }

        if(needUpdate){
            Onsky.getSaveFile().set(event.getPlayer().getUniqueId() + ".name", playerName);
            Onsky.saveFiles();
        }

        String displayName = Onsky.getSaveFile().getString(event.getPlayer().getUniqueId() + ".display-name");
        if(displayName != null){
            event.getPlayer().setDisplayName(TextUtil.toColor(displayName));
        }

    }
}
