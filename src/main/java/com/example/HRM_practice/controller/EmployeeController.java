package com.example.HRM_practice.controller;

import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.service.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeController {

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

    //Todo: 沒update成功
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




}
