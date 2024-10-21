package org.onlinebookstore.onlinebookstorebackend.server;

import org.springframework.stereotype.Component;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocketServer {
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSession = new ConcurrentHashMap<>();
    private Session session;
    private String userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        if (webSocketSession.containsKey(userId)) {
            System.out.println("User already online: " + userId);
            return;
        }
        this.session = session;
        this.userId = userId;
        webSocketSession.put(userId, this);
        addOnlineCount();
        System.out.println("New connection: " + userId + ", current online count: " + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        if (webSocketSession.containsKey(userId)) {
            webSocketSession.remove(userId);
            subOnlineCount();
            System.out.println("Connection closed: " + userId + ", current online count: " + getOnlineCount());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received message: " + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendMessageToUser(String user, String message) throws IOException {
        for (String key : webSocketSession.keySet()) {
            if (key.equals(user)) {
                webSocketSession.get(key).sendMessage(message);
            }
        }
    }

    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
        if (userId != null) {
            webSocketSession.get(userId).sendMessage(message);
        } else {
            for (String key : webSocketSession.keySet()) {
                webSocketSession.get(key).sendMessage(message);
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount.get();
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount.incrementAndGet();
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount.decrementAndGet();
    }
}
