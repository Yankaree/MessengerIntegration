package me.ngcsonsplash.messengerintegration.websocket;

import me.ngcsonsplash.messengerintegration.MessengerIntegration;
import org.bukkit.Bukkit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MinecraftWSClient extends WebSocketClient {

    private final MessengerIntegration plugin;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean manuallyClosed = false;

    public MinecraftWSClient(URI serverUri, MessengerIntegration plugin) {
        super(serverUri);
        this.plugin = plugin;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        plugin.getLogger().info("WebSocket connected.");
        startKeepAlive();
    }

    @Override
    public void onMessage(String message) {
        // Chuyển về main thread trước khi dùng Bukkit API
        Bukkit.getScheduler().runTask(plugin, () -> {
            Bukkit.broadcastMessage("§a[Bridge] §f" + message);
        });
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        plugin.getLogger().warning("WebSocket closed: " + reason);

        if (!manuallyClosed) {
            reconnectLater();
        }
    }

    @Override
    public void onError(Exception ex) {
        plugin.getLogger().warning("WebSocket error: " + ex.getMessage());
    }

    public void sendMessage(String message) {
        if (isOpen()) {
            send(message);
        }
    }

    private void reconnectLater() {
        scheduler.schedule(() -> {
            try {
                plugin.getLogger().info("Attempting reconnect...");
                reconnect();
            } catch (Exception ignored) {}
        }, 5, TimeUnit.SECONDS);
    }

    private void startKeepAlive() {
        scheduler.scheduleAtFixedRate(() -> {
            if (isOpen()) {
                send("ping");
            }
        }, 30, 30, TimeUnit.SECONDS);
    }

    public void shutdown() {
        manuallyClosed = true;
        scheduler.shutdownNow();
        close();
    }
}
