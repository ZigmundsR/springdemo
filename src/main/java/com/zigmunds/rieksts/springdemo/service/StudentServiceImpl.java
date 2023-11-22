package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.dao.StudentDAO;
import com.zigmunds.rieksts.springdemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    public Student findStudentById(int theId) {
        return studentDAO.findStudentById(theId);
    }

    @Override
    public Student findStudentAndCoursesByCourseId(int theId) {
        return studentDAO.findStudentAndCoursesByCourseId(theId);
    }

    @Override
    public void deleteStudentById(int theId) {
        studentDAO.deleteStudentById(theId);
    }
}
