
package com.LMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.LMS.Entity.User;
import com.LMS.Service.UserService;

@Controller
public class AuthPageController {

    @Autowired
    private UserService userService;

    // Register Page
    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    // Register Submit
    @PostMapping("/register")
    public String register(User user) {

        System.out.println("===== REGISTER HIT =====");
        System.out.println("NAME = " + user.getName());
        System.out.println("EMAIL = " + user.getEmail());
        System.out.println("ROLE = " + user.getRole());

        userService.register(user);

        return "redirect:/login";
    }

    // Login Page
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }
}

