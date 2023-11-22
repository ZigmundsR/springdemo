package com.zigmunds.rieksts.springdemo.dao;

import com.zigmunds.rieksts.springdemo.entity.Course;

import java.util.List;

public interface CourseDAO {
    void save(Course course);

    List<Course> findAll();

    Course findCourseById(int courseId);

    void deleteCourseById(int theId);

    Course findCourseAndReviewsByCourseId(int theId);

    void deleteReviewById(int theId);
}
