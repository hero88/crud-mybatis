package com.allxone.mybatisprojectbackend.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private Boolean gender;
    private String address;
    private Integer age;
    private String email;
    private String phoneNumber;
}
