package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.model.entity.Department;
import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.model.repository.DepartmentRepository;
import com.example.HRM_practice.model.repository.EmployeeRepository;
import com.example.HRM_practice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    //新增部門
    @Override
    public Department addDepartment(Department department) {
        String deptName = department.getDeptName();
        if(departmentRepository.findByDeptName(deptName).isPresent()){
            return null;
        }
        Department department1 = new Department();
        department1.setDeptName(department.getDeptName());
        department1.setLoc(department.getLoc());
        departmentRepository.save(department1);
        return department1;
    }

    //刪除部門
    @Override
    public void deleteDepartment(Integer deptId) {
        departmentRepository.deleteById(deptId);
    }

    //修改部門資料
    @Override
    public Department updateDepartment(Integer deptId, Department department) {
        Optional<Department> optionalDept = departmentRepository.findByDeptId(deptId);
        if(!optionalDept.isPresent()){
            return null;
        }
        Department dept = optionalDept.get();
        dept.setDeptName(department.getDeptName());
        dept.setLoc(department.getLoc());
        return departmentRepository.save(dept);
    }

    //查詢全部部門
    @Override
    public List<Department> listALLDepartment() {
        return departmentRepository.findAll();
    }

    //依照部門名稱模糊查詢
    @Override
    public List<Department> listDepartmentByName(String deptName) {
        Optional<List<Department>> departmentOptional = departmentRepository.findByDeptNameContaining(deptName);
        return departmentOptional.orElse(null);
    }



}
