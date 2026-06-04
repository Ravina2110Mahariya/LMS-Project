package com.LMS.Service;

import java.io.IOException;
import java.nio.file.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.Entity.Notes;
import com.LMS.Repository.NotesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final NotesRepository repo;

    private final String UPLOAD_DIR =
            "uploads/notes/";

    // UPLOAD NOTES
    public Notes upload(
            String title,
            String courseId,
            String uploadedBy,
            MultipartFile file
    ) throws IOException {

        Files.createDirectories(
                Paths.get(UPLOAD_DIR)
        );

        String fileName =
                file.getOriginalFilename();

        Path path = Paths.get(
                UPLOAD_DIR + fileName
        );

        Files.write(
                path,
                file.getBytes()
        );

        Notes notes = new Notes();

        notes.setTitle(title);
        notes.setCourseId(courseId);
        notes.setUploadedBy(uploadedBy);
        notes.setFileName(fileName);
        notes.setFilePath(path.toString());

        return repo.save(notes);
    }

    // GET ALL NOTES
    public List<Notes> getAll() {

        return repo.findAll();
    }

    // COURSE NOTES
    public List<Notes> getByCourse(
            String courseId) {

        return repo.findByCourseId(
                courseId
        );
    }
}