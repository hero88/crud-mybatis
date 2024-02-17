package com.allxone.mybatisprojectbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Boolean gender;
    private String address;
    private Integer age;
    private String phoneNumber;
}
