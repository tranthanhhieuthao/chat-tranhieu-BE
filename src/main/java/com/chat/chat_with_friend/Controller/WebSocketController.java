package com.chat.chat_with_friend.Controller;

import com.chat.chat_with_friend.Model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;

@Controller
public class WebSocketController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/{idGroupChat}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @PathParam("idGroupChat") String idGroupChat) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/{idGroupChat}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor,  @PathParam("idGroupChat") String idGroupChat) {
        // Add username in web socket session
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
