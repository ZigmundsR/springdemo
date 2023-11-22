package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int theId);

    void save(Employee theEmployee);

    void deleteById(int theId);

    List<Employee> findAll(String search);
}
