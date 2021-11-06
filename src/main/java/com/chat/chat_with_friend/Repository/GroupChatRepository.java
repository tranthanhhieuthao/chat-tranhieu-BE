package com.chat.chat_with_friend.Repository;

import com.chat.chat_with_friend.DTO.GroupChatDTO;
import com.chat.chat_with_friend.DTO.UserDTO;
import com.chat.chat_with_friend.Model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {

    @Query(value="SELECT new com.chat.chat_with_friend.DTO.GroupChatDTO(groupChat.id, groupChat.limitJoinGroup, groupChat.nameGroup, groupChat.typeGroup, groupChat.userCreate.username) " +
            "FROM GroupUserDetail grUser inner join grUser.groupChat groupChat inner join grUser.user usercr " +
            "WHERE usercr.username = :username order by groupChat.id asc ")
    List<GroupChatDTO> findGroupChatByUsername(String username);

    @Query(value="select new com.chat.chat_with_friend.DTO.UserDTO(user.id, user.fullName, user.username,user.phoneNumber,user.status,user.gmail, user.sex) from GroupUserDetail grpUser " +
            "inner join grpUser.user user inner join grpUser.groupChat grp " +
            "where grp.id = :id")
    List<UserDTO> findUsersInGroup(Long id);
}
