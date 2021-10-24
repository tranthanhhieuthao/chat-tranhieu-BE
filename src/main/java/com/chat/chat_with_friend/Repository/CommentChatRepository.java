package com.chat.chat_with_friend.Repository;

import com.chat.chat_with_friend.Model.CommentChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentChatRepository extends JpaRepository<CommentChat, Long> {
}
