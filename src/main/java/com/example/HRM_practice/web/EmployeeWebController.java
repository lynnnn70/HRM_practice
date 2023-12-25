package com.example.HRM_practice.web;

import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.model.entity.EmployeeDTO;
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
public class EmployeeWebController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    //查詢全部員工
    @GetMapping("listAllEmployee/{page}")
    public String listAllEmp(Model model, @PathVariable(value = "page" , required = false) String page){
        int pageNum = 0;
        if(page != null){
            pageNum =  Integer.parseInt(page);
        }
        List<Employee> employees = employeeService.listAllEmployee(pageNum,2);
        model.addAttribute("employeeAll", employees); //"對應" ${}

        return "employee/employeeList"; //html檔路徑
    }

    //依照員工姓名模糊查詢
    @GetMapping("listEmployeeByName/{empName}")
    public String listEmployeeByName(Model model, @PathVariable String empName){
        List<Employee> employees = employeeService.listEmployeeByName(empName);
        model.addAttribute("employeeByName", employees);

        return "employee/employeeList";
    }

    //依照員工編號查詢
    @GetMapping("listEmployeeById/{empId}")
    public String listEmployeeById(Model model, @PathVariable Integer empId){
        Employee employee = employeeService.listEmployeeById(empId);
        model.addAttribute("employeeById", employee);

        return "employee/employeeList";
    }
    //查SAL大於傳入值的員工

    @GetMapping("listEmployeeGreaterThanSal/{sal}")
    public String listEmployeeGreaterThanSal(Model model, @PathVariable Double sal){
        List<Employee> employees = employeeService.listEmployeeGreaterThanSal(sal);
        model.addAttribute("employeeGreaterThan", employees);

        return "employee/employeeList";
    }

    //依照部門(id)查詢該部門的所有員工名單
    @GetMapping("listEmployeeByDeptId/{deptId}")
    public String listEmployeeByDeptId(Model model, @PathVariable Integer deptId){
        List<EmployeeDTO> employeeDTOS = employeeService.listEmployeeByDeptId(deptId);
        model.addAttribute("employeeBtdeptId", employeeDTOS);

        return "employee/employeeList";
    }

    //查入職日期在傳入值之前

    //查入職日期在傳入值之後

    @GetMapping("index")
    public String index(){
        return "commons/commons";
    }

}
