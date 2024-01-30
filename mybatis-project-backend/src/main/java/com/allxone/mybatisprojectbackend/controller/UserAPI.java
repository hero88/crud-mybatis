package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.model.User;
import com.allxone.mybatisprojectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*") //frontend link
public class UserAPI {

    @Autowired
    UserService userService;


    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {

        HashMap<String, Object> result = new HashMap<String, Object>();

        try{
            List<User> data = userService.getAllUsers();
            result.put("success", true);
            result.put("message", "Call API getAllUsers successfully111111");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Call API getAllUsers fail");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }



}
