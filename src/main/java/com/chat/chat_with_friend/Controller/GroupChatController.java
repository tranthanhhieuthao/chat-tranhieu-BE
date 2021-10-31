package com.chat.chat_with_friend.Controller;

import com.chat.chat_with_friend.DTO.GroupChatDTO;
import com.chat.chat_with_friend.Response.LoginFormat;
import com.chat.chat_with_friend.Response.ResponseFormat;
import com.chat.chat_with_friend.Service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class GroupChatController {

    @Autowired
    private GroupChatService groupChatService;

    @PostMapping("/createGroup")
    public ResponseEntity createGroupChat(@RequestBody GroupChatDTO groupChatDTO) {
        ResponseFormat responseFormat = groupChatService.createGroupChat(groupChatDTO);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/groups")
    public ResponseEntity findGroupChatByUser(@RequestParam String username) {
        ResponseFormat responseFormat = groupChatService.findGroupChatByUser(username);
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/usersInGroup")
    public ResponseEntity usersInGroup(@RequestParam Long idGroup) {
        ResponseFormat responseFormat = groupChatService.findUserInGroupChat(idGroup);
        return ResponseEntity.ok(responseFormat);
    }

}
