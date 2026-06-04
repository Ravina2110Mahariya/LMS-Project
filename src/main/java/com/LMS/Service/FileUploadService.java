/*package com.LMS.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) {

        try {

            // create uploads folder if not exists
            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            // file name
            String fileName = System.currentTimeMillis()
                    + "_"
                    + file.getOriginalFilename();

            // save path
            Path path = Paths.get(uploadDir, fileName);

            // copy file
            Files.copy(file.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (IOException e) {

            throw new RuntimeException("File upload failed");
        }
    }
}*/


package com.LMS.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) {

        try {

            // create folder if not exists
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // ORIGINAL FILE NAME 👇 (IMPORTANT FIX)
            String originalFileName = file.getOriginalFilename();

            // unique file name
            String fileName = System.currentTimeMillis() + "_" + originalFileName;

            // save path
            Path path = Paths.get(uploadDir, fileName);

            // save file
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // return stored file name
            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }
}