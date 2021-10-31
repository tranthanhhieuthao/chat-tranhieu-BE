package com.chat.chat_with_friend.Repository;

import com.chat.chat_with_friend.DTO.CommentChatDTO;
import com.chat.chat_with_friend.Model.CommentChat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentChatRepository extends JpaRepository<CommentChat, Long> {

    @Query(value="select new com.chat.chat_with_friend.DTO.CommentChatDTO( user.username, cmt.comment, cmt.status, cmt.countStatus,groupChat.id, user.id) " +
            "from CommentChat  cmt inner join cmt.groupChat groupChat inner join cmt.user user " +
            "where groupChat.id = :idGroup " +
            "order by cmt.id asc ",
            countQuery="select count(cmt.id) " +
                    "from CommentChat  cmt inner join cmt.groupChat groupChat inner join cmt.user user " +
                    "where groupChat.id = :idGroup")
    Page<CommentChatDTO> findCommentChatByIDGroupChat(@Param("idGroup") Long idGroup, Pageable pageable);
}
