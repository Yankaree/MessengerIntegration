package me.ngcsonsplash.messengerintegration;

import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import me.ngcsonsplash.messengerintegration.listener.ChatListener;
import me.ngcsonsplash.messengerintegration.listener.DeathListener;
import me.ngcsonsplash.messengerintegration.listener.JoinLeaveListener;
import me.ngcsonsplash.messengerintegration.websocket.MinecraftWSClient;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;

public final class MessengerIntegration extends JavaPlugin {

    private MinecraftWSClient wsClient;
    private BridgeClient bridgeClient;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            String wsUrl = getConfig().getString("websocket.url");
            wsClient = new MinecraftWSClient(new URI(wsUrl), this);
            wsClient.connect();
            bridgeClient = new BridgeClient(wsClient);

        } catch (Exception e) {
            getLogger().severe("WebSocket init failed: " + e.getMessage());
        }

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    }

    @Override
    public void onDisable() {
        if (wsClient != null && wsClient.isOpen()) {
            wsClient.close();
        }
    }

    public BridgeClient getBridgeClient() {
        return bridgeClient;
    }
}
