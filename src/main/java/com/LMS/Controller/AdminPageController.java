package com.LMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.LMS.Entity.Course;
import com.LMS.Repository.AssignmentRepository;
import com.LMS.Repository.CertificateRepository;
import com.LMS.Repository.CourseRepository;
import com.LMS.Repository.EnrollmentRepository;
import com.LMS.Repository.LiveClassRepository;
import com.LMS.Repository.QuizRepository;
import com.LMS.Repository.ReviewRepository;
import com.LMS.Repository.UserRepository;
import com.LMS.Service.AssignmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminPageController {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AssignmentRepository assignmentRepository;
    private final QuizRepository quizRepository;
    private final CertificateRepository certificateRepository;
    private final ReviewRepository reviewRepository;
    private final AssignmentService assignmentService;
    private final LiveClassRepository liveClassRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("students",
                userRepository.count());

        model.addAttribute("courses",
                courseRepository.count());

        model.addAttribute("enrollments",
                enrollmentRepository.count());

        model.addAttribute("assignments",
                assignmentRepository.count());

        model.addAttribute("quizzes",
                quizRepository.count());

        model.addAttribute("certificates",
                certificateRepository.count());

        model.addAttribute("reviews",
                reviewRepository.count());

        model.addAttribute("liveClasses",
                liveClassRepository.count());

        return "admin/dashboard";
    }
    
 // =========================
 // ALL COURSES
 // =========================
 @GetMapping("/admin/courses")
 public String courses(Model model) {

     model.addAttribute(
             "courses",
             courseRepository.findAll());

     model.addAttribute(
             "course",
             new Course());

     return "admin/courses";
 }
 
//=========================
//USERS MANAGEMENT
//=========================
@GetMapping("/admin/users")
public String users(Model model) {

  model.addAttribute(
          "users",
          userRepository.findAll());

  return "admin/users";
}

//=========================
//Delete User
//=========================
@GetMapping("/admin/user/delete/{id}")
public String deleteUser(
        @PathVariable String id) {

    userRepository.deleteById(id);

    return "redirect:/admin/users";
}
//=========================
//Search User (Optional)
//=========================
@GetMapping("/admin/users/search")
public String searchUsers(
        @RequestParam String keyword,
        Model model) {

    model.addAttribute(
            "users",
            userRepository.findByNameContainingIgnoreCase(keyword));

    return "admin/users";
}

}