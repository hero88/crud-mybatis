package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private Boolean gender;
    private String address;
    private Integer age;
    private String email;
    private String password;
    private Boolean isActive;
    private String phoneNumber;
    private Instant createdAt;
    private Instant updatedAt;
}
