package com.chat.chat_with_friend.Controller;

import com.chat.chat_with_friend.DTO.UserDTO;
import com.chat.chat_with_friend.Response.LoginFormat;
import com.chat.chat_with_friend.Response.ResponseFormat;
import com.chat.chat_with_friend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginFormat loginFormat) {
        ResponseFormat responseFormat = userService.login(loginFormat);
        return ResponseEntity.ok(responseFormat);
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
        ResponseFormat responseFormat = userService.createUser(userDTO);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/addFriend")
    public ResponseEntity addFriend(@RequestParam Long idLogin, @RequestParam Long idFriend) {
        ResponseFormat responseFormat = userService.addFriend(idLogin, idFriend);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/friends")
    public ResponseEntity findFriend(@RequestParam Long idLogin, @RequestParam Integer page, @RequestParam Integer size) {
        ResponseFormat responseFormat = userService.findFriends(idLogin, page, size);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/searchUser")
    public ResponseEntity searchUsers(@RequestParam String username) {
        ResponseFormat responseFormat = userService.searchUsers(username);
        return ResponseEntity.ok(responseFormat);
    }
}
