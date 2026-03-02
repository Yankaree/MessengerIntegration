package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.death.DeathMessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        String customMessage = DeathMessageUtil.generate(event);

        if (customMessage != null) {
            event.setDeathMessage(customMessage);
        }
    }
}
