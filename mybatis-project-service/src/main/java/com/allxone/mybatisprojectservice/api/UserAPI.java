package com.allxone.mybatisprojectservice.api;

import com.allxone.mybatisprojectservice.dto.UserDTO;
import com.allxone.mybatisprojectservice.model.Users;
import com.allxone.mybatisprojectservice.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserAPI {
    private final IUserService userService;

    @Autowired
    public UserAPI(IUserService userService){
        this.userService = userService;
    }

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

    @GetMapping("/userName/{userName}")
    public ResponseEntity<?> findByUserName(@PathVariable String userName) {
        try {
            Users users = userService.getByUserName(userName);

            return new ResponseEntity<>(users.toUserDTO(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody Users user) {
        try {
            user.setUserId(userId);
            userService.updateUser(user);
            return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/activeUser/{userId}")
    public ResponseEntity<?> activeUser(@PathVariable Long userId, @RequestBody Users user) {
        try {
            user.setUserId(userId);
            userService.activeUser(user);
            return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user.", HttpStatus.BAD_REQUEST);
        }
    }


}
