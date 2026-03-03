package me.ngcsonsplash.messengerintegration.bridge;

import me.ngcsonsplash.messengerintegration.websocket.MinecraftWSClient;

public class BridgeClient {

    private final MinecraftWSClient wsClient;

    public BridgeClient(MinecraftWSClient wsClient) {
        this.wsClient = wsClient;
    }

    public void send(String type, String content) {
        if (wsClient == null) return;

        String json = "{"
                + "\"type\":\"" + escape(type) + "\","
                + "\"content\":\"" + escape(content) + "\""
                + "}";

        wsClient.sendMessage(json);
    }

    private String escape(String input) {
        return input.replace("\"", "\\\"");
    }
}
