package com.chat.chat_with_friend.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private Logger LOG = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebsocketConnectListener(SessionConnectedEvent connectedEvent) {
        LOG.info("received a new socket connection");
    }

    @EventListener
    public void handleWebsocketDisconnectListener(SessionDisconnectEvent connectedEvent) {
        LOG.info("socket Disconnection");
    }
}
