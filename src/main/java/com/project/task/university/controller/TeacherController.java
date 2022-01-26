package com.project.task.university.controller;

import com.project.task.university.model.Teacher;
import com.project.task.university.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("api/teacher")
@AllArgsConstructor
public class TeacherController {

    @NonNull
    private final TeacherService teacherService;

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") final Long teacherId) {
        try {
            final Optional<Teacher> student = teacherService.getTeacherById(teacherId);
            if (student.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(student.get());
            } else {
                log.error("Teacher with id {} was not found", teacherId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving teacher with id {} failed because {}", teacherId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Teacher> addTeacher(@RequestBody final Teacher teacher) {
        try {
            final Teacher addedteacher = teacherService.addTeacher(teacher);
            if (addedteacher == null) {
                log.error("Adding teacher {} failed due to age restriction", teacher);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            log.info("Successfully added teacher {}", addedteacher);
            return ResponseEntity.status(HttpStatus.OK).body(addedteacher);
        } catch (Exception e) {
            log.error("Adding teacher {} failed because {}", teacher, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable("id") final Long teacherId) {
        try {
            teacherService.deleteTeacherById(teacherId);
            log.info("Deleted teacher with id {}", teacherId);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted teacher with id " + teacherId);
        } catch (Exception e) {
            log.error("Deleting teacher with id {} failed because {}", teacherId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTeacher(@RequestBody final Teacher teacher) {
        try {
            final Teacher deletedTeacher = teacherService.deleteTeacher(teacher);
            if (deletedTeacher != null) {
                log.info("Deleting teacher {}", teacher);
                return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted successfully");
            } else {
                log.error("Deleting teacher {} failed because of wrong names", teacher);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Deleting teacher {} failed because {}", teacher, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody final Teacher teacher) {
        try {
            final Optional<Teacher> existingStudent = teacherService.getTeacherById(teacher.getTeacherId());
            if (existingStudent.isPresent()) {
                final Teacher updatedStudent = teacherService.updateTeacher(teacher);
                if (updatedStudent == null) {
                    log.error("Updating student with id {} failed due to age restriction", teacher.getTeacherId());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }
                log.info("Updated student with id {}", updatedStudent.getTeacherId());
                return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
            } else {
                log.info("No student with id {} was found", teacher.getTeacherId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Updating student with id {} failed because {}", teacher.getTeacherId(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTeachersCount() {
        try {
            final long count = teacherService.getTeachersCount();
            return ResponseEntity.status(HttpStatus.OK).body(count);
        } catch (Exception e) {
            log.error("Retrieving student count failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
