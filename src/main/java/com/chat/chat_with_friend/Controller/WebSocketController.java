package com.chat.chat_with_friend.Controller;

import com.chat.chat_with_friend.Model.ChatMessage;
import com.chat.chat_with_friend.Response.ResponseFormat;
import com.chat.chat_with_friend.Service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;

@Controller
public class WebSocketController {

    @Autowired
    private GroupChatService groupChatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/{idGroupChat}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @PathParam("idGroupChat") String idGroupChat) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/{idGroupChat}/{idUser}")
    public ResponseEntity addUser(@Payload ChatMessage chatMessage,
                                  @PathParam("idGroupChat") Long idGroupChat, @PathParam("idUser") Long idUser) {
       ResponseFormat responseFormat = groupChatService.addUserIntoGroupChat(idUser,idGroupChat,chatMessage);
        return ResponseEntity.ok(responseFormat);
    }
}
