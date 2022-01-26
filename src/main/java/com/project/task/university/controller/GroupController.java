package com.project.task.university.controller;

import com.project.task.university.model.Student;
import com.project.task.university.service.GroupService;
import com.project.task.university.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/group")
@AllArgsConstructor
public class GroupController {

    @NonNull
    private final GroupService groupService;

    @NonNull
    private final StudentService studentService;

    @GetMapping("/student/list")
    public ResponseEntity<List<Student>> getAllStudentsByGroupId(@RequestParam final Long groupId) {
        try {
            List<Student> students = studentService.getStudentsByGroupId(groupId);
            return ResponseEntity.status(HttpStatus.OK).body(students);
        } catch (Exception e) {
            log.error("Retrieving student list for groupId {} failed because {}", groupId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
