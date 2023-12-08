package com.example.HRM_practice.controller;

import com.example.HRM_practice.model.entity.Department;
import com.example.HRM_practice.service.serviceImpl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @PostMapping("addDepartment")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @DeleteMapping("deleteDepartment/{deptId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Integer deptId){
        departmentService.deleteDepartment(deptId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("updateDepartment/{deptId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Integer deptId,
                                                       @RequestBody Department department){
        return new ResponseEntity<>(departmentService.updateDepartment(deptId, department), HttpStatus.ACCEPTED);
    }

    @GetMapping("listALLDepartment")
    public ResponseEntity<List<Department>> listALLDepartment(){
        return new ResponseEntity<>(departmentService.listALLDepartment(), HttpStatus.OK);
    }

    @GetMapping("listDepartmentByName/{deptName}")
    public ResponseEntity<List<Department>> listDepartmentByName(@PathVariable String deptName){
        return new ResponseEntity<>(departmentService.listDepartmentByName(deptName), HttpStatus.OK);
    }

    @GetMapping("listDepartmentByDeptId/{deptId}")
    public ResponseEntity<List<Department>> listDepartmentByDeptId(@PathVariable Integer deptId){
        return new ResponseEntity<>(departmentService.listDepartmentByDeptId(deptId), HttpStatus.OK);
    }


}
