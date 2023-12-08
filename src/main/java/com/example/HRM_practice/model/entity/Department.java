package com.example.HRM_practice.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "loc")
    private String loc;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{deptId:").append(deptId);
        sb.append(", deptName:'").append(deptName).append('\'');
        sb.append(", loc:'").append(loc).append('\'');
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Department that = (Department) object;
        return Objects.equals(deptId, that.deptId) && Objects.equals(deptName, that.deptName) && Objects.equals(loc, that.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId, deptName, loc);
    }
}
