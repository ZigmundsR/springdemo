package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.dao.CourseDAO;
import com.zigmunds.rieksts.springdemo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseDAO courseDAO;

    @Autowired
    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }


    @Override
    public void save(Course course) {
        courseDAO.save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    @Override
    public Course findCourseById(int courseId) {
        return courseDAO.findCourseById(courseId);
    }

    @Override
    public void deleteCourseById(int theId) {
        courseDAO.deleteCourseById(theId);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        return courseDAO.findCourseAndReviewsByCourseId(theId);
    }

    @Override
    public void deleteReviewById(int theId) {
        courseDAO.deleteReviewById(theId);
    }
}
