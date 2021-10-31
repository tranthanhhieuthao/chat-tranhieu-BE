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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userCreate;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<GroupUserDetail> GroupUsers;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<CommentChat> commentChats;
}
