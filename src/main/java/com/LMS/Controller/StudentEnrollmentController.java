package com.LMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.LMS.Entity.Enrollment;
import com.LMS.Service.EnrollmentService;

@Controller
public class StudentEnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/student/enroll")
    public String enrollCourse(
            @RequestParam String courseId,
            RedirectAttributes redirectAttributes) {

        try {

            Enrollment e = new Enrollment();

            e.setCourseId(courseId);

            enrollmentService.enroll(e);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Course enrolled successfully!"
            );

        } catch (Exception ex) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    ex.getMessage()
            );
        }

        return "redirect:/student/courses";
    }
}

