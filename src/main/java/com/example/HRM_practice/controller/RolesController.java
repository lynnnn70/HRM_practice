package com.example.HRM_practice.controller;

import com.example.HRM_practice.common.ErrorMessage;
import com.example.HRM_practice.common.StatusCode;
import com.example.HRM_practice.model.entity.Roles;
import com.example.HRM_practice.response.RolesResponse;
import com.example.HRM_practice.service.serviceImpl.RolesServiceImpl;
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
public class RolesController {

    @Autowired
    private RolesServiceImpl rolesService;

    //新增角色權限
    @PostMapping("addRoles")
    public ResponseEntity<RolesResponse> addRoles(@RequestBody String roleName){

        //Confirm this user's roles is ROLE_USER_E

        //Check roleName null or not
        if(roleName == null){
            return generateResponse(StatusCode.InvalidData);
        }

        //add
        int roleId = rolesService.addRoles(roleName);
        return generateResponse(StatusCode.OK);
    }
    //刪除角色

    //授權角色權限
    //Confirm roles is admin

    //取消角色權限


    //todo 要保留roleId嗎? 因為只有成功的狀況才會有自動生成的id，失敗就不會有
    private ResponseEntity<RolesResponse> generateResponse(StatusCode statusCode, Integer roleId){
        RolesResponse response = new RolesResponse().setStatus(statusCode.getValue())
                                                    .setErrorMessage(ErrorMessage.convertStatus2Message(statusCode));
        switch (statusCode){
            case OK:
                return ResponseEntity.created(URI.create("api/addRoles")).body(response);
            case InvalidData:
                return ResponseEntity.badRequest().body(response);
            case AccountUnavailable:
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            case Duplicate:
                return ResponseEntity.accepted().body(response);
            default:
                return ResponseEntity.internalServerError().body(response);

        }
    }


}
