package com.LMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.LMS.Entity.User;
import com.LMS.Exception.UserNotFoundException;
import com.LMS.Repository.UserRepository;
import com.LMS.Security.JwtUtil;
import com.LMS.dto.AuthResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // =========================
    // REGISTER
    // =========================
    public User register(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repo.save(user);
    }

    // =========================
    // LOGIN
    // =========================
    public AuthResponse login(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        User user = repo.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with email : " + email
                        ));

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole()
        );
    }

    // =========================
    // FORGOT PASSWORD
    // =========================
    public String forgotPassword(String email) {

        repo.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with email : " + email
                        ));

        return "Reset token generated";
    }

    // =========================
    // RESET PASSWORD
    // =========================
    public String resetPassword(
            String email,
            String newPassword) {

        User user = repo.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with email : " + email
                        ));

        user.setPassword(
                passwordEncoder.encode(newPassword)
        );

        repo.save(user);

        return "Password updated successfully";
    }

    // =========================
    // GET ALL USERS
    // =========================
    public List<User> getAllUsers() {

        return repo.findAll();
    }

    // =========================
    // GET USER BY ID
    // =========================
    public User getUserById(String id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id : " + id
                        ));
    }
}