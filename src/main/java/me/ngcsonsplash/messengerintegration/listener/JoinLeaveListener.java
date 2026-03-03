package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    private final BridgeClient bridgeClient;

    public JoinLeaveListener(BridgeClient bridgeClient) {
        this.bridgeClient = bridgeClient;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        bridgeClient.send("join", event.getPlayer().getName() + " joined the server");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        bridgeClient.send("leave", event.getPlayer().getName() + " left the server");
    }
}
