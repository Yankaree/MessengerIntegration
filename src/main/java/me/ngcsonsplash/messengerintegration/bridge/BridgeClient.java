package me.ngcsonsplash.messengerintegration.bridge;

import com.google.gson.JsonObject;
import me.ngcsonsplash.messengerintegration.websocket.MinecraftWSClient;

public class BridgeClient {

    private final MinecraftWSClient wsClient;

    public BridgeClient(MinecraftWSClient wsClient) {
        this.wsClient = wsClient;
    }

    public void send(String type, String content) {
        if (wsClient == null) return;

        JsonObject json = new JsonObject();
        json.addProperty("type", type);
        json.addProperty("content", content);

        wsClient.sendMessage(json.toString());
    }
}