package com.allxone.coinmarket.controller;

import com.allxone.coinmarket.dto.request.PasswordDTO;
import com.allxone.coinmarket.dto.request.UserDTO;
import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.model.Users;
import com.allxone.coinmarket.service.UserService;
import com.allxone.coinmarket.utilities.StatusResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    private final ModelMapper mapDTO;

    @GetMapping("/{username}")
    public ResponseEntity<?> getOneUser(@PathVariable String username) {
        Users users = userService.getOneUserByUsername(username);
        if (users != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("successful")
                    .success(true)
                    .data(users)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .message("User dose not exist")
                .success(false)
                .build());
    }

    @GetMapping()
    public ResponseEntity<?> getAllUser() {
        List<Users> users = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .message("successful")
                .success(true)
                .data(users)
                .build());
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        Users user = mapDTO.map(userDTO, Users.class);
        if (userService.createUser(user)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
                    .message("successful")
                    .success(true)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .message("Create Fail, Email exist")
                .success(false)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        Users user = mapDTO.map(userDTO, Users.class);
        Users userUpdate = userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .message("Update Fail")
                .success(true)
                .data(userUpdate)
                .build());
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                    .message("successful")
                    .success(true)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .message("Delete Fail")
                .success(false)
                .build());
    }

    @PatchMapping()
    public ResponseEntity<?> changePassword(@RequestBody PasswordDTO passwordDTO, HttpServletRequest request) {
        int status = userService.changePassword(passwordDTO,request);
        return StatusResponse.setStatus(status);
    }
}
