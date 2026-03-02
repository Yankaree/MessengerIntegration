package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatListener implements Listener {

    private final BridgeClient bridge;

    public ChatListener(JavaPlugin plugin) {
        this.bridge = new BridgeClient(plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = "<" + event.getPlayer().getName() + "> " + event.getMessage();
        bridge.sendMessage("chat", message);
    }
}
