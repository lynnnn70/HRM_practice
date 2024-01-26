package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.model.entity.Roles;
import com.example.HRM_practice.model.repository.RolesRepository;
import com.example.HRM_practice.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public int addRoles(String roleName) {

        //傳入roleName>save
        Roles roles =new Roles();
        roles.setRoleName(roleName);
        rolesRepository.save(roles);

        //失敗>return null

        //成功>回傳自動生成的roleId
        return 0;
    }

    //新增角色權限
    //刪除角色權限

    //授權角色權限

    //取消角色權限
}
