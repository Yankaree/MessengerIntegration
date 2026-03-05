package me.ngcsonsplash.messengerintegration.listener;

import io.papermc.paper.advancement.AdvancementDisplay;
import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
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
        AdvancementDisplay display = event.getAdvancement().getDisplay();
        if (display == null) return;

        String title = PlainTextComponentSerializer.plainText().serialize(display.title());
        String player = event.getPlayer().getName();

        bridgeClient.send("advancement", player + " achieved " + title);
    }
}
