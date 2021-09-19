package org.lumenk.onsky.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.lumenk.onsky.utils.TextUtil;

public class ServerPingListener implements Listener {
    @EventHandler
    public void onServerPing(ServerListPingEvent event){
        event.setMotd(TextUtil.toColor("&c빨간 버튼&e이후의 세계"));
    }
}
