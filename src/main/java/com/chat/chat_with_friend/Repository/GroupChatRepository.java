package com.chat.chat_with_friend.Repository;

import com.chat.chat_with_friend.Model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {
}
