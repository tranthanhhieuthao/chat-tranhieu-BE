package com.chat.chat_with_friend.Controller;

import com.chat.chat_with_friend.DTO.UserDTO;
import com.chat.chat_with_friend.Response.LoginFormat;
import com.chat.chat_with_friend.Response.ResponseFormat;
import com.chat.chat_with_friend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public ResponseEntity addFriend(@RequestParam String userLogin, @RequestParam String userFriend) {
        ResponseFormat responseFormat = userService.addFriend(userLogin, userFriend);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/friends")
    public ResponseEntity findFriend(@RequestParam String username, @RequestParam Integer page, @RequestParam Integer size) {
        ResponseFormat responseFormat = userService.findFriends(username, page, size);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/searchUser")
    public ResponseEntity searchUsers(@RequestParam String username, @RequestParam Integer page, @RequestParam Integer size) {
        ResponseFormat responseFormat = userService.searchUsers(username, page, size);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/searchFriend")
    public ResponseEntity searchFriend(@RequestParam String usernameLogin, @RequestParam String username, @RequestParam Integer page, @RequestParam Integer size) {
        ResponseFormat responseFormat = userService.searchFriend(usernameLogin, username, page, size);
        return ResponseEntity.ok(responseFormat);
    }
}
