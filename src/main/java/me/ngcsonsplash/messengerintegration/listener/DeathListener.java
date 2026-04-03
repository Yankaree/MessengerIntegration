package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
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
        Component deathMsg = event.deathMessage();
        if (deathMsg != null) {
            String msg = PlainTextComponentSerializer.plainText().serialize(deathMsg);
            bridgeClient.send("death", msg);
        }
    }
}
