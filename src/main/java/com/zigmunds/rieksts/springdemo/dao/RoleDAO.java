package com.zigmunds.rieksts.springdemo.dao;

import com.zigmunds.rieksts.springdemo.entity.Role;

import java.util.List;

public interface RoleDAO {
    Role findRoleByName(String roleName);

    Role findRoleById(int roleId);

    List<String> findRoleNames();
}
