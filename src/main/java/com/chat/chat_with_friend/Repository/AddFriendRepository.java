package com.chat.chat_with_friend.Repository;

import com.chat.chat_with_friend.Model.AddFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddFriendRepository extends JpaRepository<AddFriend, Long> {
}
