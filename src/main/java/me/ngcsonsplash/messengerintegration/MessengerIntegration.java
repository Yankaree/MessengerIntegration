package me.ngcsonsplash.messengerintegration;

import me.ngcsonsplash.messengerintegration.listener.ChatListener;
import me.ngcsonsplash.messengerintegration.websocket.MinecraftWSClient;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;

public final class MessengerIntegration extends JavaPlugin {

    private MinecraftWSClient wsClient;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        if (getConfig().getBoolean("websocket.enabled")) {
            try {
                String host = getConfig().getString("websocket.host");
                int port = getConfig().getInt("websocket.port");

                URI uri = new URI("ws://" + host + ":" + port);
                wsClient = new MinecraftWSClient(uri, this);
                wsClient.connect();

                getLogger().info("Connecting to WS: " + uri);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);

        getLogger().info("MessengerIntegration enabled.");
    }

    @Override
    public void onDisable() {
        if (wsClient != null) {
            wsClient.close();
        }
    }

    public void sendToNode(String message) {
        if (wsClient != null && wsClient.isOpen()) {
            wsClient.send(message);
        }
    }

    public String getPrefix() {
        return getConfig().getString("websocket.prefix");
    }
}
