package com.example.HRM_practice.response;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.common.ErrorMessage;
import com.example.HRM_practice.common.StatusCode;

public class DepartmentResponse extends CommonResponse<DepartmentResponse, Void> {

    public DepartmentResponse(StatusCode statusCode, String errorMessage){
        setStatus(statusCode.getValue());
        setErrorMessage(errorMessage);
    }

    public DepartmentResponse(StatusCode statusCode){
        this(statusCode, ErrorMessage.convertStatus2Message(statusCode));
    }

    public DepartmentResponse(){}

}
