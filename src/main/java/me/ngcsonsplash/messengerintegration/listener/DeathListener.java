package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private final BridgeClient bridgeClient;

    public DeathListener(BridgeClient bridgeClient) {
        this.bridgeClient = bridgeClient;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        String msg = event.getDeathMessage();
        if (msg != null) {
            bridgeClient.send("death", msg);
        }
    }
}
