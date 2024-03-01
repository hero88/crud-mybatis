package com.allxone.mybatisprojectbackend.dto.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private Long userId;
    private String firstname;
    private String lastname;
    private Instant birthday;
    private String gender;
    private String contactNumber;
    private String email;
    private Integer departmentId;
    private String position;
    private Instant hireDate;
    private Instant terminationDate;
    private Short leavePaidDays;
    private JsonNode insuranceIds;
    private Instant createdAt;
    private Instant updatedAt;
}
