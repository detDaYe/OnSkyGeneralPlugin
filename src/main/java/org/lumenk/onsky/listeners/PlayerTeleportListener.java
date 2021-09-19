package org.lumenk.onsky.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.lumenk.onsky.commands.BackCommand;

public class PlayerTeleportListener implements Listener {

    @EventHandler
    public void OnPlayerTeleport_forBackCommand(PlayerTeleportEvent event){
        boolean temp = false;
        if(!event.getFrom().getWorld().equals(event.getTo().getWorld())){
            temp = true;
        }else if(event.getFrom().distance(event.getTo()) >= 5.0){
            temp = true;
        }

        if(temp){
            BackCommand.updatePosition(event.getPlayer());
        }
    }
}
