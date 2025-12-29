package com.jsfcourse.dao;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.jsfcourse.entities.UserRole;

@Stateless
public class UserRoleDAO {

    private static final String UNIT_NAME = "jsf_course_sushiPU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;


    public List<String> getRolesByUserId(long userId) {
        List<UserRole> roles = em.createQuery(
                "SELECT ur FROM UserRole ur WHERE ur.users.idUser = :userId", UserRole.class)
                .setParameter("userId", userId)
                .getResultList();

        return roles.stream()
                .map(ur -> ur.getRoles().getRoleName()) 
                .collect(Collectors.toList());
    }


    public void create(UserRole userRole) {
        em.persist(userRole);
    }

    public void remove(UserRole userRole) {
        em.remove(em.merge(userRole));
    }

    public UserRole merge(UserRole userRole) {
        return em.merge(userRole);
    }
}
