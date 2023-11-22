package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.dao.InstructorDAO;
import com.zigmunds.rieksts.springdemo.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService{

    private InstructorDAO instructorDAO;

    @Autowired
    public InstructorServiceImpl(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    @Override
    public void save(Instructor Instructor) {
        instructorDAO.save(Instructor);
    }

    @Override
    public List<Instructor> findAll() {
        return instructorDAO.findAll();
    }

    @Override
    public Instructor findInstructorById(int Id) {
        return instructorDAO.findInstructorById(Id);
    }

    @Override
    public void deleteInstructorById(int theId) {
        instructorDAO.deleteInstructorById(theId);
    }
}
