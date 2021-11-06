package com.chat.chat_with_friend.Service;

import com.chat.chat_with_friend.DTO.CommentChatDTO;
import com.chat.chat_with_friend.DTO.GroupChatDTO;
import com.chat.chat_with_friend.DTO.UserDTO;
import com.chat.chat_with_friend.Model.CommentChat;
import com.chat.chat_with_friend.Model.GroupChat;
import com.chat.chat_with_friend.Model.GroupUserDetail;
import com.chat.chat_with_friend.Model.User;
import com.chat.chat_with_friend.Repository.CommentChatRepository;
import com.chat.chat_with_friend.Repository.GroupChatRepository;
import com.chat.chat_with_friend.Repository.GroupUserDetailRepository;
import com.chat.chat_with_friend.Repository.UserRepository;
import com.chat.chat_with_friend.Response.ResponseFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupChatService {

    @Autowired
    private GroupChatRepository groupChatRepository;

    @Autowired
    private GroupUserDetailRepository groupUserDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentChatRepository commentChatRepository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public ResponseFormat createGroupChat(GroupChatDTO groupChatDTO) {
        User user = userRepository.findByUsername(groupChatDTO.getUserCreate());
        GroupChat groupChat = mapper.map(groupChatDTO, GroupChat.class);
        if (groupChat == null || user == null) {
            return ResponseFormat.simpleNotExits();
        }
        groupChat.setUserCreate(user);
        groupChat = groupChatRepository.saveAndFlush(groupChat);
        GroupUserDetail groupUserDetail = new GroupUserDetail();
        groupUserDetail.setGroupChat(groupChat);
        groupUserDetail.setUser(user);
        groupUserDetailRepository.save(groupUserDetail);
        return ResponseFormat.simpleSuccess(null);
    }

    public ResponseFormat findGroupChatByUser(String username) {
        List<GroupChatDTO> groupChatDTO = groupChatRepository.findGroupChatByUsername(username);
        if (groupChatDTO.isEmpty()) {
            return ResponseFormat.simpleNotExits();
        }
        return ResponseFormat.simpleSuccess(groupChatDTO);
    }

    @Transactional
    public String addUserIntoGroupChat(Long idUser, Long idGroupChat) {
        GroupUserDetail groupUserDetail = new GroupUserDetail();
        User user = userRepository.getById(idUser);
        GroupChat groupChat = groupChatRepository.getById(idGroupChat);
        GroupUserDetail groupUserDetail1 = groupUserDetailRepository.findGroupUserDetailByGroupChat_IdAndUserId(idGroupChat, idUser);
        if (user == null || groupChat == null || groupUserDetail1 != null) {
            return null;
        }
        groupUserDetail.setGroupChat(groupChat);
        groupUserDetail.setUser(user);
        groupUserDetailRepository.save(groupUserDetail);
        return user.getUsername();
    }

    public ResponseFormat findUserInGroupChat(Long idGroupChat) {
        List<UserDTO> userDTOS = groupChatRepository.findUsersInGroup(idGroupChat);
        if (userDTOS.isEmpty()) {
            return ResponseFormat.simpleNotExits();
        }
        return ResponseFormat.simpleSuccess(userDTOS);
    }

    public ResponseFormat commentsInGroup(Long idGroupChat, Integer page, Integer size) {
        Page<CommentChatDTO> commentChatDTOS = null;
        if (size == null || page == null) {
            commentChatDTOS = commentChatRepository.findCommentChatByIDGroupChat(idGroupChat, Pageable.unpaged());
        } else {
            commentChatDTOS = commentChatRepository.findCommentChatByIDGroupChat(idGroupChat, PageRequest.of(--page, size));
        }
        if (commentChatDTOS.isEmpty()) {
            return ResponseFormat.simpleNotExits();
        }
        return ResponseFormat.simpleSuccess(commentChatDTOS);
    }
}
