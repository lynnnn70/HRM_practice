package com.example.HRM_practice.web;

import com.example.HRM_practice.model.entity.Department;
import com.example.HRM_practice.service.serviceImpl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("web/dept")
public class DepartmentWebController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @GetMapping("addDepartment")
    public String addDepartment(Model model){   //Model是一個接口，用於在controller方法中向view傳遞數據
        return "department/addDepartment";
    }


    @GetMapping("departmentList")
    public String departmentList(Model model){
        List<Department> departmentList = departmentService.listALLDepartment();
        model.addAttribute("departmentList", departmentList);
        return ("department/departmentList");
    }
}
