package com.example.HRM_practice.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_job")
    private String empJob;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "sal")
    private Double sal;

    @Column(name = "comm")
    private Double comm;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpJob() {
        return empJob;
    }

    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{empId:").append(empId);
        sb.append(", deptId:").append(deptId);
        sb.append(", empName:'").append(empName).append('\'');
        sb.append(", empJob:'").append(empJob).append('\'');
        sb.append(", hireDate:").append(hireDate);
        sb.append(", sal:").append(sal);
        sb.append(", comm:").append(comm);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Employee employee = (Employee) object;
        return Objects.equals(empId, employee.empId) && Objects.equals(deptId, employee.deptId) && Objects.equals(empName, employee.empName) && Objects.equals(empJob, employee.empJob) && Objects.equals(hireDate, employee.hireDate) && Objects.equals(sal, employee.sal) && Objects.equals(comm, employee.comm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, deptId, empName, empJob, hireDate, sal, comm);
    }
}
