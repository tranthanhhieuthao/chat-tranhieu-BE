package com.chat.chat_with_friend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_chat")
public class User extends BaseClass {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name")
    private String username;

    @Column(name = "sex")
    private String sex;

    @Column(name = "gmail")
    private String gmail;

    private String password;

    @Lob
    @Column(name = "avatar", length = Integer.MAX_VALUE)
    private byte[] avatar;

    @Column(name = "phone_number")
    private String phoneNumber;
    // online, offline, busy, away

    @Column(name = "status")
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<AddFriend> friends;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<CommentChat> commentChats;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<GroupUserDetail> GroupUsers;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<GroupChat> hostGroupChats;



}
