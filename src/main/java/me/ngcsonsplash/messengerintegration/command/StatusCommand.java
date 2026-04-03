package me.ngcsonsplash.messengerintegration.command;

import me.ngcsonsplash.messengerintegration.MessengerIntegration;
import me.ngcsonsplash.messengerintegration.bridge.BridgeClient;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class StatusCommand implements CommandExecutor {

    private final MessengerIntegration plugin;
    private final BridgeClient bridgeClient;

    public StatusCommand(MessengerIntegration plugin, BridgeClient bridgeClient) {
        this.plugin = plugin;
        this.bridgeClient = bridgeClient;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("MessengerIntegration status: " + (plugin.getWsClient().isOpen() ? "Connected" : "Disconnected"));
        return true;
    }
}
