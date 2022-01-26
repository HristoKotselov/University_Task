package com.project.task.university.controller;

import com.project.task.university.UniversityApplication;
import com.project.task.university.model.Group;
import com.project.task.university.model.Student;
import com.project.task.university.objects.TestObjectCreator;
import com.project.task.university.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.project.task.university.objects.TestObjectCreator.createTestGroup1;
import static com.project.task.university.objects.TestObjectCreator.createTestStudentList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UniversityApplication.class)
public class GroupControllerTest {

    @Autowired
    private GroupController groupController;

    @MockBean
    private StudentService studentService;

    @Test
    void getAllStudentsByGroupIdTest() {
        final Group group = TestObjectCreator.createTestGroup1();
        final List<Student> students = createTestStudentList(group);

        when(studentService.getStudentsByGroupId(group.getGroupId())).then(x -> students);

        final ResponseEntity<List<Student>> response = groupController.getAllStudentsByGroupId(group.getGroupId());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().size(), students.size());

        //verify
        verify(studentService, times(1)).getStudentsByGroupId(group.getGroupId());
    }
}
