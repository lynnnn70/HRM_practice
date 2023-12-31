package com.example.HRM_practice.model.repository;

import com.example.HRM_practice.model.entity.Employee;
import com.example.HRM_practice.model.entity.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    List<Employee> findAllByEmpNameContaining(String empName);

    //查SAL大於傳入值的員工
    //JPQL寫法 @Query("SELECT e FROM Employee e WHERE e.sal > :inputSal") Employee指實體類別，不是db表格名
    @Query(value = "SELECT * FROM employee e WHERE e.sal > :inputSal", nativeQuery = true)
    List<Employee> findBySalGreaterThan(@Param("inputSal")Double inputSal);

    //查入職日期在傳入值之前
//    @Query(value = "SELECT * FROM Employee e WHERE e.hireDate < :inputDate")
//    List<Employee> findByHireDateBefore(@Param("hireDate")LocalDate hireDate);

//    @Query(value = "SELECT e.emp_id, e.dept_id, e.emp_name, e.emp_job FROM employee e WHERE e.dept_id = :input", nativeQuery = true)
//    List<Employee> findEmployeesByDeptId(@Param("input") Integer deptId);

    @Query(value = "SELECT NEW com.example.HRM_practice.model.entity.EmployeeDTO(e.empId, e.deptId, e.empName, e.empJob) FROM Employee e WHERE  e.deptId = :deptId")
    List<EmployeeDTO> findEmployeesByDeptId(@Param("deptId") Integer deptId);

    //XXXX
//    @Query(value = "SELECT NEW com.example.HRM_practice.model.entity.EmployeeDTO(e.emp_id, e.dept_id, e.emp_name, e.emp_job) FROM employee e WHERE  e.deptId = :deptId" , nativeQuery = true)
//    List<EmployeeDTO> findEmployeesByDeptId(@Param("deptId") Integer deptId);


    List<Employee> findEmployeeByDeptId(Integer deptId);
}
