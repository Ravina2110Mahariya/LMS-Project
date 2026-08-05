package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Announcement;
import com.LMS.Repository.AnnouncementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository repo;

    public Announcement save(
            Announcement announcement) {

        return repo.save(announcement);
    }

    public List<Announcement> getAll() {

        return repo.findAll();
        
    }
    
    public void delete(String id) {
        repo.deleteById(id);
    }
}