package com.chat.chat_with_friend.Controller;

import com.chat.chat_with_friend.DTO.CommentChatDTO;
import com.chat.chat_with_friend.Response.ResponseFormat;
import com.chat.chat_with_friend.Service.CommentService;
import com.chat.chat_with_friend.Service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;

@Controller
public class WebSocketController {

    @Autowired
    private GroupChatService groupChatService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping({"/chat.sendMessage/{idGroupChat}"})
    @SendTo("/topic/{idGroupChat}")
    public ResponseEntity sendMessage(@Payload CommentChatDTO commentChatDTO, @DestinationVariable String idGroupChat) {
        simpMessagingTemplate.convertAndSend("/topic/" + idGroupChat, commentChatDTO);
        ResponseFormat responseFormat = commentService.addCommentIntoGroupChat(commentChatDTO,Long.valueOf(idGroupChat));
        return ResponseEntity.ok(responseFormat);
    }

    @MessageMapping("/chat.addUser/{idGroupChat}")
    @SendTo("/topic/{idGroupChat}")
    public ResponseEntity addUser(@Payload CommentChatDTO commentChatDTO, @DestinationVariable("idGroupChat") String idGroupChat) {
        simpMessagingTemplate.convertAndSend("/topic/" + idGroupChat, commentChatDTO);
       ResponseFormat responseFormat = groupChatService.addUserIntoGroupChat(commentChatDTO.getIdUser(),Long.valueOf(idGroupChat));
        return ResponseEntity.ok(responseFormat);
    }
}
