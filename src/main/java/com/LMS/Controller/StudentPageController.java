 package com.LMS.Controller;

import java.security.Principal;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LMS.Entity.AssignmentSubmission;
import com.LMS.Entity.Quiz;
import com.LMS.Entity.QuizResult;
import com.LMS.Entity.User;
import com.LMS.Repository.CertificateRepository;
import com.LMS.Repository.ChatRepository;
import com.LMS.Repository.QuizResultRepository;
import com.LMS.Repository.UserRepository;
import com.LMS.Service.AnnouncementService;
import com.LMS.Service.AssignmentService;
import com.LMS.Service.AssignmentSubmissionService;
import com.LMS.Service.AttendanceService;
import com.LMS.Service.CourseContentService;
import com.LMS.Service.DashboardService;
import com.LMS.Service.DiscussionService;
import com.LMS.Service.EnrollmentService;
import com.LMS.Service.LiveClassService;
import com.LMS.Service.NotificationService;
import com.LMS.Service.ProgressService;
import com.LMS.Service.QuizService;
import com.LMS.Service.ReviewService;
import com.LMS.Service.VideoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentPageController {

    @Autowired
    private DashboardService dashboardService;
    
    
    @Autowired
    private CourseContentService courseContentService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private AssignmentService assignmentService;
    
    @Autowired
    private AssignmentSubmissionService assignmentSubmissionService;
    
    @Autowired
    private QuizService quizService;
    
    @Autowired
    private QuizResultRepository quizResultRepository;

    @Autowired
    private ProgressService progressService;
    
    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private LiveClassService liveClassService;
    
    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private DiscussionService discussionService;
    
    @Autowired
    private AnnouncementService announcementService;
    
    @Autowired
    private VideoService videoService;
    
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private UserRepository userRepository;

    // =========================
    // DASHBOARD
    // =========================
    
    @GetMapping("/student/dashboard")
    public String dashboard(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        // Existing Dashboard Stats
        model.addAttribute(
                "stats",
                dashboardService.dashboard(email));

        // Quiz Stats
        List<QuizResult> results =
                quizResultRepository.findByStudentEmail(email);

        int totalScore = results.stream()
                .mapToInt(QuizResult::getScore)
                .sum();

        int totalQuizzes = results.size();

        double percentage = totalQuizzes == 0
                ? 0
                : (double) totalScore / (totalQuizzes * 10) * 100;

        model.addAttribute("totalQuizScore", totalScore);
        model.addAttribute("quizPercentage", percentage);

        return "student/dashboard";
    }
    
    // =========================
    //  COURSES
    // =========================
    @GetMapping("/student/courses")
    public String courses(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        model.addAttribute(
                "courses",
                enrollmentService.getMyCoursesByEmail(email)
        );

        return "student/my-courses";
    }
    
    // =========================
    // MY COURSES
    // =========================
    @GetMapping("/student/my-courses")
    public String myCourses(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        model.addAttribute(
                "courses",
                enrollmentService.getMyCoursesByEmail(email)
                );

        return "student/my-courses";
    }
    
    @GetMapping("/student/course-content/{courseId}")
    public String courseContent(
            @PathVariable String courseId,
            Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        model.addAttribute("courseId", courseId);

        model.addAttribute(
                "contents",
                courseContentService.getContent(courseId, email)
        );

        return "student/course-content";
    }

    // =========================
    // ASSIGNMENTS
    // =========================
    @GetMapping("/student/assignments")
    public String assignments(Model model) {

        model.addAttribute(
                "assignments",
                assignmentService.getAll());

        return "student/assignments";
    }
    
     // =========================
    // MY-ASSIGNMENTS
    // =========================
    
    @GetMapping("/student/my-assignments")
    public String myAssignments(Model model) {

        model.addAttribute(
                "assignments",
                assignmentService.getAllAssignments()
        );

        return "student/my-assignments";
    }
    
     // =========================
     // SUMIT-ASSIGNMENTS
    // =========================
    
    @PostMapping("/submit-assignment")
    public String submitAssignment(
            @RequestParam String assignmentId,
            @RequestParam String answer,
            Principal principal) {

        AssignmentSubmission submission =
                new AssignmentSubmission();

        submission.setAssignmentId(assignmentId);
        submission.setStudentEmail(principal.getName());
        submission.setAnswer(answer);
        submission.setStatus("SUBMITTED");

        assignmentSubmissionService.save(submission);

        return "redirect:/student/my-assignments";
    }
    
     // =========================
    // SUMIT-ASSIGNMENTS assignmentId
   // =========================
   

    @GetMapping("/student/submit-assignment/{assignmentId}")
    public String showSubmitAssignmentPage(
            @PathVariable String assignmentId,
            Model model) {

        model.addAttribute("assignmentId", assignmentId);

        return "student/submit-assignment";
    }
    
    
    
    // =========================
    // MY SUBMISSIONS
    // =========================
    @GetMapping("/student/my-submissions")
    public String mySubmissions(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        model.addAttribute(
                "submissions",
                assignmentSubmissionService
                        .getByStudentEmail(email));

        return "student/my-submissions";
    }
    
    // =========================
    // QUIZZES 
    // =========================
    
    @GetMapping("/student/quizzes")
    public String quizzes(Model model) {

        List<Quiz> quizzes = quizService.getAll();

        System.out.println("QUIZ SIZE = " + quizzes.size());

        model.addAttribute("quizzes", quizzes);

        return "student/quizzes";
    }
    
     // =========================
    // QUIZZES BY ID
    // =========================
    @GetMapping("/student/start-quiz/{quizId}")
    public String startQuiz(
            @PathVariable String quizId,
            Model model) {

        model.addAttribute(
                "quiz",
                quizService.getById(quizId));

        return "student/start-quiz";
    }
    
     // =========================
    // QUIZZES QUIZ
    // =========================
    @PostMapping("/student/submit-quiz")
    public String submitQuiz(
            @RequestParam String quizId,
            @RequestParam String answer,
            Principal principal,
            Model model) {

        Quiz quiz = quizService.getById(quizId);

        boolean correct =
                quiz.getCorrectAnswer()
                .equalsIgnoreCase(answer);

        QuizResult result = new QuizResult();

        result.setQuizId(quizId);
        result.setStudentEmail(principal.getName());
        result.setSelectedAnswer(answer);
        result.setCorrect(correct);
        result.setScore(correct ? 10 : 0);

        quizResultRepository.save(result);

        model.addAttribute("score", correct ? 10 : 0);
        model.addAttribute("message",
                correct ? " Correct Answer!"
                        : " Wrong Answer!");

        return "student/result";
    }    
    // =========================
    // QUIZZES Results Page
    // =========================
    @GetMapping("/student/quiz-results")
    public String quizResults(
            Principal principal,
            Model model) {

        model.addAttribute(
                "results",
                quizResultRepository.findByStudentEmail(
                        principal.getName()));

        return "student/quiz-results";
    }
    
    // =========================
    // PROGRESS
    // =========================
    @GetMapping("/student/progress")
    public String progress(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        model.addAttribute(
                "progressList",
                progressService.getMyProgress(email));

        return "student/progress";
    }
    
 // =========================
 // STUDENT ATTENDANCE
 // =========================
 @GetMapping("/student/attendance")
 public String attendance(
         Model model,
         Authentication auth) {

     model.addAttribute(
             "attendance",
             attendanceService.myAttendance(auth.getName())
     );

     return "student/attendance";
 }

    // =========================
    // CERTIFICATES
    // =========================
    @GetMapping("/student/certificates")
    public String certificates(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        model.addAttribute(
                "certificates",
                certificateRepository.findByStudentEmail(email));

        return "student/certificates";
    }
    

    // =========================
    // NOTES
    // =========================
    @GetMapping("/student/notes")
    public String notes() {

        return "student/notes";
    }
    
    
    // =========================
    // LIVE CLASSES
    // =========================
    @GetMapping("/student/live-classes")
    public String liveClasses(Model model) {

        model.addAttribute(
                "classes",
                liveClassService.getAll());

        return "student/live-classes";
    }
    
     // =========================
    // CHAT 
    // =========================
    
    @GetMapping("/student/chat")
    public String chat(Model model) {

        model.addAttribute(
                "messages",
                chatRepository.findByCourseId("JAVA101"));

        return "student/chat";
    }

    // =========================
    // CHAT ID
    // =========================
    
    @GetMapping("/student/chat/{courseId}")
    public String chat(
            @PathVariable String courseId,
            Model model) {

        model.addAttribute("courseId", courseId);

        model.addAttribute(
                "messages",
                chatRepository.findByCourseId(courseId));

        return "student/chat";
    }
    
    // =========================
    // NOTIFICATIONS
    // =========================
    
    @GetMapping("/student/notifications")
    public String notifications(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        model.addAttribute(
                "notifications",
                notificationService.myNotifications(email));

        return "student/notifications";
    }
    
    // =========================
    // REVIEWS
    // =========================
    @GetMapping("/student/reviews")
    public String reviews(Model model) {

        model.addAttribute(
                "reviews",
                reviewService.getAll());

        return "student/reviews";
    }
    
    
 // =========================
 // DISCUSSIONS
 // =========================
 @GetMapping("/student/discussions")
 public String discussions(Model model) {

     model.addAttribute(
             "discussions",
             discussionService.getAll());

     return "student/discussions";
 }
 
//=========================
//ANNOUNCEMENTS
//=========================

@GetMapping("/student/announcements")
public String announcements(Model model) {

  model.addAttribute(
          "announcements",
          announcementService.getAll());

  return "student/announcements";
}
 

//=========================
//VIDEOS
//=========================

@GetMapping("/student/videos")
public String videos(Model model) {

 model.addAttribute(
         "videos",
         videoService.getAll());

 return "student/videos";
}
 
    // =========================
    // PROFILE
    // =========================
    
    @GetMapping("/student/profile")
    public String profile(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        model.addAttribute("user", user);

        return "student/profile";
    }
    }