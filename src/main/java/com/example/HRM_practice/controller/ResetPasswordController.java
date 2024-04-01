package com.example.HRM_practice.controller;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.common.StatusCode;
import com.example.HRM_practice.model.dto.ResetPasswordDto;
import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.response.ResetPasswordResponse;
import com.example.HRM_practice.service.serviceImpl.ResetPasswordServiceImpl;
import com.example.HRM_practice.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
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
    public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto,
                                                               @PathVariable Integer userId){
//Todo
//Authentication 是代表用戶的一個介面，principal代表當前使用者的身分，為獲取當前使用者Id，並將其存在loginUserId中
//        public ResponseEntity<CommonResponse<?>> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto,
//                @PathVariable Integer userId, Authentication authentication){
//        final String loginUserId = (String) authentication.getPrincipal();
//        log.debug("userId :{} resetPassword", loginUserId)

        //確認輸入的兩個新密碼是否相同
        if(resetPasswordDto == null || !resetPasswordDto.getNewPassword().equals(resetPasswordDto.getNewPasswordCheck())){
            return generateResponse(StatusCode.InvalidPassword, userId);
        }

        //確認新的不能跟舊密碼一樣
        if(resetPasswordDto.getNewPassword().equals(resetPasswordDto.getOldPassword())){
            return generateResponse(StatusCode.Duplicate, userId);
        }

        //確認密碼輸入格式是否正確
        if(!isRegisterDataValid(resetPasswordDto)){
            return generateResponse(StatusCode.WrongFormat, userId);
        }

        //確認舊密碼是否輸入正確
        Users reset = resetPasswordService.resetPassword(userId, resetPasswordDto);
        if(reset == null){
            return generateResponse(StatusCode.NotFound, userId);
        }
        return generateResponse(StatusCode.OK, userId);

    }



    private ResponseEntity<ResetPasswordResponse> generateResponse(StatusCode statusCode, Integer userId){
        ResetPasswordResponse response = new ResetPasswordResponse().setStatus(statusCode.getValue())
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
            case NotFound:
                return "Wrong_Password_NotFound";
            case Duplicate:
                return "Duplicate_Password";
            case WrongFormat:
                return "WrongFormat_Input";
            case InvalidPassword:
                return "InvalidPassword_Input";
            default:
                log.warn("unknown_status :{}", statusCode);
                return "Unknown_Status";
        }
    }

    private boolean isRegisterDataValid(ResetPasswordDto resetPasswordDto){
       return Optional.ofNullable(resetPasswordDto)
               .filter(vo -> ValidateUtil.isPasswordCorrect(resetPasswordDto.getOldPassword()))
               .filter(vo -> ValidateUtil.isPasswordCorrect(resetPasswordDto.getNewPassword()))
               .isPresent();

    };
}
