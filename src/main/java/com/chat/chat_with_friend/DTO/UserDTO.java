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
    private String username;
    private String phoneNumber;
    private String status;
    private String gmail;
    private String sex;
    private String password;

    public UserDTO(Long id, String fullName, String username, String phoneNumber,String status, String gmail, String sex) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.gmail = gmail;
        this.sex = sex;
    }
}
