package com.project.task.university.objects;

import com.project.task.university.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestObjectCreator {

    public static Student createTestStudent1() {
        return Student.builder().firstName("John").lastName("Smith")
                .age(25).build();
    }

    public static Student createTestStudent1(List<Course> courses) {
        return Student.builder().firstName("John").lastName("Smith")
                .age(25).courses(courses).build();
    }

    public static Student createTestStudent1(Group group) {
        return Student.builder().firstName("John").lastName("Smith")
                .age(25).group(group).build();
    }

    public static Student createTestStudent1(List<Course> courses, Group group) {
        return Student.builder().firstName("John").lastName("Smith")
                .age(25).courses(courses).group(group).build();
    }

    public static Student createTestStudent2() {
        return Student.builder().firstName("Jessy").lastName("Wellens")
                .age(28).build();
    }

    public static Student createTestStudent2(List<Course> courses) {
        return Student.builder().firstName("Jessy").lastName("Wellens")
                .age(28).courses(courses).build();
    }

    public static Student createTestStudent2(Group group) {
        return Student.builder().firstName("Jessy").lastName("Wellens")
                .age(28).group(group).build();
    }

    public static Student createTestStudent2(List<Course> courses, Group group) {
        return Student.builder().firstName("Jessy").lastName("Wellens")
                .age(28).courses(courses).group(group).build();
    }

    public static List<Student> createTestStudentList() {
        final Student student1 = createTestStudent1();
        final Student student2 = createTestStudent2();
        final List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        return students;
    }

    public static List<Student> createTestStudentList(List<Course> courses) {
        final Student student1 = createTestStudent1(courses);
        final Student student2 = createTestStudent2(courses);
        final List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        return students;
    }

    public static List<Student> createTestStudentList(Group group) {
        final Student student1 = createTestStudent1(group);
        final Student student2 = createTestStudent2(group);
        final List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        return students;
    }

    public static List<Student> createTestStudentList(List<Course> courses, Group group) {
        final Student student1 = createTestStudent1(courses, group);
        final Student student2 = createTestStudent2(courses, group);
        final List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        return students;
    }

    public static Teacher createTestTeacher1() {
        return Teacher.builder().firstName("Jessica").lastName("Jones").age(40).build();
    }

    public static Teacher createTestTeacher1(List<Course> courses) {
        return Teacher.builder().firstName("Jessica").lastName("Jones").age(40).courses(courses).build();
    }

    public static Teacher createTestTeacher1(Group group) {
        return Teacher.builder().firstName("Jessica").lastName("Jones").age(40).group(group).build();
    }

    public static Teacher createTestTeacher1(List<Course> courses, Group group) {
        return Teacher.builder().firstName("Jessica").lastName("Jones").age(40).courses(courses).group(group).build();
    }

    public static Teacher createTestTeacher2() {
        return Teacher.builder().firstName("Donald").lastName("Duck").age(60).build();
    }

    public static Teacher createTestTeacher2(List<Course> courses) {
        return Teacher.builder().firstName("Donald").lastName("Duck").age(60).courses(courses).build();
    }

    public static Teacher createTestTeacher2(Group group) {
        return Teacher.builder().firstName("Donald").lastName("Duck").age(60).group(group).build();
    }

    public static Teacher createTestTeacher2(List<Course> courses, Group group) {
        return Teacher.builder().firstName("Donald").lastName("Duck").age(60).courses(courses).group(group).build();
    }

    public static List<Teacher> createTestTeacherList() {
        final Teacher teacher1 = createTestTeacher1();
        final Teacher teacher2 = createTestTeacher2();
        final List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher1);
        teachers.add(teacher2);
        return teachers;
    }

    public static List<Teacher> createTestTeacherList(List<Course> courses) {
        final Teacher teacher1 = createTestTeacher1(courses);
        final Teacher teacher2 = createTestTeacher2(courses);
        final List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher1);
        teachers.add(teacher2);
        return teachers;
    }

    public static List<Teacher> createTestTeacherList(Group group) {
        final Teacher teacher1 = createTestTeacher1(group);
        final Teacher teacher2 = createTestTeacher2(group);
        final List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher1);
        teachers.add(teacher2);
        return teachers;
    }

    public static List<Teacher> createTestTeacherList(List<Course> courses, Group group) {
        final Teacher teacher1 = createTestTeacher1(courses, group);
        final Teacher teacher2 = createTestTeacher2(courses, group);
        final List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher1);
        teachers.add(teacher2);
        return teachers;
    }

    public static Course createTestMainCourse1() {
        return Course.builder().courseName("Bla Bla").courseType(CourseType.MAIN).build();
    }

    public static Course createTestMainCourse1(Group group) {
        return Course.builder().courseName("Bla Bla").courseType(CourseType.MAIN).build();
    }

    public static Course createTestMainCourse2() {
        return Course.builder().courseName("Bla Bla 2").courseType(CourseType.MAIN).build();
    }

    public static Course createTestMainCourse2(Group group) {
        return Course.builder().courseName("Bla Bla 2").courseType(CourseType.MAIN).build();
    }

    public static Course createTestSecondaryCourse1() {
        return Course.builder().courseName("Something1").courseType(CourseType.SECONDARY).build();
    }

    public static Course createTestSecondaryCourse1(Group group) {
        return Course.builder().courseName("Something1").courseType(CourseType.SECONDARY).build();
    }

    public static Course createTestSecondaryCourse2() {
        return Course.builder().courseName("Something2").courseType(CourseType.SECONDARY).build();
    }

    public static Course createTestSecondaryCourse2(Group group) {
        return Course.builder().courseName("Something2").courseType(CourseType.SECONDARY).build();
    }

    public static List<Course> createTestCourseListAll() {
        final List<Course> courses = new ArrayList<>();
        courses.add(createTestMainCourse1());
        courses.add(createTestMainCourse2());
        courses.add(createTestSecondaryCourse1());
        courses.add(createTestSecondaryCourse2());
        return courses;
    }

    public static List<Course> createTestCourseListMain() {
        final List<Course> courses = new ArrayList<>();
        courses.add(createTestMainCourse1());
        courses.add(createTestMainCourse2());
        return courses;
    }

    public static List<Course> createTestCourseListSecondary() {
        final List<Course> courses = new ArrayList<>();
        courses.add(createTestSecondaryCourse1());
        courses.add(createTestSecondaryCourse2());
        return courses;
    }

    public static Group createTestGroup1() {
        return Group.builder().build();
    }

    public static Group createTestGroup1(Course course) {
        return Group.builder().build();
    }

    public static List<Group> createTestGroupList() {
        final Group group1 = createTestGroup1();
        final Group group2 = createTestGroup1();
        final List<Group> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);
        return groups;
    }

    public static List<Group> createTestGroupList(Course course) {
        final Group group1 = createTestGroup1(course);
        final Group group2 = createTestGroup1(course);
        final List<Group> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);
        return groups;
    }

}
