package com.LMS.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Report;

public interface ReportRepository
        extends MongoRepository<Report, String>{

}