package com.allxone.mybatisprojectservice.model;

import com.allxone.mybatisprojectservice.dto.UserDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users implements UserDetails {

    private Long id;

    private String password;

    private String firstName;

    private String lastName;

    private String gender;

    private String address;

    private Byte age;

    private Boolean isActive;

    private String email;

    private String phoneNumber;

    private Role role;

    public UserDTO toUserDTO() {
        return new UserDTO()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setIsActive(isActive)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
