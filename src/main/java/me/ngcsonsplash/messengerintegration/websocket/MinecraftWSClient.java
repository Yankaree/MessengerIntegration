package me.ngcsonsplash.messengerintegration.websocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import me.ngcsonsplash.messengerintegration.MessengerIntegration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        Bukkit.getGlobalRegionScheduler().run(plugin, (task) -> {
            try {
                JsonObject json = JsonParser.parseString(message).getAsJsonObject();
                if (json.has("sender") && json.has("message")) {
                    String sender = json.get("sender").getAsString();
                    String msg = json.get("message").getAsString();
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "Bridge" + ChatColor.DARK_AQUA + "] " + ChatColor.GOLD + sender + ChatColor.WHITE + ": " + msg);
                } else {
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "Bridge" + ChatColor.DARK_AQUA + "] " + ChatColor.WHITE + message);
                }
            } catch (JsonSyntaxException | IllegalStateException e) {
                Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "Bridge" + ChatColor.DARK_AQUA + "] " + ChatColor.WHITE + message);
            }
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
        if (!manuallyClosed) {
            scheduler.schedule(() -> {
                try {
                    plugin.getLogger().info("Attempting reconnect...");
                    reconnect();
                } catch (Exception ignored) {}
            }, 5, TimeUnit.SECONDS);
        }
    }

    private void startKeepAlive() {
        scheduler.scheduleAtFixedRate(() -> {
            if (isOpen()) {
                JsonObject ping = new JsonObject();
                ping.addProperty("type", "ping");
                send(ping.toString());
            }
        }, 30, 30, TimeUnit.SECONDS);
    }

    @Override
    public void close() {
        manuallyClosed = true;
        super.close();
        scheduler.shutdownNow();
    }
}