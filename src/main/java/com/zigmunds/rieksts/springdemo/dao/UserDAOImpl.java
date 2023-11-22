package com.zigmunds.rieksts.springdemo.dao;

import com.zigmunds.rieksts.springdemo.entity.Employee;
import com.zigmunds.rieksts.springdemo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> theQuery = entityManager.createQuery("from User where userName=:userName", User.class);
        theQuery.setParameter("userName", userName);

        User theUser = null;

        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    public Boolean userExistByUserName(String userName) {
        TypedQuery<Integer> theQuery = entityManager.createQuery("select u.id from User u where u.userName=:userName", Integer.class);
        theQuery.setParameter("userName", userName);

        Integer check = null;

        try {
            check = theQuery.getSingleResult();
        } catch (Exception e) {
            check = null;
        }

        return check == null;
    }

    @Override
    @Transactional
    public void save(User theUser) {
        entityManager.merge(theUser);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> theQuery = entityManager.createQuery("from User", User.class);

        List<User> user = theQuery.getResultList();

        return user;
    }

    @Override
    public List<User> findAll(String search) {

        TypedQuery<User> theQuery = entityManager.createQuery(
                "SELECT u FROM User u " +
                        "JOIN u.employee e " +
                        "WHERE u.userName LIKE :search " +
                        "OR e.firstName LIKE :search " +
                        "OR e.lastName LIKE :search " +
                        "OR e.email LIKE :search", User.class);

        theQuery.setParameter("search", "%" + search + "%");

        List<User> user = theQuery.getResultList();

        return user;
    }

    @Override
    public User findById(int theId) {
        User user = entityManager.find(User.class, theId);

        return user;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        User user = entityManager.find(User.class, theId);

        entityManager.remove(user);
    }
}
