package org.lumenk.onsky.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.lumenk.onsky.commands.BackCommand;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void OnPlayerDeath_forBackCommand(PlayerDeathEvent event){
        BackCommand.updatePosition(event.getEntity(), event.getEntity().getLocation());
    }
}
