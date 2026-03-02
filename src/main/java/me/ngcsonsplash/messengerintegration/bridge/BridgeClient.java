package me.ngcsonsplash.messengerintegration.bridge;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BridgeClient {

    private final JavaPlugin plugin;
    private final String webhookUrl;

    public BridgeClient(JavaPlugin plugin) {
        this.plugin = plugin;
        this.webhookUrl = plugin.getConfig().getString("bridge.url");
    }

    public void sendMessage(String type, String message) {
        if (!plugin.getConfig().getBoolean("bridge.enabled")) return;

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL(webhookUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                String json = "{"
                        + "\"type\":\"" + type + "\","
                        + "\"message\":\"" + message.replace("\"", "'") + "\""
                        + "}";

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(json.getBytes(StandardCharsets.UTF_8));
                }

                conn.getResponseCode();

            } catch (Exception e) {
                plugin.getLogger().warning("Webhook lỗi: " + e.getMessage());
            }
        });
    }
}
