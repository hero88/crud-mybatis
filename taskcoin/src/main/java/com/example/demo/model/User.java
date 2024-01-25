package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String address;
    private int age;
    private boolean isActive;
    private String email;
    private String phone_number;
    private Date emailVerificationAt;
    private String rememberToken;
    private Date createdAt;
    private Date updatedAt;
    private Set<Role> roles;

    public User(String username, String password, String name, String gender, String address, int age, boolean isActive, String email, String phone_number, Date emailVerificationAt, String rememberToken, Date createdAt, Date updatedAt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.isActive = isActive;
        this.email = email;
        this.phone_number = phone_number;
        this.emailVerificationAt = emailVerificationAt;
        this.rememberToken = rememberToken;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(String username, String email, String encode) {
    }
}
