package me.ngcsonsplash.messengerintegration;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import me.ngcsonsplash.messengerintegration.listener.ChatListener;
import me.ngcsonsplash.messengerintegration.listener.DeathListener;
import me.ngcsonsplash.messengerintegration.listener.JoinLeaveListener;
import me.ngcsonsplash.messengerintegration.websocket.MinecraftWSClient;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;

public final class MessengerIntegration extends JavaPlugin {

    private MinecraftWSClient wsClient;
    private BridgeClient bridgeClient;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        String wsUrl = getConfig().getString("websocket.url");
        try {
            wsClient = new MinecraftWSClient(new URI(wsUrl), this);
            wsClient.connect();

            bridgeClient = new BridgeClient(wsClient);

            Bukkit.getPluginManager().registerEvents(new ChatListener(bridgeClient), this);
            Bukkit.getPluginManager().registerEvents(new JoinLeaveListener(bridgeClient), this);
            Bukkit.getPluginManager().registerEvents(new DeathListener(bridgeClient), this);

            getLogger().info("MessengerIntegration enabled.");

        } catch (Exception e) {
            getLogger().severe("Invalid WebSocket URL in config.yml");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        if (wsClient != null) {
            wsClient.shutdown();
        }
    }
}
