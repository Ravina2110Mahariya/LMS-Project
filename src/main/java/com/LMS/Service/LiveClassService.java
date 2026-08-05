package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.LiveClass;
import com.LMS.Repository.LiveClassRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LiveClassService {

    private final LiveClassRepository repository;

    public LiveClass save(LiveClass liveClass) {
        return repository.save(liveClass);
    }

    public List<LiveClass> getAll() {
        return repository.findAll();
    }

    public LiveClass getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}