package com.example.HRM_practice.controller;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.common.StatusCode;
import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.service.ResetPasswordService;
import com.example.HRM_practice.service.serviceImpl.ResetPasswordServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordServiceImpl resetPasswordService;

    private static final Logger log = LoggerFactory.getLogger(ResetPasswordController.class);

    //先輸入舊密碼，驗證是這個人的沒錯，再請他輸入新密碼
    @PutMapping("resetPassword")
    public ResponseEntity<CommonResponse<?>> resetPassword(@RequestBody Users user){
        Users users = resetPasswordService.checkPassword(user.getUserId(), user);
        if(users == null){
            StatusCode statusCode = StatusCode.InvalidData;
            return generateResponse(statusCode, user.getUserId());
        }
        StatusCode ok = StatusCode.OK;
        return generateResponse(ok, users.getUserId());
    }

    private ResponseEntity<CommonResponse<?>> generateResponse(StatusCode statusCode, Integer userId){
        CommonResponse<?> response = new CommonResponse<>().setStatus(statusCode.getValue())
                .setErrorMessage(convertStatusToMessage(statusCode));

        switch (statusCode){
            case OK:
                return ResponseEntity.created(URI.create("/api/users/" + userId)).body(response);
            case InvalidData:
                return ResponseEntity.badRequest().body(response);
            case InternalError:
                return ResponseEntity.internalServerError().body(response);
            case AccountUnavailable:
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            default:
                log.warn("unknown_status :{}", statusCode);
                return ResponseEntity.internalServerError().body(response);
        }
    }

    private String convertStatusToMessage(StatusCode statusCode){
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
                log.warn("unknown_status :{}", statusCode);
                return "Unknown_Status";
        }
    }
}
