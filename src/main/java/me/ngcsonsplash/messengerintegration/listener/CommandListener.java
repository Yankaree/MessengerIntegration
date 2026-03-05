package me.ngcsonsplash.messengerintegration.listener;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandListener implements Listener {

    private final BridgeClient bridgeClient;

    public CommandListener(BridgeClient bridgeClient) {
        this.bridgeClient = bridgeClient;
    }

    @EventHandler
    public void onCommand(ServerCommandEvent event) {
        String cmd = event.getCommand();

        if (cmd.toLowerCase().startsWith("say ")) {
            bridgeClient.send("say", cmd.substring(4));
        }
    }
}
