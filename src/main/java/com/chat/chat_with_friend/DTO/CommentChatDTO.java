package com.chat.chat_with_friend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentChatDTO implements Serializable {

    private Long id;
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
    private Date timeCreate;

    public CommentChatDTO( Long id,String sender, String comment, String status, Long countStatus, Long idGroupChat, Long idUser, Date timeCreate) {
        this.id = id;
        this.sender = sender;
        this.comment = comment;
        this.status = status;
        this.countStatus = countStatus;
        this.idGroupChat = idGroupChat;
        this.idUser = idUser;
        this.timeCreate = timeCreate;
    }


}
