package com.example.HRM_practice.controller;

import com.example.HRM_practice.common.StatusCode;
import com.example.HRM_practice.response.UsersResponse;
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
    public ResponseEntity<UsersResponse> register(@RequestBody Users user) throws AccountUnavaliableException{
        StatusCode statusCode;
        Integer userId = null;
        //Check Regex
        if(!isRegisterDataValid(user)){
            log.debug("invalid data when register,{}", user);
            return generateResponse(StatusCode.InvalidData, null);
        }
        try {
            userId = registerService.register(user);
            log.debug("username available");
            return generateResponse(StatusCode.OK, user.getUserId());
        }catch (AccountUnavaliableException ae){
            statusCode = StatusCode.InvalidData;
            log.warn("username unavailable:" + user.getUserName(), user.getPassword(), ae);
            return generateResponse(statusCode, null);
        }catch (Exception e){
            log.warn("error when register", e);
            statusCode = StatusCode.InvalidData;
            return generateResponse(statusCode,null);
        }
    }

    private boolean isRegisterDataValid(Users users){
        String username = users.getUserName();
        String password = users.getPassword();
        return ValidateUtil.isUserNameCorrect(username) && ValidateUtil.isPasswordCorrect(password);
    }

    private ResponseEntity<UsersResponse> generateResponse(StatusCode statusCode, Integer userId){
        UsersResponse response = new UsersResponse().setStatus(statusCode.getValue())
                                                              .setErrorMessage(convertStatusToMessage((statusCode)));
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
