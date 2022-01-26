package com.project.task.university.controller;

import com.project.task.university.UniversityApplication;
import com.project.task.university.controller.filter.StudentFilter;
import com.project.task.university.model.Course;
import com.project.task.university.model.Student;
import com.project.task.university.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.project.task.university.objects.TestObjectCreator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UniversityApplication.class)
public class StudentControllerTest {

    @Autowired
    private StudentController studentController;

    @Autowired
    private StudentFilter studentFilter;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudentByIdTest() {
        final List<Course> courseList = createTestCourseListAll();
        final Student student = createTestStudent1(courseList);
        student.setStudentId(100L);

        when(studentService.getStudentById(student.getStudentId())).then(x -> Optional.of(student));

        final ResponseEntity<Student> response = studentController.getStudentById(student.getStudentId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getFirstName(), student.getFirstName());
        assertEquals(response.getBody().getLastName(), student.getLastName());
        assertEquals(response.getBody().getAge(), student.getAge());
        assertEquals(response.getBody().getCourses(), student.getCourses());

        //verify
        verify(studentService, times(1)).getStudentById(student.getStudentId());
    }

    @Test
    void addStudentTest() {
        final List<Course> courseList = createTestCourseListAll();
        final Student student = createTestStudent1(courseList);

        when(studentService.addStudent(student)).then(x -> student);

        final ResponseEntity<Student> response = studentController.addStudent(student);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getFirstName(), student.getFirstName());
        assertEquals(response.getBody().getLastName(), student.getLastName());
        assertEquals(response.getBody().getAge(), student.getAge());
        assertEquals(response.getBody().getCourses(), student.getCourses());

        //verify
        verify(studentService, times(1)).addStudent(student);
    }

    @Test
    void addStudentBadRequestTest() {
        final List<Course> courseList = createTestCourseListAll();
        final Student student = createTestStudent1(courseList);
        student.setAge(15);

        when(studentService.addStudent(student)).then(x -> null);

        final ResponseEntity<Student> response = studentController.addStudent(student);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(response.getBody());

        //verify
        verify(studentService, times(1)).addStudent(student);
    }

    @Test
    void addStudentListTest() {
        final List<Student> students = createTestStudentList();
        Map<Boolean, List<Student>> map = studentFilter.splitFilter(students);

        when(studentService.addStudentsFromList(students)).then(x -> map);

        final ResponseEntity<Map<Boolean, List<Student>>> response = studentController.addStudentList(students);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().get(false).size(), 0);
        assertEquals(response.getBody().get(true).size(), 2);

        //verify
        verify(studentService, times(1)).addStudentsFromList(students);
    }

    @Test
    void deleteStudentTest() {
        final Student student = createTestStudent1();
        when(studentService.deleteStudent(student)).then(x -> student);

        final ResponseEntity<String> response = studentController.deleteStudent(student);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), "Student deleted successfully");

        //verify
        verify(studentService, times(1)).deleteStudent(student);
    }

    @Test
    void deleteStudentNotFoundTest() {
        final Student student = new Student();
        when(studentService.deleteStudent(student)).then(x -> null);

        final ResponseEntity<String> response = studentController.deleteStudent(student);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(response.getBody());

        //verify
        verify(studentService, times(1)).deleteStudent(student);
    }

    @Test
    void updateStudentTest() {
        final Student student = createTestStudent1();
        student.setStudentId(10L);

        when(studentService.getStudentById(student.getStudentId())).then(x -> Optional.of(student));
        when(studentService.updateStudent(student)).then(x -> student);

        final ResponseEntity<Student> response = studentController.updateStudent(student);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());

        //verify
        verify(studentService, times(1)).getStudentById(student.getStudentId());
        verify(studentService, times(1)).updateStudent(student);
    }

    @Test
    void getStudentCount() {
        when(studentService.getStudentsCount()).then(x -> 5L);

        final ResponseEntity<Long> response = studentController.getStudentsCount();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), 5L);

        //verify
        verify(studentService, times(1)).getStudentsCount();
    }
}
