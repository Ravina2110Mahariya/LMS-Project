package com.LMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.Entity.Report;
import com.LMS.Repository.ReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository repo;

    public List<Report> getAll() {
        return repo.findAll();
    }

    public Report save(Report report) {
        return repo.save(report);
    }

    public Report getById(String id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Report Not Found"));
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}