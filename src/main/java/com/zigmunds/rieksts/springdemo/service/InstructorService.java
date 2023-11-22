package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.entity.Instructor;

import java.util.List;

public interface InstructorService {
    void save(Instructor Instructor);

    List<Instructor> findAll();

    Instructor findInstructorById(int instructorId);

    void deleteInstructorById(int theId);
}
