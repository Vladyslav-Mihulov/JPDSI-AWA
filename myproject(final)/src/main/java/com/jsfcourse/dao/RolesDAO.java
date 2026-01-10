package com.jsfcourse.dao;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.jsfcourse.entities.Roles;
import com.jsfcourse.entities.UserRole;
/**
 *
 * @author vladm
 */
@Stateless
public class RolesDAO {

    private final static String UNIT_NAME = "jsf_course_sushiPU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public List<Roles> getAllRoles() {
        return em.createQuery("SELECT r FROM Roles r ORDER BY r.roleName", Roles.class)
                 .getResultList();
    }

    public void addRoleToUser(UserRole userRole) {
        em.persist(userRole);
    }

    public void removeRoleFromUser(UserRole userRole) {
        em.remove(em.merge(userRole));
    }
}
