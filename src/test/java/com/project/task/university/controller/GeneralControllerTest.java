package com.project.task.university.controller;

import com.project.task.university.UniversityApplication;
import com.project.task.university.model.Course;
import com.project.task.university.model.Group;
import com.project.task.university.model.Student;
import com.project.task.university.model.Teacher;
import com.project.task.university.service.StudentService;
import com.project.task.university.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.project.task.university.objects.TestObjectCreator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UniversityApplication.class)
public class GeneralControllerTest {

    @Autowired
    private GeneralController generalController;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;

    @Test
    void getTeachersAndStudentsByCourseIdAndGroupId() {
        final List<Course> courseList = createTestCourseListMain();
        final Group group = createTestGroup1(courseList.get(0));
        final List<Student> students = createTestStudentList(courseList, group);
        final List<Teacher> teachers = createTestTeacherList(courseList, group);

        final List<Object> teachersAndStudents = new ArrayList<>();
        teachersAndStudents.addAll(teachers);
        teachersAndStudents.addAll(students);

        when(studentService.getStudentsByCourseIdAndGroupId(courseList.get(0).getCourseId(), group.getGroupId())).then(x -> students);
        when(teacherService.getTeachersByCourseIdAndGroupId(courseList.get(0).getCourseId(), group.getGroupId())).then(x -> teachers);

        final ResponseEntity<List<Object>> response = generalController.getTeachersAndStudentsByCourseIdAndGroupId(courseList.get(0).getCourseId(), group.getGroupId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().size(), teachersAndStudents.size());

        //verify
        verify(studentService, times(1)).getStudentsByCourseIdAndGroupId(courseList.get(0).getCourseId(), group.getGroupId());
        verify(teacherService, times(1)).getTeachersByCourseIdAndGroupId(courseList.get(0).getCourseId(), group.getGroupId());
    }
}
