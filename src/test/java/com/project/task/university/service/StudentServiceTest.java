package com.project.task.university.service;

import com.project.task.university.UniversityApplication;
import com.project.task.university.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.project.task.university.objects.TestObjectCreator.createTestStudent1;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Rollback
@Transactional
@SpringBootTest(classes = UniversityApplication.class)
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void getStudentByIdTest() {
        final Optional<Student> student = studentService.getStudentById(1L);

        assertTrue(student.isPresent());
        assertEquals("Harry", student.get().getFirstName());
        assertEquals("McGuire", student.get().getLastName());
        assertEquals(25,student.get().getAge());
        assertEquals(1, student.get().getGroup().getGroupId());
    }

    @Test
    void saveStudentTest() {
        final Student student = createTestStudent1();

        final Student addedTeacher = studentService.addStudent(student);
        assertNotNull(addedTeacher);
        final Optional<Student> savedTeacher = studentService.getStudentById(addedTeacher.getStudentId());
        assertTrue(savedTeacher.isPresent());
        assertEquals(student.getFirstName(), savedTeacher.get().getFirstName());
        assertEquals(student.getLastName(), savedTeacher.get().getLastName());
        assertEquals(student.getAge(),savedTeacher.get().getAge());
        assertEquals(student.getGroup(), savedTeacher.get().getGroup());
        assertEquals(student.getCourses(), savedTeacher.get().getCourses());
    }

    @Test
    void deleteStudentTest() {
        final Student student = studentService.getStudentById(1L).get();
        studentService.deleteStudent(student);

        assertFalse(studentService.getStudentById(1L).isPresent());
    }

    @Test
    void deleteStudentByIdTest() {
        studentService.deleteStudentById(1L);

        assertFalse(studentService.getStudentById(1L).isPresent());
    }

    @Test
    void getTotalStudentsCountTest() {
        long count = studentService.getStudentsCount();
        assertEquals(20, count);
    }

    @Test
    void getAllStudentsTest() {
        List<Student> students = studentService.getAllStudents();
        assertEquals(20, students.size());
    }

    @Test
    void getStudentsByGroupIdTest() {
        final List<Student> foundStudents =  studentService.getStudentsByGroupId(1L);
        assertEquals(3, foundStudents.size());
    }

    @Test
    void getStudentsByCourseIdTest() {
        final List<Student> foundStudents =  studentService.getStudentsByCourseId(1L);
        assertEquals(6, foundStudents.size());
    }

    @Test
    void getStudentsByAgeAndCourseTest() {
        final List<Student> foundStudents = studentService.getStudentsByAgeAndCourseId(20, 1L);
        assertEquals(5, foundStudents.size());
    }

    @Test
    void getStudentsByCourseAndGroupIdTest() {
        final List<Student> foundStudents = studentService.getStudentsByCourseIdAndGroupId(1L, 1L);
        assertEquals(3, foundStudents.size());
    }
}
