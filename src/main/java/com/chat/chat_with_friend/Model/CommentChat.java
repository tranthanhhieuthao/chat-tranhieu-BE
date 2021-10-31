package com.chat.chat_with_friend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment_chat")
public class CommentChat extends BaseClass {

    private String comment;
    // like, heart, simile
    private String status;

    @Column(name = "count_status")
    private Long countStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_chat_id")
    private GroupChat groupChat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
