package com.chat.chat_with_friend.Service;

import com.chat.chat_with_friend.Common.Const;
import com.chat.chat_with_friend.DTO.UserDTO;
import com.chat.chat_with_friend.Model.AddFriend;
import com.chat.chat_with_friend.Model.User;
import com.chat.chat_with_friend.Repository.AddFriendRepository;
import com.chat.chat_with_friend.Repository.UserRepository;
import com.chat.chat_with_friend.Response.ResponseFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddFriendRepository addFriendRepository;

    @Autowired
    private ModelMapper mapper;

    public ResponseFormat createUser(UserDTO userDTO) {
        if (userDTO == null) {
            return ResponseFormat.simpleNotExits();
        }
        User user = mapper.map(userDTO, User.class);
        userRepository.save(user);
        return ResponseFormat.simpleSuccess(null);
    }

    public ResponseFormat addFriend(Long idLogin, Long idFriend) {
        AddFriend addFriend = new AddFriend();
        addFriend.setStatus(Const.NEW_FRIEND);
        User userLogin = userRepository.getById(idLogin);
        User userFriend = userRepository.getById(idFriend);
        if (userLogin == null || userFriend == null) {
            return ResponseFormat.simpleNotExits();
        }
        addFriend.setUser(userLogin);
        addFriend.setUserFriend(userFriend);
        addFriendRepository.save(addFriend);
        return ResponseFormat.simpleSuccess(null);
    }

    public ResponseFormat findFriends(Long idLogin) {
        return ResponseFormat.simpleSuccess(null);
    }
}
