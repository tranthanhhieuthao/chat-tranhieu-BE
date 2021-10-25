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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddFriendRepository addFriendRepository;

    @Autowired
    private ModelMapper mapper;

    public ResponseFormat login(LoginFormat loginFormat) {
        User user = userRepository.findByGmail(loginFormat.getGmail());
        if (user == null) {
            return ResponseFormat.simpleNotExits();
        }
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return ResponseFormat.simpleSuccess(userDTO);
    }

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

    public ResponseFormat findFriends(Long idLogin, Integer page, Integer size) {
        Page<FriendDTO> friendDTO =  null;
        if (page == null || size == null) {
            friendDTO = userRepository.findFriendsById(idLogin, Pageable.unpaged());
        }
        friendDTO = userRepository.findFriendsById(idLogin, PageRequest.of(--page, size));
        if (friendDTO.isEmpty()) {
            return ResponseFormat.simpleNotExits();
        }
        return ResponseFormat.simpleSuccess(friendDTO);
    }
}
