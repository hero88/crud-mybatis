package crudmybatis.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String address;
    private int age;
    private boolean isActive;
    private String email;
    private String phoneNumber;
    private Date emailVerificationAt;
    private String rememberToken;
    private Date createdAt;
    private Date updatedAt;
    private String gender;
    private Set<Role> roles = new HashSet<>();

    private LocalDateTime otpGeneratedTime;

    public User(String username, String password, String name, String email, Date createdAt,String rememberToken,String address,String phoneNumber,String gender, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.rememberToken = rememberToken;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }




}
