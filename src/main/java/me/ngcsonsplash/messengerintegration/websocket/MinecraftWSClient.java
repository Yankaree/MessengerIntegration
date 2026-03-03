package me.ngcsonsplash.messengerintegration.websocket;

import me.ngcsonsplash.messengerintegration.MessengerIntegration;
import org.bukkit.Bukkit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

public class MinecraftWSClient extends WebSocketClient {

    private final MessengerIntegration plugin;
    private Timer keepAliveTimer;

    public MinecraftWSClient(URI serverUri, MessengerIntegration plugin) {
        super(serverUri);
        this.plugin = plugin;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        plugin.getLogger().info("WebSocket connected!");
        startKeepAlive();
    }

    @Override
    public void onMessage(String message) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            Bukkit.broadcastMessage("§a[Bridge] §f" + message);
        });
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        plugin.getLogger().warning("WebSocket closed. Reconnecting in 5s...");
        stopKeepAlive();
        reconnectLater();
    }

    @Override
    public void onError(Exception ex) {
        plugin.getLogger().warning("WebSocket error: " + ex.getMessage());
    }

    private void startKeepAlive() {
        keepAliveTimer = new Timer();
        keepAliveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isOpen()) {
                    send("ping");
                }
            }
        }, 30000, 30000);
    }

    private void stopKeepAlive() {
        if (keepAliveTimer != null) {
            keepAliveTimer.cancel();
        }
    }

    private void reconnectLater() {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            try {
                reconnect();
            } catch (Exception e) {
                plugin.getLogger().warning("Reconnect failed: " + e.getMessage());
            }
        }, 100L);
    }
}
