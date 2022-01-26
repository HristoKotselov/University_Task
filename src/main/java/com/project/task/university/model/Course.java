package com.project.task.university.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_type")
    @Enumerated(EnumType.STRING)
    private CourseType courseType;

}
