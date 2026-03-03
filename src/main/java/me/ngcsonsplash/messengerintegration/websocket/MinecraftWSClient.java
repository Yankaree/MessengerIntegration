package me.ngcsonsplash.messengerintegration.websocket;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class MinecraftWSClient extends WebSocketClient {

    private final JavaPlugin plugin;

    public MinecraftWSClient(URI serverUri, JavaPlugin plugin) {
        super(serverUri);
        this.plugin = plugin;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        plugin.getLogger().info("Connected to NodeJS WebSocket");
    }

    @Override
    public void onMessage(String message) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            String prefix = plugin.getConfig().getString("websocket.prefix");
            Bukkit.broadcastMessage(prefix + message);
        });
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        plugin.getLogger().warning("WebSocket disconnected: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        plugin.getLogger().severe("WebSocket error:");
        ex.printStackTrace();
    }
}
