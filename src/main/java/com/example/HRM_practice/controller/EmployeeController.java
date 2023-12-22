package com.example.HRM_practice.controller;

import com.example.HRM_practice.model.entity.Department;
import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.model.entity.EmployeeDTO;
import com.example.HRM_practice.service.serviceImpl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){

        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("deleteEmployee/{empId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer empId){
        employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("updateEmployee/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer empId,
                                                   @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(empId, employee),HttpStatus.OK);
    }

    //查詢所有員工並依分頁顯示
    //{page} 當前分頁 (從0開始)
    //return (page, 每頁顯示幾筆)
    @GetMapping("listAllEmployee/{page}")
    public ResponseEntity<List<Employee>> listAllEmployee(@PathVariable Integer page){
        return new ResponseEntity<>(employeeService.listAllEmployee(page, 2),HttpStatus.OK);
    }

    @GetMapping("listEmployeeByName/{empName}")
    public ResponseEntity<List<Employee>> listEmployeeByName(@PathVariable String empName){
        return new ResponseEntity<>(employeeService.listEmployeeByName(empName),HttpStatus.OK);
    }

    @GetMapping("listEmployeeById/{empId}")
    public ResponseEntity<Employee> listEmployeeById(@PathVariable Integer empId){
        return new ResponseEntity<>(employeeService.listEmployeeById(empId),HttpStatus.OK);
    }

    @GetMapping("listEmployeeGreaterThanSal/{sal}")
    public ResponseEntity<List<Employee>> listEmployeeGreaterThanSal(@PathVariable Double sal){
        return new ResponseEntity<>(employeeService.listEmployeeGreaterThanSal(sal),HttpStatus.OK);
    }

    @GetMapping("listEmployeeByDeptId/{deptId}")
    public ResponseEntity<List<EmployeeDTO>> listEmployeeByDeptId(@PathVariable Integer deptId){

        log.info("Attempting to find employees by deptId{}", deptId);

        List<EmployeeDTO> employeeList = employeeService.listEmployeeByDeptId(deptId);

        log.info("find {} successfully by deptId", employeeList);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
}
