package com.LMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.Entity.User;
import com.LMS.Repository.UserRepository;

@RestController
public class FixAdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/fix-admin")
    @ResponseBody
    public String fixAdmin() {

        User user = userRepository
                .findByEmail("riya@gmail.com")
                .orElse(null);

        if (user != null) {

            user.setPassword(
                    new BCryptPasswordEncoder()
                            .encode("123456")
            );

            user.setRole("ADMIN");

            userRepository.save(user);

            return "Admin Updated Successfully";
        }

        return "Admin Not Found";
    }
}