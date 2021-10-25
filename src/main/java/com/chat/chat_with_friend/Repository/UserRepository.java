package com.chat.chat_with_friend.Repository;

import com.chat.chat_with_friend.DTO.FriendDTO;
import com.chat.chat_with_friend.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value="select new com.chat.chat_with_friend.DTO.FriendDTO(userFriend.id, userFriend.fullName, userFriend.userName,userFriend.phoneNumber, addFriend.status, userFriend.status) " +
            "from AddFriend addFriend inner join addFriend.userFriend userFriend inner join addFriend.user userLogin " +
            "where userLogin.id = :id",
            countQuery  = "select userFriend.id " +
                    "from AddFriend addFriend inner join addFriend.userFriend userFriend inner join addFriend.user userLogin " +
                    "where userLogin.id = :id")
    Page<FriendDTO> findFriendsById(@Param("id") Long id, Pageable pageable);

    User findByGmail(String gmail);
}
