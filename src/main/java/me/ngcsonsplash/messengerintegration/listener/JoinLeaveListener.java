package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinLeaveListener implements Listener {

    private final BridgeClient bridge;

    public JoinLeaveListener(JavaPlugin plugin) {
        this.bridge = new BridgeClient(plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getJoinMessage() != null) {
            bridge.sendMessage("join", event.getJoinMessage());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (event.getQuitMessage() != null) {
            bridge.sendMessage("leave", event.getQuitMessage());
        }
    }
}
