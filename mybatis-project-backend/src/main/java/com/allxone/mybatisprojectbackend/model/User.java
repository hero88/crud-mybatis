package com.allxone.mybatisprojectbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String address;
    private int age;
    private boolean isActive;
    private String email;
    private String phoneNumber;
    private Date emailVerificationAt;
    private String rememberToken;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
