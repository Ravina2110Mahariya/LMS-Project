package com.LMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.LMS.Entity.User;
import com.LMS.Service.UserService;
import com.LMS.dto.AuthResponse;
import com.LMS.dto.LoginRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    // =========================
    // REGISTER USER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody User user) {

        return ResponseEntity.ok(
                service.register(user)
        );
    }

    // =========================
    // LOGIN USER
    // =========================
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                service.login(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }

    // =========================
    // FORGOT PASSWORD
    // =========================
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                service.forgotPassword(
                        request.getEmail()
                )
        );
    }

    // =========================
    // RESET PASSWORD
    // =========================
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                service.resetPassword(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }

    // =========================
    // GET ALL USERS (ADMIN)
    // =========================
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {

        return service.getAllUsers();
    }

    // =========================
    // GET USER BY ID (ADMIN)
    // =========================
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public User getUserById(
            @PathVariable String id) {

        return service.getUserById(id);
    }

    // =========================
    // STUDENT API
    // =========================
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/student")
    public String studentApi() {

        return "Student Access Successful";
    }
    
 // LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        return ResponseEntity.ok(
                "Logout Successful"
        );
    }
}