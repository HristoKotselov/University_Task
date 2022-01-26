package com.project.task.university.repository;

import com.project.task.university.model.Course;
import com.project.task.university.model.CourseType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @NonNull List<Course> findAll();

    @Query("from Course c where c.courseType=:courseType")
    List<Course> getAllCoursesByType(@Param("courseType") CourseType courseType);
}
