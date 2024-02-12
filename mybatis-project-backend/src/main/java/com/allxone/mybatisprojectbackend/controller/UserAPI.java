package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.model.User;
import com.allxone.mybatisprojectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserAPI {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> doGetAllUsers() {

        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            List<User> data = userService.getAllUsers();
            result.put("success", true);
            result.put("message", "Call API doGetAllUsers successfully");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call API doGetAllUsers fail");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Long id) {

        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            userService.deleteUser(id);
            result.put("success", true);
            result.put("message", "Call API doDeleteUser successfully");
            result.put("data", id);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call API doDeleteUser fail");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);

    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> doUpdateUser(@RequestBody User user){
        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            userService.updateUser(user);
            result.put("success", true);
            result.put("message", "Call API getAllUsers successfully");
            result.put("data", "Testing token");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call API getAllUsers fail");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

}
