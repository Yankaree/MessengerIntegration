package me.ngcsonsplash.messengerintegration;

import org.bukkit.plugin.java.JavaPlugin;

public final class MessengerIntegrationPlugin extends JavaPlugin {

    private static MessengerIntegrationPlugin instance;
    private BridgeClient bridgeClient;
    private WebhookServer webhookServer;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        getLogger().info("Starting MessengerIntegration...");

        bridgeClient = new BridgeClient(this);
        webhookServer = new WebhookServer(this);

        webhookServer.start();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        getLogger().info("MessengerIntegration enabled!");
    }

    @Override
    public void onDisable() {
        if (webhookServer != null) {
            webhookServer.stop();
        }

        getLogger().info("MessengerIntegration disabled!");
    }

    public static MessengerIntegrationPlugin getInstance() {
        return instance;
    }

    public BridgeClient getBridgeClient() {
        return bridgeClient;
    }
}
