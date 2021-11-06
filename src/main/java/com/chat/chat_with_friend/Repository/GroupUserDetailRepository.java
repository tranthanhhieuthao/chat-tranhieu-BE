package com.chat.chat_with_friend.Repository;

import com.chat.chat_with_friend.Model.GroupUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserDetailRepository extends JpaRepository<GroupUserDetail, Long> {

    GroupUserDetail findGroupUserDetailByGroupChat_IdAndUserId(Long groupChat_Id, Long userId);
}
