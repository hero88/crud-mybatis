package com.allxone.mybatisprojectservice.controller;

import com.allxone.mybatisprojectservice.model.Users;
import com.allxone.mybatisprojectservice.repository.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UsersDAO usersDAO;

    @Autowired
    public UserController(UsersDAO usersDAO){
        this.usersDAO = usersDAO;
    }

    @GetMapping("/{userId}")
    public Users findByUserId(@PathVariable Long userId) {
        return usersDAO.selectUserById(userId);
    }

    @GetMapping("/userName/{userName}")
    public Users findByUserName(@PathVariable String userName) {
        return usersDAO.selectUserByUserName(userName);
    }
}
