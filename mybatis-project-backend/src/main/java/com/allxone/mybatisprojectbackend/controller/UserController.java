package com.allxone.mybatisprojectbackend.controller;

import com.allxone.mybatisprojectbackend.common.dto.CommonResponse;
import com.allxone.mybatisprojectbackend.convert.UserConvert;
import com.allxone.mybatisprojectbackend.dto.request.ChangePasswordRequest;
import com.allxone.mybatisprojectbackend.dto.request.UserRequest;
import com.allxone.mybatisprojectbackend.dto.response.UserResponse;
import com.allxone.mybatisprojectbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/changePassword")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllUsers")
//    @PreAuthorize("hasAuthority('admin:read')")
    public CommonResponse<List<UserResponse>> getAllUsers() {
        try {
            List<UserResponse> data = userService.getAllUsers();
            return CommonResponse.success(data);
        } catch (Exception e) {
            System.out.println(e);
            return CommonResponse.error(null);
        }
    }


    @DeleteMapping("/deleteUserById/{id}")
//    @PreAuthorize("hasAuthority('admin:delete')")
    public CommonResponse<Long> deleteUserById(@PathVariable Long id) {

        try {
            userService.deleteUserById(id);
            return CommonResponse.success(id);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

    @PutMapping("/updateUser")
//    @PreAuthorize("hasAnyAuthority('admin:update','user:update')")
    public CommonResponse<UserResponse> updateUser(@RequestBody UserRequest userRequest) {

        try {
            UserResponse data = userService.updateUser(userRequest);
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }

    }

    @GetMapping("/getUser/{id}")
//    @PreAuthorize("hasAnyAuthority('admin:read','user:read')")
    public CommonResponse<UserResponse> getUser(@PathVariable Long id) {
        try {
            UserResponse data = UserConvert.toDto(userService.getUserById(id));
            return CommonResponse.success(data);
        } catch (Exception e) {
            return CommonResponse.error(null);
        }
    }

}
