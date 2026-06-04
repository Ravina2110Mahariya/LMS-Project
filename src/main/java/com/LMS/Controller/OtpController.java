package com.LMS.Controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

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