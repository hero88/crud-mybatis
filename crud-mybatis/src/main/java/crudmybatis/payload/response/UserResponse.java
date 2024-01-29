package crudmybatis.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String name;
    private String address;
    private int age;
    private boolean isActive;
    private String email;
    private String phoneNumber;
    private Date createdAt;
    private Date updatedAt;
    private String gender;
    List<String> roles;
}
