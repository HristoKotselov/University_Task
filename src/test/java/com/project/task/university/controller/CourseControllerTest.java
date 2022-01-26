package com.project.task.university.controller;

import com.project.task.university.UniversityApplication;
import com.project.task.university.model.Course;
import com.project.task.university.model.CourseType;
import com.project.task.university.model.Student;
import com.project.task.university.service.CourseService;
import com.project.task.university.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.project.task.university.objects.TestObjectCreator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UniversityApplication.class)
public class CourseControllerTest {

    @Autowired
    private CourseController courseController;

    @MockBean
    private CourseService courseService;

    @MockBean
    private StudentService studentService;

    @Test
    void getAllCoursesByTypeTest() {
        final List<Course> mainCourses = createTestCourseListMain();

        when(courseService.getAllCoursesByType(CourseType.MAIN)).then(x -> mainCourses);

        final ResponseEntity<List<Course>> responseMain = courseController.getAllCoursesByType(CourseType.MAIN.toString());
        assertEquals(responseMain.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseMain.getBody());
        assertEquals(responseMain.getBody().size(), mainCourses.size());

        //verify
        verify(courseService, times(1)).getAllCoursesByType(CourseType.MAIN);
    }

    @Test
    void getAllCoursesByTypeAllTest() {
        final List<Course> allCourses = createTestCourseListAll();

        when(courseService.getAllCourses()).then(x -> allCourses);

        final ResponseEntity<List<Course>> responseAll = courseController.getAllCoursesByType(null);
        assertEquals(responseAll.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseAll.getBody());
        assertEquals(responseAll.getBody().size(), allCourses.size());

        //verify
        verify(courseService, times(1)).getAllCourses();
    }

    @Test
    void getAllCoursesByTypeBadRequestTest() {
        final ResponseEntity<List<Course>> responseAll = courseController.getAllCoursesByType("Third");
        assertEquals(responseAll.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(responseAll.getBody());
    }

    @Test
    void getAllStudentsByCourseIdTest() {
        final List<Course> courses = createTestCourseListAll();
        final List<Student> students = createTestStudentList(courses);

        when(studentService.getStudentsByCourseId(courses.get(0).getCourseId())).then(x -> students);

        final ResponseEntity<List<Student>> responseMain = courseController.getAllStudentsByCourseId(courses.get(0).getCourseId());
        assertEquals(responseMain.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseMain.getBody());
        assertEquals(responseMain.getBody().size(), students.size());

        //verify
        verify(studentService, times(1)).getStudentsByCourseId(courses.get(0).getCourseId());
    }
}
