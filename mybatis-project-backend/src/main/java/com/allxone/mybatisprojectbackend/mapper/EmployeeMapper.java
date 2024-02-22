package com.allxone.mybatisprojectbackend.mapper;

import com.allxone.mybatisprojectbackend.model.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployeeById(Long id);
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
}
