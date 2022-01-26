package com.project.task.university.service;

import com.project.task.university.model.Course;
import com.project.task.university.model.CourseType;
import com.project.task.university.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    @NonNull
    private final CourseRepository courseRepository;

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    public long getTotalCoursesCount() {
        return courseRepository.count();
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getAllCoursesByType(CourseType courseType) {
        return courseRepository.getAllCoursesByType(courseType);
    }
}
