package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);

    Boolean userExistByUserName(String userName);
    
    void save(User user);

    List<User> findAll();

    List<User> findAll(String search);

    User findById(int theId);

    void deleteById(int theId);
}
