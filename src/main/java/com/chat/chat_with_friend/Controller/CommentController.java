package com.chat.chat_with_friend.Controller;

import com.chat.chat_with_friend.DTO.CommentChatDTO;
import com.chat.chat_with_friend.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/updateCommentReact")
    public ResponseEntity updateCommentReact(@RequestBody CommentChatDTO commentChatDTO) {
        return ResponseEntity.ok(commentService.updateCommentReact(commentChatDTO));
    }
}
