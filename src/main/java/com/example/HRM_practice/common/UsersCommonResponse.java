package com.example.HRM_practice.common;

public class UsersCommonResponse extends CommonResponse<UsersCommonResponse,Void>{


    public UsersCommonResponse(StatusCode statusCode, String message){
        setStatus(statusCode.getValue());
        setErrorMessage(message);
    }

    public UsersCommonResponse(StatusCode statusCode) {
        this(statusCode, ErrorMessage.convertStatus2Message(statusCode));

    }
}
