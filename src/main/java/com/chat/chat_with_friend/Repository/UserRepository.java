package com.chat.chat_with_friend.Repository;

import com.chat.chat_with_friend.DTO.FriendDTO;
import com.chat.chat_with_friend.DTO.UserDTO;
import com.chat.chat_with_friend.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value="select new com.chat.chat_with_friend.DTO.FriendDTO(userFriend.id, userFriend.fullName, userFriend.username,userFriend.phoneNumber, addFriend.status, userFriend.status) " +
            "from AddFriend addFriend inner join addFriend.userFriend userFriend inner join addFriend.user userLogin " +
            "where userLogin.id = :id",
            countQuery  = "select count(userFriend.id) " +
                    "from AddFriend addFriend inner join addFriend.userFriend userFriend inner join addFriend.user userLogin " +
                    "where userLogin.id = :id")
    Page<FriendDTO> findFriendsById(@Param("id") Long id, Pageable pageable);

    User findByGmail(String gmail);
    User findByUsername(String username);

    @Query(value="select new com.chat.chat_with_friend.DTO.UserDTO(user.id, user.fullName, user.username,user.phoneNumber,user.status,user.gmail, user.sex) from User user " +
            "where user.username like %:username% or user.gmail like %:username% or user.fullName like %:username%")
    List<UserDTO> findUsersByUsername(@Param("username") String username);
}
