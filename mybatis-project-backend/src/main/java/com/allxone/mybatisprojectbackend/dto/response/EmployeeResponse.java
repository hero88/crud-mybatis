package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

import java.time.Instant;

@Data
public class EmployeeRequest {
    private Long id;
    private Long userId;
    private String firstname;
    private String lastname;
    private Instant birthday;
    private String gender;
    private String contactNumber;
    private String email;
    private int departmentId;
    private String position;
    private Instant hireDate;
    private Instant terminationDate;
}
