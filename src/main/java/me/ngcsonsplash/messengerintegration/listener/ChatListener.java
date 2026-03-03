package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.MessengerIntegration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final MessengerIntegration plugin;

    public ChatListener(MessengerIntegration plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        String msg = event.getPlayer().getName() + ": " + event.getMessage();
        plugin.sendToNode(msg);
    }
}
