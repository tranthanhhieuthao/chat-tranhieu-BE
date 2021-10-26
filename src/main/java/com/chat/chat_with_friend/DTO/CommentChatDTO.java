package com.chat.chat_with_friend.DTO;

import com.chat.chat_with_friend.Model.GroupChat;
import com.chat.chat_with_friend.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentChatDTO {

    private String comment;
    // like, heart, simile
    private String status;
    private Long countStatus;
    private Long idGroupChat;
    private Long idUser;
}
