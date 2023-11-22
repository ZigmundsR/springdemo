package com.zigmunds.rieksts.springdemo.dao;

import com.zigmunds.rieksts.springdemo.entity.Course;
import com.zigmunds.rieksts.springdemo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {

    private EntityManager entityManager;

    @Autowired
    public CourseDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.merge(course);
    }

    @Override
    public List<Course> findAll() {
        TypedQuery<Course> theQuery = entityManager.createQuery("from Course", Course.class);

        return theQuery.getResultList();
    }

    @Override
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse = entityManager.find(Course.class, theId);

        entityManager.remove(tempCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                + "LEFT JOIN FETCH c.reviews "
                + "where c.id = :courseId", Course.class);

        query.setParameter("courseId", theId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteReviewById(int theId) {
        Review review = entityManager.find(Review.class, theId);

        entityManager.remove(review);
    }
}
