package com.project.task.university.controller;

import com.project.task.university.model.Student;
import com.project.task.university.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("api/student")
@AllArgsConstructor
public class StudentController {

    @NonNull
    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") final Long studentId) {
        try {
            final Optional<Student> student = studentService.getStudentById(studentId);
            if (student.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(student.get());
            } else {
                log.error("Student with id {} was not found", studentId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving student with id {} failed because {}", studentId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody final Student student) {
        try {
            final Student addedStudent = studentService.addStudent(student);
            if (addedStudent == null) {
                log.error("Adding student {} failed due to age restriction", student);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            log.info("Successfully added student {}", addedStudent);
            return ResponseEntity.status(HttpStatus.OK).body(addedStudent);
        } catch (Exception e) {
            log.error("Adding student {} failed because {}", student, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add/list")
    public ResponseEntity<Map<Boolean, List<Student>>> addStudentList(@RequestBody final List<Student> students) {
        try {
            Map<Boolean, List<Student>> studentMap = studentService.addStudentsFromList(students);
            log.info("Added a list of students {}", studentMap.get(true));
            log.info("Ignored a list of students {} not old enough", studentMap.get(false));
            return ResponseEntity.status(HttpStatus.OK).body(studentMap);
        } catch (Exception e) {
            log.error("Adding list of students {} failed because {}", students, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") final Long studentId) {
        try {
            studentService.deleteStudentById(studentId);
            log.info("Deleted student with id {}", studentId);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted student with id " + studentId);
        } catch (Exception e) {
            log.error("Deleting student with id {} failed because {}", studentId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestBody final Student student) {
        try {
            final Student deletedStudent = studentService.deleteStudent(student);
            if (deletedStudent != null) {
                log.info("Deleting student {}", student);
                return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
            } else {
                log.error("Deleting student {} failed because of wrong information", student);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Deleting student {} failed because {}", student, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody final Student student) {
        try {
            final Optional<Student> existingStudent = studentService.getStudentById(student.getStudentId());
            if (existingStudent.isPresent()) {
                final Student updatedStudent = studentService.updateStudent(student);
                if (updatedStudent == null) {
                    log.error("Updating student with id {} failed due to age restriction or given names are blank",
                            student.getStudentId());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }
                log.info("Updated student with id {}", updatedStudent.getStudentId());
                return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
            } else {
                log.info("No student with id {} was found", student.getStudentId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Updating student with id {} failed because {}", student.getStudentId(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Student>> getAllStudentsByAgeAndCourse(@RequestParam(required = false) final Integer age,
                                                                      @RequestParam(required = false) final Long courseId) {
        try {
            if (age != null && age > 0 && courseId != null) {
                final List<Student> students = studentService.getStudentsByAgeAndCourseId(age, courseId);
                return ResponseEntity.status(HttpStatus.OK).body(students);
            } else {
                List<Student> allStudents = studentService.getAllStudents();
                return ResponseEntity.status(HttpStatus.OK).body(allStudents);
            }
        } catch (Exception e) {
            log.error("Retrieving student list for courseId {} and above age {} failed because {}", courseId, age, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getStudentsCount() {
        try {
            final Long count = studentService.getStudentsCount();
            return ResponseEntity.status(HttpStatus.OK).body(count);
        } catch (Exception e) {
            log.error("Retrieving student count failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
