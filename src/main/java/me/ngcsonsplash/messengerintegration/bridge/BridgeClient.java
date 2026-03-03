package me.ngcsonsplash.messengerintegration.bridge;

import me.ngcsonsplash.messengerintegration.websocket.MinecraftWSClient;

public class BridgeClient {

    private final MinecraftWSClient wsClient;

    public BridgeClient(MinecraftWSClient wsClient) {
        this.wsClient = wsClient;
    }

    public void sendMessage(String message) {
        if (wsClient != null && wsClient.isOpen()) {
            wsClient.send(message);
        }
    }
}
