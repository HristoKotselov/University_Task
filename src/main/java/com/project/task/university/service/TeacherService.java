package com.project.task.university.service;

import com.project.task.university.model.Teacher;
import com.project.task.university.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {

    @NonNull
    private final TeacherRepository teacherRepository;

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(@NonNull Teacher teacher) {
        Optional<Teacher> existingTeacher = teacherRepository.findById(teacher.getTeacherId());
        if (existingTeacher.isEmpty()) {
            return teacherRepository.save(teacher);
        }
        return null;
    }

    public Teacher deleteTeacher(@NonNull Teacher teacher) {
        Optional<Teacher> existingTeacher = teacherRepository.findById(teacher.getTeacherId());
        if (existingTeacher.isPresent() &&
                existingTeacher.get().getFirstName().equals(teacher.getFirstName()) &&
                existingTeacher.get().getLastName().equals(teacher.getLastName())) {
            teacherRepository.delete(teacher);
            return existingTeacher.get();
        }
        return null;
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    public long getTeachersCount() {
        return teacherRepository.count();
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public List<Teacher> getTeachersByCourseIdAndGroupId(Long courseId, Long groupId) {
        return teacherRepository.getTeachersByCourseIdAndGroupId(courseId, groupId);
    }
}
