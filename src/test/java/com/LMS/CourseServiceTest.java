package com.LMS;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.LMS.Entity.Course;
import com.LMS.Exception.CourseNotFoundException;
import com.LMS.Exception.ResourceAlreadyExistsException;
import com.LMS.Repository.CourseRepository;
import com.LMS.Service.CourseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository repo;

    @InjectMocks
    private CourseService service;

    private Course course;

    @BeforeEach
    void setup() {

        course = new Course();

        course.setId("1");
        course.setTitle("Java");
        course.setDescription("Core Java");
        course.setInstructor("Ravina");
        course.setPrice(999);
    }

    @Test
    void testGetCourseById() {

        when(repo.findById("1"))
                .thenReturn(Optional.of(course));

        Course result =
                service.getCourseById("1");

        assertNotNull(result);
        assertEquals("Java",
                result.getTitle());
    }

    @Test
    void testCourseNotFound() {

        when(repo.findById("100"))
                .thenReturn(Optional.empty());

        assertThrows(
                CourseNotFoundException.class,
                () -> service.getCourseById("100")
        );
    }

    @Test
    void testAddCourse() {

        when(repo.findByTitle(course.getTitle()))
                .thenReturn(Optional.empty());

        when(repo.save(course))
                .thenReturn(course);

        Course saved = service.addCourse(course);

        assertNotNull(saved);
        assertEquals("Java", saved.getTitle());

        verify(repo, times(1)).save(course);
    } 
    
    @Test
    void testDuplicateCourse() {

        when(repo.findByTitle(course.getTitle()))
                .thenReturn(Optional.of(course));

        assertThrows(
                ResourceAlreadyExistsException.class,
                () -> service.addCourse(course)
        );
    }
}