package com.zigmunds.rieksts.springdemo.dao;

import com.zigmunds.rieksts.springdemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> findAll();

    void save(Student student);

    Student findStudentById(int theId);

    void deleteStudentById(int theId);

    Student findStudentAndCoursesByCourseId(int theId);
}
