package com.example.HRM_practice.model.entity;

import javax.persistence.*;

public class EmployeeDTO {

    private Integer empId;

    private Integer deptId;

    private String empName;

    private String empJob;

    public EmployeeDTO(Object[] objects){
        this.empId = (Integer) objects[0];
        this.deptId = (Integer) objects[1];
        this.empName = (String) objects[2];
        this.empJob = (String) objects[3];
    }

    public EmployeeDTO(Integer empId, Integer deptId, String empName, String empJob) {
        this.empId = empId;
        this.deptId = deptId;
        this.empName = empName;
        this.empJob = empJob;
    }

    public EmployeeDTO(Employee employee) {
        this.empId = employee.getEmpId();
        this.deptId = employee.getDeptId();
        this.empName = employee.getEmpName();
        this.empJob = employee.getEmpJob();
    }



    public Integer getEmpId() {
        return empId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpJob() {
        return empJob;
    }

    public EmployeeDTO() {
    }
}
