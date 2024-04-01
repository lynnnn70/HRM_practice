package com.example.HRM_practice.response;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.common.ErrorMessage;
import com.example.HRM_practice.common.StatusCode;

public class ResetPasswordResponse extends CommonResponse<ResetPasswordResponse, Void> {

    public ResetPasswordResponse(StatusCode statusCode, String errorMessage){
        setStatus(statusCode.getValue());
        setErrorMessage(errorMessage);
    }

    public ResetPasswordResponse(StatusCode statusCode){
        this(statusCode, ErrorMessage.convertStatus2Message(statusCode));
    }

    public ResetPasswordResponse() {

    }
}
