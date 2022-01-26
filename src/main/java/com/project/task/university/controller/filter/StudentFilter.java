package com.project.task.university.controller.filter;

import com.project.task.university.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StudentFilter implements Filter<Student> {

    private static final int MIN_STUDENT_AGE = 18;

    @Override
    public Map<Boolean, List<Student>> splitFilter(List<Student> students) {
        return students.stream().collect(Collectors.partitioningBy(x -> x.getAge() >= MIN_STUDENT_AGE));
    }

    @Override
    public List<Student> filter(List<Student> students) {
        return students.stream().filter(student -> student.getAge() >= MIN_STUDENT_AGE).collect(Collectors.toList());
    }

    public boolean isValidStudent(Student student) {
        return !student.getFirstName().isBlank() && !student.getLastName().isBlank() && student.getAge() >= MIN_STUDENT_AGE;
    }

}
