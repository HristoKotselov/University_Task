package com.project.task.university.service;

import com.project.task.university.UniversityApplication;
import com.project.task.university.model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.project.task.university.objects.TestObjectCreator.*;
import static org.junit.jupiter.api.Assertions.*;

@Rollback
@Transactional
@SpringBootTest(classes = UniversityApplication.class)
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    void getTeacherByIdTest() {
        final Optional<Teacher> teacher = teacherService.getTeacherById(1L);

        assertTrue(teacher.isPresent());
        assertEquals("John", teacher.get().getFirstName());
        assertEquals("Smith", teacher.get().getLastName());
        assertEquals(35,teacher.get().getAge());
        assertEquals(1, teacher.get().getGroup().getGroupId());
    }

    @Test
    void saveTeacherTest() {
        final Teacher teacher = createTestTeacher1();

        final Teacher addedTeacher = teacherService.addTeacher(teacher);
        assertNotNull(addedTeacher);
        final Optional<Teacher> savedTeacher = teacherService.getTeacherById(addedTeacher.getTeacherId());
        assertTrue(savedTeacher.isPresent());
        assertEquals(teacher.getFirstName(), savedTeacher.get().getFirstName());
        assertEquals(teacher.getLastName(), savedTeacher.get().getLastName());
        assertEquals(teacher.getAge(),savedTeacher.get().getAge());
        assertEquals(teacher.getGroup(), savedTeacher.get().getGroup());
        assertEquals(teacher.getCourses(), savedTeacher.get().getCourses());
    }

    @Test
    void deleteTeacherTest() {
        final Teacher teacher = teacherService.getTeacherById(1L).get();
        teacherService.deleteTeacher(teacher);

        assertFalse(teacherService.getTeacherById(1L).isPresent());
    }

    @Test
    void deleteTeacherByIdTest() {
        teacherService.deleteTeacherById(1L);

        assertFalse(teacherService.getTeacherById(1L).isPresent());
    }

    @Test
    void getTotalTeachersCountTest() {
        long count = teacherService.getTeachersCount();
        assertEquals(12, count);
    }

    @Test
    void getAllTeachersTest() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        assertEquals(12, teachers.size());
    }

    @Test
    void getTeachersByCourseAndGroupIdTest() {
        final List<Teacher> foundTeachers = teacherService.getTeachersByCourseIdAndGroupId(1L, 1L);
        assertEquals(1, foundTeachers.size());
    }
}
