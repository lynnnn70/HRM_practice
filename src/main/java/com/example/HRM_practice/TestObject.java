package com.example.HRM_practice;

import com.example.HRM_practice.model.entity.Roles;

import java.util.List;

public class TestObject {
    private Integer id;

    private List<Roles> rolesList;

    public TestObject() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{id:").append(id);
        sb.append(", rolesList:").append(rolesList);
        sb.append("}");
        return sb.toString();
    }

    public TestObject(Integer id, List<Roles> rolesList) {
        this.id = id;
        this.rolesList = rolesList;
    }
}
