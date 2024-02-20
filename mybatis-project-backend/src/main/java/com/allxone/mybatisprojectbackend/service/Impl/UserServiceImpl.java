package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.UserConvert;
import com.allxone.mybatisprojectbackend.dto.request.ChangePasswordRequest;
import com.allxone.mybatisprojectbackend.dto.request.UserRequest;
import com.allxone.mybatisprojectbackend.dto.response.UserResponse;
import com.allxone.mybatisprojectbackend.mapper.UserMapper;
import com.allxone.mybatisprojectbackend.mapper.UserRoleMapper;
import com.allxone.mybatisprojectbackend.model.User;
import com.allxone.mybatisprojectbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.getAllUsers()
                .stream()
                .map(UserConvert::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) throws IllegalStateException {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        try {
            userMapper.updateUserPassword(user);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User user = UserConvert.toUser(userRequest);
        userMapper.saveUser(user);
        return UserConvert.toDto(getUserById(user.getId()));
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        User updateUser = userMapper.getUserById(userRequest.getId());

        updateUser.setName(userRequest.getName() != null ? userRequest.getName() : updateUser.getName());
        updateUser.setGender(userRequest.getGender() != null ? userRequest.getGender() : updateUser.getGender());
        updateUser.setAddress(userRequest.getAddress() != null ? userRequest.getAddress() : updateUser.getAddress());
        updateUser.setAge(userRequest.getAge() != null ? userRequest.getAge() : updateUser.getAge());
        updateUser.setPhoneNumber(userRequest.getPhoneNumber() != null ? userRequest.getPhoneNumber() : updateUser.getPhoneNumber());

        userMapper.updateUser(updateUser);
        return UserConvert.toDto(getUserById(updateUser.getId()));
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void activatedUser(User user) {
        user.setIsActive(true);
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRoleMapper.deleteUserRoleByUsUserId(id);
        userMapper.deleteUserById(id);
    }

}
