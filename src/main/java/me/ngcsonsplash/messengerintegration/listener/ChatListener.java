package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.plaintext.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    private final BridgeClient bridgeClient;

    public ChatListener(BridgeClient bridgeClient) {
        this.bridgeClient = bridgeClient;
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        String player = event.getPlayer().getName();
        String message = PlainTextComponentSerializer.plainText().serialize(event.message());

        bridgeClient.send("chat", player + ": " + message);
    }
}
