package com.example.HRM_practice.service;

import com.example.HRM_practice.model.entity.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    //新增員工
    public Employee addEmployee(Employee employee);

    //刪除員工
    public void deleteEmployee(Integer empId);

    //修改員工資料
    public Employee updateEmployee(Integer empId, Employee employee);

    //查詢全部員工
    public List<Employee> listAllEmployee(int page, int size);

    //依照員工姓名模糊查詢
    public List<Employee> listEmployeeByName(String empName);

    //依照員工編號查詢
    public Employee listEmployeeById(Integer empId);

    //查SAL大於傳入值的員工
    public List<Employee> listEmployeeGreaterThanSal(Double sal);

    //查入值日期在傳入值之前
//    public List<Employee> listEmployeeBeforeDate(LocalDate hireDate);

    //查入值日期在傳入值之後
//    public List<Employee> listEmployeeAfterDate(LocalDate hireDate);
}
