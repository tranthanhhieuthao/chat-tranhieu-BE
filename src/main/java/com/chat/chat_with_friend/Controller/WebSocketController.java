package com.chat.chat_with_friend.Controller;

import com.chat.chat_with_friend.DTO.CommentChatDTO;
import com.chat.chat_with_friend.Model.CommentChat;
import com.chat.chat_with_friend.Service.CommentService;
import com.chat.chat_with_friend.Service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebSocketController {

    @Autowired
    private GroupChatService groupChatService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping({"/chat.sendMessage/{idGroupChat}"})
    public void sendMessage(@Payload CommentChatDTO commentChatDTO, @DestinationVariable String idGroupChat)  {
        CommentChat cmt = commentService.addCommentIntoGroupChat(commentChatDTO,Long.valueOf(idGroupChat));
        commentChatDTO.setId(cmt.getId());
        commentChatDTO.setTimeCreate(cmt.getTimeCreate());
        if(commentChatDTO.getType().equals("CHAT")) {
            simpMessagingTemplate.convertAndSend("/topic/" + idGroupChat, commentChatDTO);
        }
    }

    @MessageMapping("/chat.addUser/{idGroupChat}")
    public void addUser(@Payload CommentChatDTO commentChatDTO, @DestinationVariable("idGroupChat") String idGroupChat) {
      String nameJoin = groupChatService.addUserIntoGroupChat(commentChatDTO.getIdUser(),Long.valueOf(idGroupChat));
        commentChatDTO.setUsernameJoin(nameJoin);
        if(commentChatDTO.getType().equals("JOIN")) {
            simpMessagingTemplate.convertAndSend("/topic/" + idGroupChat, commentChatDTO);
        }
    }

    @MessageMapping("/group.addUser")
    public void listGroupRefresh(@Payload CommentChatDTO commentChatDTO) {
        if(commentChatDTO.getType().equals("ADD_GROUP")) {
            simpMessagingTemplate.convertAndSend("/topic/group" , commentChatDTO);
        }
    }

}
