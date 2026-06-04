package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Certificate;

public interface CertificateRepository
        extends MongoRepository<Certificate, String> {

    List<Certificate> findByStudentEmail(
            String studentEmail
    );
}