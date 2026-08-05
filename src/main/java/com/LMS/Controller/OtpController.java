package com.LMS.Controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.Service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {

    private final EmailService service;

    // SEND OTP
    @PostMapping("/send")
    public String sendOtp(
            @RequestBody Map<String, String> body) {

        return service.sendOtp(
                body.get("email")
        );
    }

    // VERIFY OTP
    @PostMapping("/verify")
    public String verifyOtp(
            @RequestBody Map<String, String> body) {

        return service.verifyOtp(
                body.get("email"),
                body.get("otp")
        );
    }
}