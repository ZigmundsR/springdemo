package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    void save(Student student);

    Student findStudentById(int theId);

    Student findStudentAndCoursesByCourseId(int theId);

    void deleteStudentById(int theId);
}
