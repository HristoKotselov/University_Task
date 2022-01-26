package com.project.task.university.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "teacher")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"group", "courses"})
@EqualsAndHashCode(exclude = {"group", "courses"})
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "course_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
