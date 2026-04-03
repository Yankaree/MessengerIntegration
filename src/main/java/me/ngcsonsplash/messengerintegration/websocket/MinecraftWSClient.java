package me.ngcsonsplash.messengerintegration.websocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import me.ngcsonsplash.messengerintegration.MessengerIntegration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit; // Thêm import này
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
                if (json.has("type")) {
                    String type = json.get("type").getAsString();

                    if (type.equals("messenger_message")) {
                        // Handle messages coming from Messenger
                        if (json.has("sender") && json.has("message")) {
                            String sender = json.get("sender").getAsString();
                            String msg = json.get("message").getAsString();

                            // Check for /status command from Messenger
                            if (msg.trim().equalsIgnoreCase("/status")) {
                                // Execute the command from console to trigger StatusCommand
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "msstatus");
                                return; // Command handled, don't broadcast raw message
                            }

                            Component broadcastMsg = Component.text()
                                    .append(Component.text("[", NamedTextColor.DARK_AQUA))
                                    .append(Component.text("Bridge", NamedTextColor.AQUA))
                                    .append(Component.text("] ", NamedTextColor.DARK_AQUA))
                                    .append(Component.text(sender, NamedTextColor.GOLD))
                                    .append(Component.text(": ", NamedTextColor.WHITE))
                                    .append(Component.text(msg, NamedTextColor.WHITE))
                                    .build();

                            Bukkit.broadcast(broadcastMsg);
                        }
                    } else if (type.equals("command_response")) {
                        // Handle responses from the bot for commands issued by Minecraft
                        if (json.has("message")) {
                            Bukkit.broadcast(Component.text("[Bridge Bot]: ", NamedTextColor.YELLOW).append(Component.text(json.get("message").getAsString(), NamedTextColor.WHITE)));
                        }
                    } else if (type.equals("status_request")) { // New type for status request from bot
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "msstatus");
                        return;
                    }
                    else {
                        // Fallback for other JSON types from bot, if any
                        Bukkit.broadcast(Component.text("[Bridge Bot Info]: ", NamedTextColor.GRAY).append(Component.text(message, NamedTextColor.WHITE)));
                    }
                } else {
                    // If no 'type' field, broadcast raw message
                    Bukkit.broadcast(Component.text("[Bridge Bot Raw]: ", NamedTextColor.GRAY).append(Component.text(message, NamedTextColor.WHITE)));
                }
            } catch (JsonSyntaxException | IllegalStateException e) {
                // If message is not valid JSON, broadcast raw message
                Bukkit.broadcast(Component.text("[Bridge Bot Error]: ", NamedTextColor.RED).append(Component.text("Invalid message from bot: " + message, NamedTextColor.WHITE)));
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