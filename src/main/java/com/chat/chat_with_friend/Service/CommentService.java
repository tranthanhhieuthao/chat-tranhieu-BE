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

    public ResponseFormat addCommentIntoGroupChat(CommentChatDTO commentChatDTO) {
        CommentChat commentChat = new CommentChat();
        User user = userRepository.getById(commentChatDTO.getIdUser());
        GroupChat groupChat = groupChatRepository.getById(commentChatDTO.getIdGroupChat());
        if (user == null || groupChat == null) {
            return ResponseFormat.simpleNotExits();
        }
        commentChat.setComment(commentChatDTO.getComment());
        commentChat.setCountStatus(commentChatDTO.getCountStatus());
        commentChat.setStatus(commentChatDTO.getStatus());
        commentChat.setGroupChat(groupChat);
        commentChat.setUser(user);
        return ResponseFormat.simpleSuccess(commentChat);
    }

}
