package com.example.HRM_practice.controller;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.common.StatusCode;
import com.example.HRM_practice.exception.AccountUnavaliableException;
import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.service.serviceImpl.RegisterServiceImpl;
import com.example.HRM_practice.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api")
public class RegisterController {

    @Autowired
    private RegisterServiceImpl registerService;

    public static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @PostMapping("register")
    public ResponseEntity<CommonResponse<?>> register(@RequestBody Users user){
        StatusCode statusCode;
        Integer userId = null;
        if(!isRegisterDataValid(user)){
            statusCode = StatusCode.InvalidData;
            log.debug("123");
            System.out.println(statusCode);
            log.debug("invalid data when register,{}", user);
            return generateResponse(statusCode, userId);
        }
        try {
            userId = registerService.register(user);
            log.debug("username available");
            return generateResponse(StatusCode.OK, user.getUserId());
        }catch (AccountUnavaliableException ae){
            statusCode = StatusCode.InvalidData;
//            return generateResponse(statusCode, user.getUserId());
        }catch (Exception e){
            log.warn("username unavailable123:" + user.getUserName(), user.getPassword(), e);
            log.warn("error when register", e);
            statusCode = StatusCode.InvalidData;
//            return generateResponse(statusCode,user.getUserId());
        }
            log.debug("end of register. StatusCode:{}, UserId:{}", statusCode, userId);
        return generateResponse(statusCode, userId);

    }

    private boolean isRegisterDataValid(Users users){
        String username = users.getUserName();
        String password = users.getPassword();
        return ValidateUtil.isUserNameCorrect(username) && ValidateUtil.isPasswordCorrect(password);
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
