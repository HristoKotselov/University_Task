package com.project.task.university.controller;

import com.project.task.university.UniversityApplication;
import com.project.task.university.model.Course;
import com.project.task.university.model.Teacher;
import com.project.task.university.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static com.project.task.university.objects.TestObjectCreator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UniversityApplication.class)
public class TeacherControllerTest {

    @Autowired
    private TeacherController teacherController;

    @MockBean
    private TeacherService teacherService;

    @Test
    void getTeacherByIdTest() {
        final List<Course> courseList = createTestCourseListAll();
        final Teacher teacher = createTestTeacher1(courseList);
        teacher.setTeacherId(100L);

        when(teacherService.getTeacherById(teacher.getTeacherId())).then(x -> Optional.of(teacher));

        final ResponseEntity<Teacher> response = teacherController.getTeacherById(teacher.getTeacherId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getFirstName(), teacher.getFirstName());
        assertEquals(response.getBody().getLastName(), teacher.getLastName());
        assertEquals(response.getBody().getAge(), teacher.getAge());
        assertEquals(response.getBody().getCourses(), teacher.getCourses());

        //verify
        verify(teacherService, times(1)).getTeacherById(teacher.getTeacherId());
    }

    @Test
    void addTeacherTest() {
        final List<Course> courseList = createTestCourseListAll();
        final Teacher teacher = createTestTeacher1(courseList);

        when(teacherService.addTeacher(teacher)).then(x -> teacher);

        final ResponseEntity<Teacher> response = teacherController.addTeacher(teacher);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getFirstName(), teacher.getFirstName());
        assertEquals(response.getBody().getLastName(), teacher.getLastName());
        assertEquals(response.getBody().getAge(), teacher.getAge());
        assertEquals(response.getBody().getCourses(), teacher.getCourses());

        //verify
        verify(teacherService, times(1)).addTeacher(teacher);
    }

    @Test
    void deleteTeacherTest() {
        final Teacher teacher = createTestTeacher1();
        when(teacherService.deleteTeacher(teacher)).then(x -> teacher);

        final ResponseEntity<String> response = teacherController.deleteTeacher(teacher);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), "Teacher deleted successfully");

        //verify
        verify(teacherService, times(1)).deleteTeacher(teacher);
    }

    @Test
    void deleteStudentNotFoundTest() {
        final Teacher teacher = new Teacher();
        when(teacherService.deleteTeacher(teacher)).then(x -> null);

        final ResponseEntity<String> response = teacherController.deleteTeacher(teacher);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(response.getBody());

        //verify
        verify(teacherService, times(1)).deleteTeacher(teacher);
    }

    @Test
    void updateTeacherTest() {
        final Teacher teacher = createTestTeacher1();
        teacher.setTeacherId(10L);

        when(teacherService.getTeacherById(teacher.getTeacherId())).then(x -> Optional.of(teacher));
        when(teacherService.updateTeacher(teacher)).then(x -> teacher);

        final ResponseEntity<Teacher> response = teacherController.updateTeacher(teacher);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());

        //verify
        verify(teacherService, times(1)).getTeacherById(teacher.getTeacherId());
        verify(teacherService, times(1)).updateTeacher(teacher);
    }

    @Test
    void getTeacherCount() {
        when(teacherService.getTeachersCount()).then(x -> 5L);

        final ResponseEntity<Long> response = teacherController.getTeachersCount();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), 5L);

        //verify
        verify(teacherService, times(1)).getTeachersCount();
    }
}
