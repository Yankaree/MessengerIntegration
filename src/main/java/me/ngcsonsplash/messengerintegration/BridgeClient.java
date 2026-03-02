package me.ngcsonsplash.messengerintegration;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BridgeClient {

    private final MessengerIntegrationPlugin plugin;
    private final String bridgeUrl;
    private final int timeout;

    public BridgeClient(MessengerIntegrationPlugin plugin) {
        this.plugin = plugin;

        FileConfiguration config = plugin.getConfig();
        this.bridgeUrl = config.getString("bridge.url");
        this.timeout = config.getInt("bridge.timeout", 5000);
    }

    public void send(String type, String message) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL(bridgeUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.setConnectTimeout(timeout);
                connection.setReadTimeout(timeout);
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");

                String json = "{"
                        + "\"type\":\"" + escape(type) + "\","
                        + "\"message\":\"" + escape(message) + "\""
                        + "}";

                byte[] outputBytes = json.getBytes(StandardCharsets.UTF_8);

                try (OutputStream os = connection.getOutputStream()) {
                    os.write(outputBytes);
                }

                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    plugin.getLogger().warning("Bridge returned HTTP " + responseCode);
                }

                connection.disconnect();

            } catch (Exception e) {
                plugin.getLogger().warning("Failed to send message to bridge: " + e.getMessage());
            }
        });
    }

    private String escape(String text) {
        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}
