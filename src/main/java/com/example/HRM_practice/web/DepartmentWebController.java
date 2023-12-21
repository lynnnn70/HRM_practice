package com.example.HRM_practice.web;

import com.example.HRM_practice.model.entity.Department;
import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.model.entity.EmployeeDTO;
import com.example.HRM_practice.service.serviceImpl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @GetMapping("departmentList1")
    public String departmentList1(Model model){
        List<Department> departmentList = departmentService.listALLDepartment();
        model.addAttribute("departmentList", departmentList);
        return ("department/departmentList_1");
    }

    @GetMapping("departmentList2")
    public String departmentList2(Model model,
                                  @RequestParam(required = false, name="deptIdSelect") Integer deptIdSelect ){
        List<Department> departmentList = departmentService.listALLDepartment();
        List<Department> selcetedList;
        if(deptIdSelect != null && deptIdSelect != -1){
            Department department = departmentService.listDepartmentById(deptIdSelect);
            selcetedList = Arrays.asList(department);
            model.addAttribute("deptId",deptIdSelect);
        }else {
            selcetedList = departmentList;
        }
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("selcetedList", selcetedList);

        return ("department/departmentList_2");
    }

    @GetMapping("listInUpdateDepartment")
    public String listInUpdateDepartment(Model model,
                                   @RequestParam Integer deptId){
        Department department = departmentService.listDepartmentById(deptId);

//        Employee employee = new Employee();
//        employee.setEmpName("Wilson");

        model.addAttribute("department",department);
//        model.addAttribute("test" , employee);

        return "department/updateDepartment"; //對應html檔
    }

    @PostMapping("updateDepartment")
    public String updateDepartment( @ModelAttribute Department department){
        // 可能要做驗證

        departmentService.updateDepartment(department.getDeptId() ,department);

        //根據deptId來加載相應的信息重導到更新後的部門訊息頁面
        return "redirect:listInUpdateDepartment?deptId=" + department.getDeptId();

    }


}
