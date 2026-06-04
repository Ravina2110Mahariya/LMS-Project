package com.LMS.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.LMS.Entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // ✅ Find user by email (login ke liye)
    Optional<User> findByEmail(String email);
}