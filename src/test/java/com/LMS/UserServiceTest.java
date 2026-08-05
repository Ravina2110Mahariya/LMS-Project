package com.LMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.LMS.Entity.User;
import com.LMS.Exception.UserNotFoundException;
import com.LMS.Repository.UserRepository;
import com.LMS.Security.JwtUtil;
import com.LMS.Service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserService service;

    @Test
    void testGetUserById() {

        User user = new User();
        user.setId("1");
        user.setEmail("test@gmail.com");

        when(repo.findById("1"))
                .thenReturn(Optional.of(user));

        User result = service.getUserById("1");

        assertNotNull(result);
        assertEquals(
                "test@gmail.com",
                result.getEmail()
        );
    }

    @Test
    void testUserNotFound() {

        when(repo.findById("10"))
                .thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> service.getUserById("10")
        );
    }
}