package com.allxone.mybatisprojectbackend.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
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
