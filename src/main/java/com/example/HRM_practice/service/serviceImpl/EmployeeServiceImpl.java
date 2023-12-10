package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.model.repository.EmployeeRepository;
import com.example.HRM_practice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //新增員工
    @Override
    public Employee addEmployee(Employee employee) {
        Employee emp = new Employee();
        emp.setDeptId(employee.getDeptId());
        emp.setEmpName(employee.getEmpName());
        emp.setEmpJob(employee.getEmpJob());
        emp.setHireDate(employee.getHireDate());
        emp.setSal(employee.getSal());
        emp.setComm(employee.getComm());
        employeeRepository.save(emp);
        return emp;
    }

    //刪除員工
    @Override
    public void deleteEmployee(Integer empId) {
        employeeRepository.deleteById(empId);
    }
    //修改員工資料
    @Override
    public Employee updateEmployee(Integer empId, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if(!optionalEmployee.isPresent()){
            return null;
        }
        Employee emp = optionalEmployee.get();
        emp.setEmpName(employee.getEmpName());
        emp.setEmpJob(employee.getEmpJob());
        emp.setHireDate(employee.getHireDate());
        emp.setSal(employee.getSal());
        emp.setComm(employee.getComm());
        emp.setDeptId(employee.getDeptId());
        return employeeRepository.save(emp);
    }
    //查詢全部員工

    @Override
    public List<Employee> listAllEmployee(int page, int size) {
        Page<Employee> pageResult = employeeRepository.findAll(
                PageRequest.of(page, //查詢的頁數 從0開始
                               size, //查詢的每頁筆數
                        Sort.by("deptId").ascending())); //依照deptId 升冪排序

        return pageResult.getContent();
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
