package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementListener implements Listener {

    private final BridgeClient bridgeClient;

    public AdvancementListener(BridgeClient bridgeClient) {
        this.bridgeClient = bridgeClient;
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {
        if (event.getAdvancement().getDisplay() == null) return;

        String title = event.getAdvancement().getDisplay().getTitle();
        String player = event.getPlayer().getName();

        bridgeClient.send("advancement", player + " achieved " + title);
    }
}
