package com.example.HRM_practice.service;

import com.example.HRM_practice.model.entity.Department;
import com.example.HRM_practice.model.entity.Employee;

import java.util.List;

public interface DepartmentService {

    //新增部門
    public Department addDepartment(Department department);

    //刪除部門
    public void deleteDepartment(Integer deptId);

    //修改部門資料
    public Department updateDepartment(Department department);

    //查詢全部部門
    public List<Department> listALLDepartment();

    //依照部門名稱模糊查詢
    public List<Department> listDepartmentByName(String deptName);

    //依照部門(id)查詢該部門的所有員工名單（不包括機敏資料 如 手機 地址等
    public List<Employee> listEmployeeByDeptId(Integer deptId);



}
