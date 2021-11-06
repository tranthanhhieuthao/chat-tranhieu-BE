package com.chat.chat_with_friend.Service;

import com.chat.chat_with_friend.Common.Const;
import com.chat.chat_with_friend.DTO.FriendDTO;
import com.chat.chat_with_friend.DTO.UserDTO;
import com.chat.chat_with_friend.Model.AddFriend;
import com.chat.chat_with_friend.Model.User;
import com.chat.chat_with_friend.Repository.AddFriendRepository;
import com.chat.chat_with_friend.Repository.UserRepository;
import com.chat.chat_with_friend.Response.LoginFormat;
import com.chat.chat_with_friend.Response.ResponseFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddFriendRepository addFriendRepository;

    @Autowired
    private ModelMapper mapper;

    public ResponseFormat login(LoginFormat loginFormat) {
        User user = userRepository.findByUsername(loginFormat.getUsername());
        if (user == null) {
            return ResponseFormat.simpleNotExits();
        }
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return ResponseFormat.simpleSuccess(userDTO);
    }

    public ResponseFormat createUser(UserDTO userDTO) {
        User userEmail = userRepository.findByGmail(userDTO.getGmail());
        User userName = userRepository.findByUsername(userDTO.getUsername());
        if (userEmail != null) {
            return ResponseFormat.commonResponse("Duplicate Email", null, ResponseFormat.statusResponse.FAIL.toString());
        }
        if (userName != null) {
            return ResponseFormat.commonResponse("Duplicate Username", null, ResponseFormat.statusResponse.FAIL.toString());
        }
        if (userDTO == null) {
            return ResponseFormat.simpleNotExits();
        }
        User user = mapper.map(userDTO, User.class);
        userRepository.save(user);
        return ResponseFormat.simpleSuccess(null);
    }

    public ResponseFormat addFriend(String userLoginStr, String userFriendStr) {
        AddFriend addFriend = new AddFriend();
        addFriend.setStatus(Const.NEW_FRIEND);
        User userLogin = userRepository.findByUsername(userLoginStr);
        User userFriend = userRepository.findByUsername(userFriendStr);
        if (userLogin == null || userFriend == null) {
            return ResponseFormat.simpleNotExits();
        }
        addFriend.setUser(userLogin);
        addFriend.setUserFriend(userFriend);
        addFriendRepository.save(addFriend);
        return ResponseFormat.simpleSuccess(null);
    }

    public ResponseFormat findFriends(String username, Integer page, Integer size) {
        Page<FriendDTO> friendDTO =  null;
        if (page == null || size == null) {
            friendDTO = userRepository.findFriendsByUsername(username, Pageable.unpaged());
        } else {
            friendDTO = userRepository.findFriendsByUsername(username, PageRequest.of(--page, size));
        }
        if (friendDTO.isEmpty()) {
            return ResponseFormat.simpleNotExits();
        }
        return ResponseFormat.simpleSuccess(friendDTO);
    }

    public ResponseFormat searchUsers(String username, Integer page, Integer size) {
        Page<UserDTO> userDTO = null;
        if (page == null || size == null) {
            userDTO = userRepository.findUsersByUsername(username, Pageable.unpaged());
        } else {
            userDTO = userRepository.findUsersByUsername(username, PageRequest.of(--page, size));
        }
        if (userDTO == null) {
            return ResponseFormat.simpleNotExits();
        }
        return ResponseFormat.simpleSuccess(userDTO);
    }

    public ResponseFormat searchFriend(String usernameLogin, String username, Integer page, Integer size) {
        Page<UserDTO> userDTO = null;
        if (page == null || size == null) {
            userDTO = userRepository.findFriendByUsername(usernameLogin,username, Pageable.unpaged());
        } else {
            userDTO = userRepository.findFriendByUsername(usernameLogin,username, PageRequest.of(--page, size));
        }
        if (userDTO == null) {
            return ResponseFormat.simpleNotExits();
        }
        return ResponseFormat.simpleSuccess(userDTO);
    }
}
