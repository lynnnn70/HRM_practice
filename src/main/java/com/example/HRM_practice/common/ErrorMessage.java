package com.example.HRM_practice.common;

public class ErrorMessage {

    public static String convertStatus2Message(StatusCode statusCode){
        switch (statusCode){
            case OK:
                return "success";
            case InvalidData:
                return "Invalid_Input";
            case InternalError:
                return "Internal_Error";
            case AccountUnavailable:
                return "Account_Unavailable";
            case Duplicate:
                return "Duplicate";
            default:
                return "Unknown_Status";
        }
    }
}
