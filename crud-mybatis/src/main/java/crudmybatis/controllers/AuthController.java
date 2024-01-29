package crudmybatis.controllers;

import crudmybatis.exception.RandomString;
import crudmybatis.exception.UserNotFoundException;
import crudmybatis.models.ERole;
import crudmybatis.models.Role;
import crudmybatis.models.User;
import crudmybatis.models.UserRole;
import crudmybatis.payload.request.ForgotRequest;
import crudmybatis.payload.request.LoginRequest;
import crudmybatis.payload.request.RegisterRequest;
import crudmybatis.payload.request.ResetPasswordRequest;
import crudmybatis.payload.response.JwtResponse;
import crudmybatis.payload.response.MessageResponse;
import crudmybatis.payload.response.UserResponse;
import crudmybatis.security.jwt.JwtUtils;
import crudmybatis.security.services.UserDetailsImpl;
import crudmybatis.services.RoleService;
import crudmybatis.services.UserRoleService;
import crudmybatis.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Set<Role> roles = userService.getRoles(userDetails.getUsername());
            List<String> strRoles = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), strRoles));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            if (userService.existsByUsername(registerRequest.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
            }
            if (userService.existsByEmail(registerRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
            }
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(encoder.encode(registerRequest.getPassword()));
            user.setName(registerRequest.getName());
            user.setEmail(registerRequest.getEmail());
            user.setAge(registerRequest.getAge());
            user.setAddress(registerRequest.getAddress());
            user.setGender(registerRequest.getGender());
            user.setPhoneNumber(registerRequest.getPhoneNumber());
            user.setActive(true);
            user.setCreatedAt(new Date());
            Set<String> strRoles = registerRequest.getRoles();
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleService.findByName(String.valueOf(ERole.ROLE_USER));
                if (userRole == null) {
                    throw new RuntimeException("Error: Role is not found.");
                }
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleService.findByName(String.valueOf(ERole.ROLE_ADMIN));
                            if (adminRole == null) {
                                throw new RuntimeException("Error: Role is not found.");
                            }
                            roles.add(adminRole);
                            break;
                        default:
                            Role userRole = roleService.findByName(String.valueOf(ERole.ROLE_USER));
                            if (userRole == null) {
                                throw new RuntimeException("Error: Role is not found.");
                            }
                            roles.add(userRole);
                    }
                });
            }
            userService.insertUser(user);
            User savedUser = userService.findByUsername(user.getUsername());
            roles.forEach(role -> {
                UserRole userRole = new UserRole(savedUser.getId(), role.getId());
                userRoleService.insertUserRole(userRole);
            });
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getUserInfo(Authentication auth) {
        try {
            User user = userService.findByUsername(auth.getName());
            Set<Role> roles = userService.getRoles(auth.getName());
            List<String> strRoles = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setUsername(user.getUsername());
            userResponse.setName(user.getName());
            userResponse.setAddress(user.getAddress());
            userResponse.setAge(user.getAge());
            userResponse.setActive(user.isActive());
            userResponse.setEmail(user.getEmail());
            userResponse.setPhoneNumber(user.getPhoneNumber());
            userResponse.setCreatedAt(user.getCreatedAt());
            userResponse.setUpdatedAt(user.getUpdatedAt());
            userResponse.setGender(user.getGender());
            userResponse.setRoles(strRoles);
            return ResponseEntity.ok().body(userResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
    }


//    @PutMapping("/verify-account")
//    public ResponseEntity<String> verifyAccount(@RequestParam String email,
//                                                @RequestParam String otp) {
//        return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
//    }
//    @PutMapping("/regenerate-otp")
//    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
//        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
//    }


//    @GetMapping("/forgot_password")
//    public String showForgotPasswordForm() {
//        return "forgot_password_form";
//    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPassword(@Valid @RequestBody ForgotRequest request) {

        try {
            if (!userService.existsByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Email not found!"));
            } else {
                String email = request.getEmail();
                String token = RandomString.getAlphaNumericString(30);

                userService.updateResetPasswordToken(token, email);
                String resetPasswordLink = "http://localhost:8080/password/reset_password_form.html?email=" + email + "&token=" + token;

                System.out.println(resetPasswordLink);
                sendEmail(email, resetPasswordLink);
            }


            return ResponseEntity.ok(new MessageResponse("successfully!"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public void sendEmail(String email, String link)
            throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Here's the link to reset your password");
        mimeMessageHelper.setText("""
                <div>
                  <p>Hello,</p>
                  <p>You have requested to reset your password.</p>
                  <p>Click the link below to change your password:</p>
                  <p><a href="%s" target="_blank">Change my password</a>
                </div>
                """.formatted(link), true);

        mailSender.send(mimeMessage);
        System.out.println("Email sending complete.");
    }

//    @GetMapping("/reset_password")
//    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
//        User user = userService.getByResetPasswordToken(token);
//        model.addAttribute("token", token);
//
//        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
//            return "message";
//        }
//
//        return "reset_password_form";
//    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> processResetPassword(@Valid @RequestBody ResetPasswordRequest request) {

        User user = userService.getByResetPasswordToken(request.getToken());
        if (user == null) {
            return ResponseEntity.ok(new MessageResponse("!!!!"));
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            user.setPassword(encodedPassword);
            user.setRememberToken(null);
            userService.updatePassword(user);
        }
        return ResponseEntity.ok(new MessageResponse("successfully!"));
    }


}
