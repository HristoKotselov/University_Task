package com.project.task.university.controller;

import com.project.task.university.model.Student;
import com.project.task.university.model.Teacher;
import com.project.task.university.service.StudentService;
import com.project.task.university.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("api")
@AllArgsConstructor
public class GeneralController {

    @NonNull
    private final StudentService studentService;

    @NonNull
    private final TeacherService teacherService;

    @GetMapping("/teacher-student/list")
    public ResponseEntity<List<Object>> getTeachersAndStudentsByCourseIdAndGroupId(@RequestParam final Long courseId,
                                                                                   @RequestParam final Long groupId) {
        try {
            List<Teacher> teachers = teacherService.getTeachersByCourseIdAndGroupId(courseId, groupId);
            List<Student> students = studentService.getStudentsByCourseIdAndGroupId(courseId, groupId);
            List<Object> all = new ArrayList<>();
            if (!teachers.isEmpty()) {
                all.addAll(teachers);
            }
            if (!students.isEmpty()) {
                all.addAll(students);
            }
            return ResponseEntity.status(HttpStatus.OK).body(all);
        } catch (Exception e) {
            log.error("Retrieving teacher and student list for groupId {} and courseId {} failed because {}",
                    groupId, courseId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
