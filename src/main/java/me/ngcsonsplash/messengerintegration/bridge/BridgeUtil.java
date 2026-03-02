package me.ngcsonsplash.messengerintegration.bridge;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BridgeUtil {

    private static JavaPlugin plugin;
    private static String endpoint;

    public static void init(JavaPlugin pl, String url) {
        plugin = pl;
        endpoint = url;
    }

    public static void send(String message) {

        if (endpoint == null || endpoint.isEmpty()) return;

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            try {

                URL url = new URL(endpoint);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                String json = "{ \"message\": \"" 
                        + message.replace("\"", "\\\\\"") + "\" }";

                try (OutputStream os = connection.getOutputStream()) {
                    os.write(json.getBytes(StandardCharsets.UTF_8));
                }

                connection.getResponseCode();
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
