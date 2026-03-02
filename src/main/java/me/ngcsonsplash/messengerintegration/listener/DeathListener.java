package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathListener implements Listener {

    private final BridgeClient bridge;

    public DeathListener(JavaPlugin plugin) {
        this.bridge = new BridgeClient(plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        String vanillaMessage = event.getDeathMessage();
        if (vanillaMessage == null) return;

        bridge.sendMessage("death", vanillaMessage);
    }
}
