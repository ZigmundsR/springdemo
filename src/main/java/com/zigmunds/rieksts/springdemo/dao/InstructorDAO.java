package com.zigmunds.rieksts.springdemo.dao;

import com.zigmunds.rieksts.springdemo.entity.Instructor;

import java.util.List;

public interface InstructorDAO {
    void save(Instructor instructor);

    List<Instructor> findAll();

    Instructor findInstructorById(int id);

    void deleteInstructorById(int theId);
}
