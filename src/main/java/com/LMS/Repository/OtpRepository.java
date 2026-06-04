package com.LMS.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Otp;

public interface OtpRepository
        extends MongoRepository<Otp, String> {

    Optional<Otp> findByEmail(String email);
}