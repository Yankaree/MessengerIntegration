package me.ngcsonsplash.messengerintegration.bridge;

import me.ngcsonsplash.messengerintegration.websocket.MinecraftWSClient;

public class BridgeClient {

    private final MinecraftWSClient wsClient;

    public BridgeClient(MinecraftWSClient wsClient) {
        this.wsClient = wsClient;
    }

    public void send(String message) {
        wsClient.sendMessage(message);
    }
}
