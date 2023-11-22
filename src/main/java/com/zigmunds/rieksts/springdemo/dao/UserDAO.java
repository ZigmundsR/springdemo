package com.zigmunds.rieksts.springdemo.dao;

import com.zigmunds.rieksts.springdemo.entity.User;

import java.util.List;

public interface UserDAO {
    User findByUserName(String userName);

    Boolean userExistByUserName(String userName);

    void save(User theUser);

    List<User> findAll();

    List<User> findAll(String search);

    User findById(int theId);

    void deleteById(int theId);

}
