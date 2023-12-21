package com.example.HRM_practice.controller;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.common.StatusCode;
import com.example.HRM_practice.model.entity.Department;
import com.example.HRM_practice.service.serviceImpl.DepartmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:5500") // 允許來自特定源的跨來源請求

public class DepartmentController {

    public static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentServiceImpl departmentService;

    //{}是佔位符，當訊息被寫入時{}將被替換為相應的值
    @PostMapping("addDepartment")
    public ResponseEntity<CommonResponse<?>> addDepartment(@RequestBody Department department){

        log.info("Attempting to department with data:{}", department);

        Department addDepartment = departmentService.addDepartment((department));
        if(addDepartment == null){
            log.info("Duplicate input:{}", department);
            return generateResponse(StatusCode.Duplicate);
        }

        CommonResponse<Department> objectCommonResponse = new CommonResponse<>();
        objectCommonResponse.setBody(addDepartment);

        log.info("Department added successfully. Add department:{}", objectCommonResponse);
        return generateResponse(StatusCode.OK);
    }
//    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
//        log.info("Attempting to add department with data:{}", department);
//
//        Department addedDepartment = departmentService.addDepartment(department);
//        if(addedDepartment == null){
//            log.info("add new department duplicate ");
//        return
//        }
//        log.info("Department added successfully. Add department:{}", addedDepartment);
//        return new ResponseEntity<>(addedDepartment, HttpStatus.CREATED);
//    }

    private ResponseEntity<CommonResponse<?>> generateResponse(StatusCode statusCode){
        CommonResponse<?> response = new CommonResponse<>().setStatus(statusCode.getValue()).setErrorMessage(convertStatusToMessage(statusCode));

        switch(statusCode){
            case OK:
                return ResponseEntity.created(URI.create("api/addDepartment")).body(response); //RESTFUL風格
            case InvalidData:
                return ResponseEntity.badRequest().body(response);
            case InternalError:
                return ResponseEntity.internalServerError().body(response);
            case AccountUnavailable:
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            case Duplicate:
                return ResponseEntity.accepted().body(response);
            default:
                log.warn("unknown_status :{}", statusCode);
                return ResponseEntity.internalServerError().body(response);
        }
    }

    private String convertStatusToMessage(StatusCode statusCode){
        switch (statusCode){
            case OK:
                return "success";
            case InvalidData:
                return "Invalid_Input";
            case InternalError:
                return "Internal_Error";
            case AccountUnavailable:
                return "Account_Unavailable";
            case Duplicate:
                return "Duplicate";
            default:
                log.warn("unknown_status :{}", statusCode);
                return "Unknown_Status";
        }
    }

    @DeleteMapping("deleteDepartment/{deptId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Integer deptId){
        departmentService.deleteDepartment(deptId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("updateDepartment/{deptId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Integer deptId,
                                                       @RequestBody Department department){

        log.info("Attempting to update department:{}", department);

        Department updatedDepartment = departmentService.updateDepartment(deptId, department);

        log.info("Department updated successfully. update department:{}",updatedDepartment);

        return new ResponseEntity<>(updatedDepartment, HttpStatus.ACCEPTED);
    }

    @GetMapping("listALLDepartment")
    public ResponseEntity<List<Department>> listALLDepartment(){

        log.info("Attempting to find All departments.");

        List<Department> departmentList = departmentService.listALLDepartment();

        log.info("Find {} successfully.", departmentList);

        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    @GetMapping("listDepartmentByName/{deptName}")
    public ResponseEntity<List<Department>> listDepartmentByName(@PathVariable String deptName){

        log.info("Attempting to find departments by name{}.", deptName);

        List<Department> departmentList = departmentService.listDepartmentByName(deptName);

        if(departmentList == null || departmentList.isEmpty()){
            return new ResponseEntity<>(null, generateResponse(StatusCode.InvalidData).getStatusCode());
        }

        log.info(("find {} successfully by deptName."), departmentList);

        return new ResponseEntity<>(departmentList, generateResponse(StatusCode.OK).getStatusCode());
    }



}
