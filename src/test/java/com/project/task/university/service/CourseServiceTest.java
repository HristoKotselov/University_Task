package com.project.task.university.service;

import com.project.task.university.UniversityApplication;
import com.project.task.university.model.Course;
import com.project.task.university.model.CourseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Rollback
@Transactional
@SpringBootTest(classes = UniversityApplication.class)
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    void getCourseByIdTest() {
        Optional<Course> course = courseService.getCourseById(1L);
        assertTrue(course.isPresent());
    }

    @Test
    void addCourseTest() {
        Course course = Course.builder().courseName("Something").courseType(CourseType.SECONDARY).build();
        Course addedCourse = courseService.addCourse(course);

        assertNotNull(addedCourse);

        Optional<Course> existingCourse = courseService.getCourseById(addedCourse.getCourseId());
        assertTrue(existingCourse.isPresent());
    }

    @Test
    void deleteCourseByIdTest() {
        final Optional<Course> existingCourse = courseService.getCourseById(1L);
        assertTrue(existingCourse.isPresent());

        courseService.deleteCourseById(1L);
        final Optional<Course> checkCourse = courseService.getCourseById(1L);
        assertFalse(checkCourse.isPresent());
    }

    @Test
    void deleteCourseTest() {
        final Optional<Course> existingCourse = courseService.getCourseById(1L);
        assertTrue(existingCourse.isPresent());

        courseService.deleteCourse(existingCourse.get());
        final Optional<Course> checkCourse = courseService.getCourseById(existingCourse.get().getCourseId());
        assertFalse(checkCourse.isPresent());
    }

    @Test
    void getTotalCoursesCountTest() {
        final long count = courseService.getTotalCoursesCount();
        assertEquals(10, count);
    }

    @Test
    void getAllCoursesTest() {
        final List<Course> courses = courseService.getAllCourses();
        assertEquals(10, courses.size());
    }

    @Test
    void getAllCoursesByType() {
        final List<Course> mainCourses = courseService.getAllCoursesByType(CourseType.MAIN);
        assertEquals(8, mainCourses.size());

        final List<Course> secondaryCourses = courseService.getAllCoursesByType(CourseType.SECONDARY);
        assertEquals(2, secondaryCourses.size());
    }

}
