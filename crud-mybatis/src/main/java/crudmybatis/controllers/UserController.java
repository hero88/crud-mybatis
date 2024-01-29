package crudmybatis.controllers;

import crudmybatis.models.Role;
import crudmybatis.models.User;
import crudmybatis.payload.request.UserRequest;
import crudmybatis.payload.response.MessageResponse;
import crudmybatis.services.CoinService;
import crudmybatis.services.RoleService;
import crudmybatis.services.UserRoleService;
import crudmybatis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private CoinService coinService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.selectAllUsers();
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserRequest userRequest) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            user.setUsername(userRequest.getUsername());
            String password = userRequest.getPassword().trim();
            if (password.length() >= 6) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(password);
                user.setPassword(encodedPassword);
            }
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setAddress(userRequest.getAddress());
            user.setAge(userRequest.getAge());
            user.setUpdatedAt(new Date());
            userService.updateUser(user);
            return ResponseEntity.ok().body(new MessageResponse("User updated successfully."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserById(Authentication auth, @PathVariable long id) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            if (Objects.equals(auth.getName(), user.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Can't delete your account"));
            }
            coinService.deleteCoinByUserId(user.getId());
            userRoleService.deleteUserRoleByUserId(user.getId());
            userService.deleteUserById(user.getId());
            return ResponseEntity.ok().body(new MessageResponse("User deleted successfully."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }
}
