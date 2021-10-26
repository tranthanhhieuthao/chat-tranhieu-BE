package com.chat.chat_with_friend.Repository;

import com.chat.chat_with_friend.DTO.GroupChatDTO;
import com.chat.chat_with_friend.Model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {

    @Query(value="SELECT new com.chat.chat_with_friend.DTO.GroupChatDTO(groupChat.id, groupChat.limitJoinGroup, groupChat.nameGroup, groupChat.typeGroup) " +
            "FROM GroupChat groupChat inner join groupChat.users user " +
            "WHERE user.id = :id")
    List<GroupChatDTO> findGroupChatByUserId(Long id);
}
