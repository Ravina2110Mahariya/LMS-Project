package com.LMS.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Attendance;

public interface AttendanceRepository
        extends MongoRepository<Attendance, String> {

    List<Attendance> findByStudentEmail(
            String studentEmail
    );

    List<Attendance> findByCourseId(
            String courseId
    );
}