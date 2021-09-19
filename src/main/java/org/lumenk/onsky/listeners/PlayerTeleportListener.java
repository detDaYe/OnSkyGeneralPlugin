package org.lumenk.onsky.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.lumenk.onsky.commands.BackCommand;

public class PlayerTeleportListener implements Listener {

    @EventHandler
    public void OnPlayerTeleport_forBackCommand(PlayerTeleportEvent event){
        BackCommand.testAndUpdatePosition(event.getPlayer(), event.getFrom(), event.getTo());
    }
}
