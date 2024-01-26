package com.example.HRM_practice.response;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.common.ErrorMessage;
import com.example.HRM_practice.common.StatusCode;

public class RolesResponse extends CommonResponse<RolesResponse, Void> {

    public RolesResponse(StatusCode statusCode, String errorCode){
        setStatus(statusCode.getValue());
        setErrorMessage(errorCode);
    }

    public RolesResponse(StatusCode statusCode){
        this(statusCode, ErrorMessage.convertStatus2Message(statusCode));
    }

    public RolesResponse() {

    }
}
