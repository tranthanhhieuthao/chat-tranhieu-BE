package com.chat.chat_with_friend.Service;

import com.chat.chat_with_friend.DTO.CommentChatDTO;
import com.chat.chat_with_friend.DTO.GroupChatDTO;
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

    public ResponseFormat createGroupChat(GroupChatDTO groupChatDTO) {
        GroupChat groupChat = mapper.map(groupChatDTO, GroupChat.class);
        if (groupChat == null) {
            return ResponseFormat.simpleNotExits();
        }
        groupChatRepository.save(groupChat);
        return ResponseFormat.simpleSuccess(null);
    }

    public ResponseFormat findGroupChatByUser(Long id) {
        List<GroupChatDTO> groupChatDTO = groupChatRepository.findGroupChatByUserId(id);
        if (groupChatDTO.isEmpty()) {
            return ResponseFormat.simpleNotExits();
        }
        return ResponseFormat.simpleSuccess(groupChatDTO);
    }

    public ResponseFormat addUserIntoGroupChat(CommentChatDTO chatDTO) {
        GroupUserDetail groupUserDetail = new GroupUserDetail();
        User user = userRepository.getById(chatDTO.getIdUser());
        GroupChat groupChat = groupChatRepository.getById(chatDTO.getIdGroupChat());
        if (user == null || groupChat == null) {
            return ResponseFormat.simpleNotExits();
        }
        groupUserDetail.setGroupChat(groupChat);
        groupUserDetail.setUser(user);
        groupUserDetailRepository.save(groupUserDetail);
        return ResponseFormat.simpleSuccess(null);
    }
}
