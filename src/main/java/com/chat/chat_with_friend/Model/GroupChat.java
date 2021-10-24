package com.chat.chat_with_friend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_chat")
public class GroupChat extends BaseClass {

    @Column(name = "limit_join_group")
    private Long limitJoinGroup;

    private String nameGroup;
    // single, double, many
    private String typeGroup;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group_chat")
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<CommentChat> commentChats;
}
