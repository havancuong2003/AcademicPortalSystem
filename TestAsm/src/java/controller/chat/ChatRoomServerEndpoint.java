package controller.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chatRoomServer/{groupid}")
public class ChatRoomServerEndpoint {

    private static Map<String, Set<Session>> chatGroups = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("groupid") String groupId) {
        chatGroups.computeIfAbsent(groupId, k -> Collections.synchronizedSet(new HashSet<>())).add(session);
        try {
            session.getBasicRemote().sendText("Server connected...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void handleMessage(String message, Session userSession, @PathParam("groupid") String groupId) throws IOException {
        
        String username = (String) userSession.getUserProperties().get("username");
        if (username == null) {
            userSession.getUserProperties().put("username", message);
            userSession.getBasicRemote().sendText("System: you are connected as " + message);
        } else {
            Set<Session> groupSessions = chatGroups.get(groupId);
            if (groupSessions != null) {
                for (Session session : groupSessions) {
                    session.getBasicRemote().sendText(username + ": " + message);
                }
            }
        }
    }

    @OnClose
    public void handleClose(Session session, @PathParam("groupid") String groupId) {
        Set<Session> groupSessions = chatGroups.get(groupId);
        if (groupSessions != null) {
            groupSessions.remove(session);
            if (groupSessions.isEmpty()) {
                chatGroups.remove(groupId);
            }
        }
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
}
