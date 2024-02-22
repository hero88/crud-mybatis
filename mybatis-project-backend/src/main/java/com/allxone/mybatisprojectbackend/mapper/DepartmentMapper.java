package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    List<Department> getAllDepartments();
    void updateDepartment(Department department);
    void deleteDepartmentById(int id);
    void saveDepartment(Department department);
    Department getDepartmentById(int id);
}
