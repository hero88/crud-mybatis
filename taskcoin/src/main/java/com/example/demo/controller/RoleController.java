package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/{name}")
    public ResponseEntity<Role> findByUsername(@PathVariable String name){
        Role role = roleService.findByName(name);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleService.findByName(name));
    }
}
