package com.project.task.university.repository;

import com.project.task.university.model.Student;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(@NonNull Long id);

    @NonNull List<Student> findAll();

    @Query("select max(s.studentId) from Student s")
    Long getLastId();

    @Query("from Student s left join fetch s.courses c where c.courseId=:courseId")
    List<Student> getStudentsByCourseId(@Param("courseId") Long courseId);

    @Query("from Student s where s.group.groupId=:groupId")
    List<Student> getStudentsByGroupId(@Param("groupId") Long groupId);

    @Query("from Student s left join fetch s.courses c where s.age>=:age and c.courseId=:courseId")
    List<Student> getStudentsByAgeAndCourseId(@Param("age") int age, @Param("courseId") Long courseId);

    @Query("from Student s left join fetch s.courses c where s.group.groupId=:groupId and c.courseId=:courseId")
    List<Student> getStudentsByCourseIdAndGroupId(@Param("courseId") Long courseId, @Param("groupId") Long groupId);
}