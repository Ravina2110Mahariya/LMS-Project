package com.LMS.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.LMS.Entity.Announcement;

public interface AnnouncementRepository
        extends MongoRepository<Announcement, String> {

}