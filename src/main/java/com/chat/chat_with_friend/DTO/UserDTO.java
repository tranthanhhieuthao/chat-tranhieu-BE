package com.chat.chat_with_friend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String fullName;
    private String userName;
    private String phoneNumber;
    private String status;
    private String gmail;
    private String sex;
}
