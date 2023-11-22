package com.zigmunds.rieksts.springdemo.dao;

import com.zigmunds.rieksts.springdemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.merge(student);
    }

    @Override
    public Student findStudentById(int theId) {
        return entityManager.find(Student.class, theId);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student student = entityManager.find(Student.class, theId);

        entityManager.remove(student);
    }

    @Override
    public Student findStudentAndCoursesByCourseId(int theId) {
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "select c from Student c "
                        + "LEFT JOIN FETCH c.courses "
                        + "where c.id = :Id", Student.class);

        theQuery.setParameter("Id", theId);

        return theQuery.getSingleResult();
    }
}
