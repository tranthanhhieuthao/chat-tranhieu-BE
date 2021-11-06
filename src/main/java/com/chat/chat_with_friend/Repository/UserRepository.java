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

    @Query(value="select " +
            "new com.chat.chat_with_friend.DTO.FriendDTO( userFriend.id ," +
            " case userLogin.username when :username then userFriend.fullName else userLogin.fullName end, " +
            "case userLogin.username when :username then userFriend.username else userLogin.username end," +
            "case userLogin.username when :username then userFriend.phoneNumber else userLogin.phoneNumber end, " +
            "addFriend.status, " +
            "case userLogin.username when :username then userFriend.status else userLogin.status end) " +
            "from AddFriend addFriend inner join addFriend.userFriend userFriend inner join addFriend.user userLogin " +
            "where userLogin.username = :username or userFriend.username = :username ",
            countQuery  = "select count(userFriend.id) " +
                    "from AddFriend addFriend inner join addFriend.userFriend userFriend inner join addFriend.user userLogin " +
                    "where userLogin.username = :username or userFriend.username = :username ")
    Page<FriendDTO> findFriendsByUsername(@Param("username") String username, Pageable pageable);

    User findByGmail(String gmail);
    User findByUsername(String username);

    @Query(value="select new com.chat.chat_with_friend.DTO.UserDTO(user.id, user.fullName, user.username,user.phoneNumber,user.status,user.gmail, user.sex) from User user " +
            "where user.username like %:username% or user.gmail like %:username% or user.fullName like %:username%",
            countQuery  = "select count(user.id) " +
                    "from User user " +
                    "where user.username like %:username% or user.gmail like %:username% or user.fullName like %:username%")
    Page<UserDTO> findUsersByUsername(@Param("username") String username, Pageable pageable);

    @Query(value="select new com.chat.chat_with_friend.DTO.UserDTO(user.id, user.fullName, user.username,user.phoneNumber,user.status,user.gmail, user.sex) from AddFriend addFr " +
            "inner join addFr.user user inner join addFr.userFriend userFr where user.username = :usernameLogin and userFr.username like %:username% ",
            countQuery  = "select count(user.id) " +
                    "from AddFriend addFr " +
                    "inner join addFr.user user inner join addFr.userFriend userFr where user.username = :usernameLogin and userFr.username like %:username%")
    Page<UserDTO> findFriendByUsername(@Param("usernameLogin") String usernameLogin, @Param("username") String username, Pageable pageable);
}
