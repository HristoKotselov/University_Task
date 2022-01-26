package com.project.task.university.repository;

import com.project.task.university.model.Teacher;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @NonNull List<Teacher> findAll();

    @Query("from Teacher t left join fetch t.courses c where t.group.groupId=:groupId and c.courseId=:courseId")
    List<Teacher> getTeachersByCourseIdAndGroupId(@Param("courseId") Long courseId, @Param("groupId") Long groupId);
}
