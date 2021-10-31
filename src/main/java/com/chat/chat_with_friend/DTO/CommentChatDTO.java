package com.chat.chat_with_friend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentChatDTO implements Serializable {

    private String sender;
    private String comment;
    // like, heart, simile
    private String status;
    private Long countStatus;
    private Long idGroupChat;
    private Long idUser;
    // chat, join , leave
    private String type;

    private String usernameJoin;

    public CommentChatDTO( String sender, String comment, String status, Long countStatus, Long idGroupChat, Long idUser) {
        this.sender = sender;
        this.comment = comment;
        this.status = status;
        this.countStatus = countStatus;
        this.idGroupChat = idGroupChat;
        this.idUser = idUser;
    }

}
