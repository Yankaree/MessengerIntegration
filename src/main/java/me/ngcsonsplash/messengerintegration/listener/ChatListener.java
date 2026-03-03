package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final BridgeClient bridgeClient;

    public ChatListener(BridgeClient bridgeClient) {
        this.bridgeClient = bridgeClient;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String player = event.getPlayer().getName();
        String message = event.getMessage();

        bridgeClient.send("chat", player + ": " + message);
    }
}
