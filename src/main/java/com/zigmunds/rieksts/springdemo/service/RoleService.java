package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.entity.Role;

import java.util.Collection;
import java.util.List;

public interface RoleService {
    Role findRoleByName(String roleName);

    Collection<Role> findRolesByNames(List<String> roleNames);

    List<String> findRoleNames();
}
