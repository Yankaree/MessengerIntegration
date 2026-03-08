package me.ngcsonsplash.messengerintegration;

import me.ngcsonsplash.messengerintegration.command.StatusCommand;
import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import me.ngcsonsplash.messengerintegration.listener.*;
import me.ngcsonsplash.messengerintegration.status.StatusTask;
import me.ngcsonsplash.messengerintegration.websocket.MinecraftWSClient;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public final class MessengerIntegration extends JavaPlugin {

    private MinecraftWSClient wsClient;
    private BridgeClient bridgeClient;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        try {
            String url = getConfig().getString("websocket.url");
            wsClient = new MinecraftWSClient(new URI(url), this);
            wsClient.connect();

            bridgeClient = new BridgeClient(wsClient);

            // Register listeners
            getServer().getPluginManager().registerEvents(
                    new ChatListener(bridgeClient), this);

            getServer().getPluginManager().registerEvents(
                    new JoinLeaveListener(bridgeClient), this);

            getServer().getPluginManager().registerEvents(
                    new DeathListener(bridgeClient), this);

            getServer().getPluginManager().registerEvents(
                    new AdvancementListener(bridgeClient), this);

            getServer().getPluginManager().registerEvents(
                    new CommandListener(bridgeClient), this);
            
            // Register command
            getCommand("msstatus").setExecutor(new StatusCommand(this, bridgeClient));

            // Schedule status task
            Bukkit.getAsyncScheduler().runAtFixedRate(this, (task) -> new StatusTask(this).run(), 0L, 5L, TimeUnit.SECONDS);

            getLogger().info("MessengerIntegration enabled.");

        } catch (Exception e) {
            getLogger().severe("Failed to connect WebSocket: " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        if (wsClient != null && wsClient.isOpen()) {
            wsClient.close();
        }
        getLogger().info("MessengerIntegration disabled.");
    }

    public MinecraftWSClient getWsClient() {
        return wsClient;
    }
}