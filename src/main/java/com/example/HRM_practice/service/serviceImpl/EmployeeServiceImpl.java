package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.model.repository.EmployeeRepository;
import com.example.HRM_practice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //新增員工
    @Override
    public Employee addEmployee(Employee employee) {
        return null;
    }

    //刪除員工
    @Override
    public void deleteEmployee(Integer empId) {

    }
    //修改員工資料
    @Override
    public Employee updateEmployee(Integer empId, Employee employee) {
        return null;
    }
    //查詢全部員工
    @Override
    public List<Employee> listAllEmployee() {
        return null;
    }

    //依照員工姓名模糊查詢
    @Override
    public List<Employee> listEmployeeByName(String empName) {
        return null;
    }

    //依照員工編號查詢
    @Override
    public List<Employee> listEmployeeById(Integer id) {
        return null;
    }

    //查SAL大於傳入值的員工
    @Override
    public List<Employee> listEmployeeGreaterThanSal(Double sal) {
        return null;
    }

    //查入值日期在傳入值之前
    @Override
    public List<Employee> listEmployeeBeforeDate(LocalDate hireDate) {
        return null;
    }

    //查入值日期在傳入值之後
    @Override
    public List<Employee> listEmployeeAfterDate(LocalDate hireDate) {
        return null;
    }
}
