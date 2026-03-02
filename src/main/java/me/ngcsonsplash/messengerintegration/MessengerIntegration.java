package me.ngcsonsplash.messengerintegration;

import me.ngcsonsplash.messengerintegration.bridge.BridgeUtil;
import me.ngcsonsplash.messengerintegration.listener.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MessengerIntegration extends JavaPlugin {

    @Override
    public void onEnable() {

        // Tạo config nếu chưa có
        saveDefaultConfig();

        // Lấy URL NodeJS bridge
        String bridgeUrl = getConfig().getString("bridge.url");

        if (bridgeUrl == null || bridgeUrl.isEmpty()) {
            getLogger().warning("Bridge URL chưa được cấu hình trong config.yml!");
        } else {
            BridgeUtil.init(this, bridgeUrl);
            getLogger().info("Đã kết nối Bridge: " + bridgeUrl);
        }

        // Register event listener
        getServer().getPluginManager().registerEvents(
                new DeathListener(),
                this
        );

        getLogger().info("MessengerIntegration đã bật thành công!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MessengerIntegration đã tắt.");
    }
}
