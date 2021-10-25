package com.chat.chat_with_friend.Service;

import com.chat.chat_with_friend.Repository.GroupChatRepository;
import com.chat.chat_with_friend.Response.ResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupChatService {

    @Autowired
    private GroupChatRepository groupChatRepository;

    public ResponseFormat createGroupChat() {
        return ResponseFormat.simpleSuccess(null);
    }
}
