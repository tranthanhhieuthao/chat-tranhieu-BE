package com.chat.chat_with_friend.Service;

import com.chat.chat_with_friend.DTO.CommentChatDTO;
import com.chat.chat_with_friend.Model.CommentChat;
import com.chat.chat_with_friend.Model.GroupChat;
import com.chat.chat_with_friend.Model.User;
import com.chat.chat_with_friend.Repository.CommentChatRepository;
import com.chat.chat_with_friend.Repository.GroupChatRepository;
import com.chat.chat_with_friend.Repository.UserRepository;
import com.chat.chat_with_friend.Response.ResponseFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentChatRepository commentChatRepository;

    @Autowired
    private GroupChatRepository groupChatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public CommentChat addCommentIntoGroupChat(CommentChatDTO commentChatDTO, Long idGroupChat) {
        CommentChat commentChat = new CommentChat();
        User user = userRepository.findByUsername(commentChatDTO.getSender());
        GroupChat groupChat = groupChatRepository.getById(idGroupChat);
        if (user == null || groupChat == null) {
            return null;
        }
        commentChat.setComment(commentChatDTO.getComment());
        commentChat.setCountStatus(commentChatDTO.getCountStatus());
        commentChat.setStatus(commentChatDTO.getStatus());
        commentChat.setGroupChat(groupChat);
        commentChat.setUser(user);
        commentChatRepository.save(commentChat);
        return commentChat;
    }

    public ResponseFormat updateCommentReact(CommentChatDTO commentChatDTO) {
         CommentChat commentChat = commentChatRepository.getById(commentChatDTO.getId());
         if (commentChat == null) {
             return ResponseFormat.simpleNotExits();
         }
         commentChat.setStatus(commentChatDTO.getStatus());
         commentChat.setCountStatus(commentChatDTO.getCountStatus());
         commentChatRepository.save(commentChat);
         return ResponseFormat.simpleSuccess(null);
    }

}
