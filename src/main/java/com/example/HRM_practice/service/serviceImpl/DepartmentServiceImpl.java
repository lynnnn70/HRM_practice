package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.model.entity.Department;
import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.model.repository.DepartmentRepository;
import com.example.HRM_practice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    //新增部門
    @Override
    public Department addDepartment(Department department) {
        return null;
    }

    //刪除部門
    @Override
    public void deleteDepartment(Integer deptId) {

    }

    //修改部門資料
    @Override
    public Department updateDepartment(Integer deptId, Department department) {
        return null;
    }

    //查詢全部部門
    @Override
    public List<Department> listALLDepartment() {
        return null;
    }

    //依照部門名稱模糊查詢
    @Override
    public List<Department> listDepartmentByName(String deptName) {
        return null;
    }

    //依照部門(id)查詢該部門的所有員工名單（不包括機敏資料 如 手機 地址等
    @Override
    public List<Employee> listEmployeeByDeptId(Integer deptId) {
        return null;
    }
}
