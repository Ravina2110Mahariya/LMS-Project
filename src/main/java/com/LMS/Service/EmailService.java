package com.LMS.Service;

import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.LMS.Entity.Otp;
import com.LMS.Repository.OtpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final OtpRepository otpRepo;

    // SEND OTP
    public String sendOtp(String email) {

        String otp = String.valueOf(
                100000 + new Random().nextInt(900000)
        );

        Otp otpEntity = new Otp();

        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);

        otpRepo.save(otpEntity);

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(email);

        message.setSubject(
                "LMS OTP Verification"
        );

        message.setText(
                "Your OTP is: " + otp
        );

        mailSender.send(message);

        return "OTP Sent Successfully";
    }

    // VERIFY OTP
    public String verifyOtp(
            String email,
            String otp) {

        Otp savedOtp = otpRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "OTP not found"
                        ));

        if (!savedOtp.getOtp().equals(otp)) {

            throw new RuntimeException(
                    "Invalid OTP"
            );
        }

        return "OTP Verified Successfully";
    }
}