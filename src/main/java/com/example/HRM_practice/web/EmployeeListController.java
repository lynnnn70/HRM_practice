package com.example.HRM_practice.web;

import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.service.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("web/emp")
public class EmployeeListController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("ListAll")
    public String listAllEmp(Model model, @PathVariable Integer page){
        List<Employee> employeeList = employeeService.listAllEmployee(page,2);
        model.addAttribute("empList, employeeList");

        return "Employee/employeeList";
    }
}
