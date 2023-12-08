package com.example.HRM_practice.controller;

import com.example.HRM_practice.service.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;
}
