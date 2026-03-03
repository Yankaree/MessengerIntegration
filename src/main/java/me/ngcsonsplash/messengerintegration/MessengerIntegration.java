package me.ngcsonsplash.messengerintegration;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import me.ngcsonsplash.messengerintegration.listener.ChatListener;
import me.ngcsonsplash.messengerintegration.listener.DeathListener;
import me.ngcsonsplash.messengerintegration.listener.JoinLeaveListener;
import me.ngcsonsplash.messengerintegration.websocket.MinecraftWSClient;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;

public final class MessengerIntegration extends JavaPlugin {

    private BridgeClient bridgeClient;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        String wsUrl = getConfig().getString("websocket.url");

        try {
            MinecraftWSClient wsClient =
                    new MinecraftWSClient(new URI(wsUrl), this);

            wsClient.connect();

            bridgeClient = new BridgeClient(wsClient);

            getServer().getPluginManager().registerEvents(
                    new ChatListener(bridgeClient), this);

            getServer().getPluginManager().registerEvents(
                    new JoinLeaveListener(bridgeClient), this);

            getServer().getPluginManager().registerEvents(
                    new DeathListener(bridgeClient), this);

        } catch (Exception e) {
            getLogger().severe("Invalid WebSocket URL!");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("MessengerIntegration disabled.");
    }
}
