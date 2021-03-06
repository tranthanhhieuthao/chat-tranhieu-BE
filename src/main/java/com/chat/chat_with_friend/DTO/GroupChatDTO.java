package com.chat.chat_with_friend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatDTO {

    private Long id;
    private Long limitJoinGroup;

    private String nameGroup;
    // single, double, many
    private String typeGroup;
    private String userCreate;

    public GroupChatDTO(Long id, Long limitJoinGroup, String nameGroup, String typeGroup) {
        this.id = id;
        this.limitJoinGroup = limitJoinGroup;
        this.nameGroup = nameGroup;
        this.typeGroup = typeGroup;
    }


}
