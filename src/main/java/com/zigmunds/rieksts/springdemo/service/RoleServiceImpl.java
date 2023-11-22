package com.zigmunds.rieksts.springdemo.service;

import com.zigmunds.rieksts.springdemo.dao.RoleDAO;
import com.zigmunds.rieksts.springdemo.dao.UserDAO;
import com.zigmunds.rieksts.springdemo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleDAO RoleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO RoleDAO) {
        this.RoleDAO = RoleDAO;
    }

    @Override
    public Role findRoleByName(String roleName) {
        return RoleDAO.findRoleByName(roleName);
    }

    @Override
    public Collection<Role> findRolesByNames(List<String> roleNames) {

        Collection<Role> roles = new ArrayList<>();

        for(String role : roleNames){
            roles.add(RoleDAO.findRoleByName(role));
        }

        return roles;
    }

    @Override
    public List<String> findRoleNames() {
        return RoleDAO.findRoleNames();
    }
}
