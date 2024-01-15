package com.example.HRM_practice.controller;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.common.StatusCode;
import com.example.HRM_practice.model.dto.ResetPasswordDto;
import com.example.HRM_practice.service.serviceImpl.ResetPasswordServiceImpl;
import com.example.HRM_practice.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.core.Authentication;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordServiceImpl resetPasswordService;

    private static final Logger log = LoggerFactory.getLogger(ResetPasswordController.class);

    @PutMapping("resetPassword/{userId}")
    public ResponseEntity<CommonResponse<?>> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto,
                                                           @PathVariable Integer userId, Authentication authentication){

        //確認輸入的兩個新密碼是否相同
        if(!resetPasswordDto.getNewPassword().equals(resetPasswordDto.getNewPasswordCheck())){
            return null;
        }
        //確認密碼輸入格式是否正確
        if(!isRegisterDataValid(resetPasswordDto)){
            return null;
        }
        //Todo 確認舊密碼是否輸入正確，不是> X，是> save
        resetPasswordService.resetPassword()

//        if(resetPasswordDto.getNewPasswordCheck().equals())
//        StatusCode statusCode = null;
//        if(!isRegisterDataValid(resetPasswordDto)){
//            statusCode = StatusCode.InvalidData;
//            return generateResponse(statusCode, userId);
//        }
//        resetPasswordService.resetPassword(userId, loginUserName, resetPasswordDto)






//        if(!newPassword.equals(newPasswordCheck)){
//            statusCode = StatusCode.InvalidData;
//            return generateResponse(statusCode,userId);
//        }
//
//        Users user = resetPasswordService.resetPassword(userId, oldPassword, newPassword);
//
//        if(user == null){
//            statusCode = StatusCode.InvalidData;
//            return generateResponse(statusCode, userId);
//        }
//        statusCode = StatusCode.OK;
//        return generateResponse(statusCode, user.getUserId());

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

    private boolean isRegisterDataValid(ResetPasswordDto resetPasswordDto){
       return Optional.ofNullable(resetPasswordDto)
               .filter(vo -> ValidateUtil.isPasswordCorrect(resetPasswordDto.getOldPassword()))
               .filter(vo -> ValidateUtil.isPasswordCorrect(resetPasswordDto.getNewPassword());

    }
}
