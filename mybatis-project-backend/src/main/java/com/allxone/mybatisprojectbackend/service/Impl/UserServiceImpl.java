package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.dto.request.ChangePasswordRequest;
import com.allxone.mybatisprojectbackend.mapper.UserMapper;
import com.allxone.mybatisprojectbackend.model.Token;
import com.allxone.mybatisprojectbackend.model.User;
import com.allxone.mybatisprojectbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {
        List<User> result = userMapper.getAllUsers();
        return result;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userMapper.updateUser(user);
    }

    @Override
    public User saveUser(User user) {
        userMapper.saveUser(user);
        return getUserById(user.getId());
    }

    @Override
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return getUserById(user.getId());
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void activatedUser(User user) {
        user.setActive(true);
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUserById(id);
    }
}
