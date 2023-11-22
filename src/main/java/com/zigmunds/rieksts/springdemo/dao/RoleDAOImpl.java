package com.zigmunds.rieksts.springdemo.dao;

import com.zigmunds.rieksts.springdemo.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO{

    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String roleName) {

        TypedQuery<Role> theQuery = entityManager.createQuery("from Role where name=:roleName", Role.class);
        theQuery.setParameter("roleName", roleName);

        Role theRole = null;

        try {
            theRole = theQuery.getSingleResult();
        } catch (Exception e) {
            theRole = null;
        }
        return theRole;
    }

    @Override
    public Role findRoleById(int roleId) {
        TypedQuery<Role> theQuery = entityManager.createQuery("from Role where id=:roleId", Role.class);
        theQuery.setParameter("roleId", roleId);

        Role theRole = null;

        try {
            theRole = theQuery.getSingleResult();
        } catch (Exception e) {
            theRole = null;
        }
        return theRole;
    }

    @Override
    public List<String> findRoleNames() {
        TypedQuery<String> theQuery = entityManager.createQuery("select name from Role", String.class);
        List<String> roles;

        try {
            roles = theQuery.getResultList();
        } catch (Exception e) {
            roles = null;
        }
        return roles;
    }
}
