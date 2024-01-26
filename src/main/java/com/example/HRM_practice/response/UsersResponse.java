package com.example.HRM_practice.response;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.common.ErrorMessage;
import com.example.HRM_practice.common.StatusCode;

public class UsersResponse extends CommonResponse<UsersResponse,Void> {


    public UsersResponse(StatusCode statusCode, String message){
        setStatus(statusCode.getValue());
        setErrorMessage(message);
    }

    public UsersResponse(StatusCode statusCode) {
        this(statusCode, ErrorMessage.convertStatus2Message(statusCode));

    }

    public UsersResponse() {

    }
}
