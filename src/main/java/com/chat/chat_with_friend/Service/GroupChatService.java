package com.chat.chat_with_friend.Service;

import com.chat.chat_with_friend.DTO.GroupChatDTO;
import com.chat.chat_with_friend.DTO.UserDTO;
import com.chat.chat_with_friend.Model.GroupChat;
import com.chat.chat_with_friend.Model.GroupUserDetail;
import com.chat.chat_with_friend.Model.User;
import com.chat.chat_with_friend.Repository.GroupChatRepository;
import com.chat.chat_with_friend.Repository.GroupUserDetailRepository;
import com.chat.chat_with_friend.Repository.UserRepository;
import com.chat.chat_with_friend.Response.ResponseFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseFormat addUserIntoGroupChat(Long idUser, Long idGroupChat) {
        GroupUserDetail groupUserDetail = new GroupUserDetail();
        User user = userRepository.getById(idUser);
        GroupChat groupChat = groupChatRepository.getById(idGroupChat);
        if (user == null || groupChat == null) {
            return ResponseFormat.simpleNotExits();
        }
        groupUserDetail.setGroupChat(groupChat);
        groupUserDetail.setUser(user);
        groupUserDetailRepository.save(groupUserDetail);
        return ResponseFormat.simpleSuccess(null);
    }

    public ResponseFormat findUserInGroupChat(Long idGroupChat) {
        List<UserDTO> userDTOS = groupChatRepository.findUsersInGroup(idGroupChat);
        if (userDTOS.isEmpty()) {
            return ResponseFormat.simpleNotExits();
        }
        return ResponseFormat.simpleSuccess(userDTOS);
    }
}
