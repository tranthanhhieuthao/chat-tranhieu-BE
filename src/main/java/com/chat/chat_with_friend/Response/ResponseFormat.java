package com.chat.chat_with_friend.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFormat {

    private String message;
    private Object content;
    private String status;

      public static enum statusResponse {
        FAIL, SUCCESS, BAD_REQUEST, NOT_EXITS
    }

    public static ResponseFormat simpleSuccess(Object obj) {
         return new ResponseFormat("", obj, statusResponse.SUCCESS.toString());
    }

    public static ResponseFormat commonResponse(String message, Object obj, String status) {
         return new ResponseFormat(message, obj, status);
    }

    public static ResponseFormat simpleFail(Object obj) {
        return new ResponseFormat("", obj, statusResponse.FAIL.toString());
    }

    public static ResponseFormat simpleNotExits() {
        return new ResponseFormat("", null, statusResponse.NOT_EXITS.toString());
    }

    public static ResponseFormat simpleBadRequest(Object obj) {
        return new ResponseFormat("", obj, statusResponse.BAD_REQUEST.toString());
    }

}
