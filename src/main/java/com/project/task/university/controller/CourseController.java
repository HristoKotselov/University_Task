package com.project.task.university.controller;

import com.project.task.university.model.*;
import com.project.task.university.service.CourseService;
import com.project.task.university.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@Log4j2
@RestController
@RequestMapping("api/course")
@AllArgsConstructor
public class CourseController {

    @NonNull
    private final CourseService courseService;

    @NonNull
    private final StudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<List<Course>> getAllCoursesByType(@RequestParam(value = "courseType", required = false) final String courseTypeString) {
        try {
            if (courseTypeString == null) {
                List<Course> allCourses = courseService.getAllCourses();
                return ResponseEntity.status(HttpStatus.OK).body(allCourses);
            } else if (EnumUtils.isValidEnumIgnoreCase(CourseType.class, courseTypeString)) {
                List<Course> courses = courseService.getAllCoursesByType(CourseType.valueOf(courseTypeString.toUpperCase()));
                return ResponseEntity.status(HttpStatus.OK).body(courses);
            } else {
                log.error("Retrieving course list for course type {} failed because there is no such course type", courseTypeString);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving course list for course type {} failed because {}", courseTypeString, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/student/list")
    public ResponseEntity<List<Student>> getAllStudentsByCourseId(@RequestParam(name = "courseId") final Long courseId) {
        try {
            List<Student> students = studentService.getStudentsByCourseId(courseId);
            return ResponseEntity.status(HttpStatus.OK).body(students);
        } catch (Exception e) {
            log.error("Retrieving student list for courseId {} failed because {}", courseId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
