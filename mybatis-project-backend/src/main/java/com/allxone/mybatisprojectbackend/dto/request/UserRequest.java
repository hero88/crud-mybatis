package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String firstname;
    private String lastname;
    private Boolean gender;
    private String address;
    private Integer age;
    private String email;
    private String phoneNumber;
}
