package me.ngcsonsplash.messengerintegration;

import me.ngcsonsplash.messengerintegration.listener.ChatListener;
import me.ngcsonsplash.messengerintegration.listener.DeathListener;
import me.ngcsonsplash.messengerintegration.listener.JoinLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessengerIntegration extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);

        getLogger().info("MessengerIntegration enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MessengerIntegration disabled!");
    }
}
