package com.LMS.Controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.Service.NotesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/notes")
@RequiredArgsConstructor
public class AdminNotesController {

    private final NotesService notesService;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute(
                "notes",
                notesService.getAll());

        return "admin/notes-list";
    }

    @GetMapping("/add")
    public String add() {

        return "admin/add-notes";
    }

    @PostMapping("/save")
    public String save(

            @RequestParam String title,
            @RequestParam String courseId,
            @RequestParam String uploadedBy,
            @RequestParam MultipartFile file

    ) throws IOException {

        notesService.upload(
                title,
                courseId,
                uploadedBy,
                file);

        return "redirect:/admin/notes";
    }

}