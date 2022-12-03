package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    List<Student> students = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();
    Map<Teacher,List<Student>> studentTeacherMap = new HashMap<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public Student getStudent(String name) {
        for(Student student:students) if(student.getName().equals(name)) return student;
        return new Student();
    }

    public Teacher getTeacher(String name) {
        for(Teacher teacher:teachers) if(teacher.getName().equals(name)) return teacher;
        return new Teacher();
    }

    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for(Student student:students) studentList.add(student.getName());
        return studentList;
    }

    public void addStudentTeacherPair(String studentName, String teacherName) {
        Student student = getStudent(studentName);
        Teacher teacher = getTeacher(teacherName);
        if(!studentTeacherMap.containsKey(teacher)) studentTeacherMap.put(teacher,new ArrayList<>());
        studentTeacherMap.get(teacher).add(student);
    }

    public List<String> getStudentsByTeacherName(String teacherName) {
        Teacher teacher = getTeacher(teacherName);
        List<String> studentList = new ArrayList<>();
        for(Student student:studentTeacherMap.get(teacher)) studentList.add(student.getName());
        return studentList;
    }

    public void deleteTeacherByName(String teacherName) {
        Teacher teacher = getTeacher(teacherName);
        for(Student student: studentTeacherMap.get(teacher)) students.remove(student);
        studentTeacherMap.remove(teacher);
        teachers.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(Teacher teacher: new ArrayList<>(teachers)) deleteTeacherByName(teacher.getName());
        teachers.clear();
        studentTeacherMap.clear();
    }
}
