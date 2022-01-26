package com.project.task.university.service;

import com.project.task.university.controller.filter.StudentFilter;
import com.project.task.university.model.Student;
import com.project.task.university.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StudentService {

    @NonNull
    private final StudentRepository studentRepository;

    @NonNull
    private final StudentFilter studentFilter;

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        if (studentFilter.isValidStudent(student)) {
            return studentRepository.save(student);
        }
        return null;
    }

    public Student updateStudent(@NonNull Student student) {
        Optional<Student> existingStudent = studentRepository.findById(student.getStudentId());
        if (studentFilter.isValidStudent(student) && existingStudent.isPresent()) {
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student deleteStudent(@NonNull Student student) {
        Optional<Student> existingStudent = studentRepository.findById(student.getStudentId());
        if (existingStudent.isPresent() &&
                existingStudent.get().getFirstName().equals(student.getFirstName()) &&
                existingStudent.get().getLastName().equals(student.getLastName()) &&
                existingStudent.get().getAge() == student.getAge()) {
            studentRepository.delete(student);
            return existingStudent.get();
        }
        return null;
    }

    public Long getStudentsCount() {
        return studentRepository.count();
    }

    public Map<Boolean, List<Student>> addStudentsFromList(List<Student> students) {
        Map<Boolean, List<Student>> studentMap = studentFilter.splitFilter(students);
        studentRepository.saveAll(studentMap.get(true));
        return studentMap;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByGroupId(Long groupId) {
        return studentRepository.getStudentsByGroupId(groupId);
    }

    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentRepository.getStudentsByCourseId(courseId);
    }

    public List<Student> getStudentsByAgeAndCourseId(int age, Long courseId) {
        return studentRepository.getStudentsByAgeAndCourseId(age, courseId);
    }

    public List<Student> getStudentsByCourseIdAndGroupId(Long courseId, Long groupId) {
        return studentRepository.getStudentsByCourseIdAndGroupId(courseId, groupId);
    }

}
