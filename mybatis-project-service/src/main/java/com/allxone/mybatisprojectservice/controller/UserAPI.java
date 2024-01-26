package com.allxone.mybatisprojectservice.controller;

import com.allxone.mybatisprojectservice.dto.user.UserDTO;
import com.allxone.mybatisprojectservice.model.Users;
import com.allxone.mybatisprojectservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserAPI {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<?> findAllUser() {
        List<UserDTO> userDTOS = userService.findAllUserDTO();

        if (userDTOS.isEmpty()) {
            return new ResponseEntity<>("No customer found.", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable Long userId) {
        try {
            Users users = userService.findByUserId(userId);

            return new ResponseEntity<>(users.toUserDTO(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByUserName(@PathVariable String email) {
        try {
            Users users = userService.findByEmail(email);

            return new ResponseEntity<>(users.toUserDTO(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody Users user) {
        try {
            user.setId(userId);
            userService.updateUser(user);
            return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/activeUser/{userId}")
    public ResponseEntity<?> activeUser(@PathVariable Long userId) {
        try {
            var user = userService.findByUserId(userId);
            userService.activeUser(user);
            return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> insertUser(@RequestBody Users user) {
        try {
            userService.insertUser(user);
            return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user.", HttpStatus.BAD_REQUEST);
        }
    }

}
