package com.jsfcourse.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsfcourse.entities.Users;

@Stateless
public class UsersDAO {

    private final static String UNIT_NAME = "jsf_course_sushiPU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Users user) {
        em.persist(user);
    }

    public Users merge(Users user) {
        return em.merge(user);
    }

    public void remove(Users user) {
        em.remove(em.merge(user));
    }

    public Users find(Long idUser) {
        return em.find(Users.class, idUser);
    }

    public List<Users> getFullList() {
        List<Users> list = null;

        Query query = em.createQuery("select u from Users u");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Users> getList(Map<String, Object> searchParams) {
        List<Users> list = null;

        String select = "SELECT DISTINCT u ";
        String from = "FROM Users u LEFT JOIN FETCH u.userRoleCollection LEFT JOIN FETCH u.userRoleCollection.roles ";
        String where = "";
        String orderby = "order by u.lastName asc, u.firstName asc, u.login asc, u.idUser asc, u.email asc  ";
        
        String login = (String) searchParams.get("login");
        if (login != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.login like :login ";
        }
        
        Long idUser = (Long) searchParams.get("idUser");
        if (idUser != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.idUser = :idUser ";
        }
        
        String email = (String) searchParams.get("email");
        if (email != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.email like :email ";
        }
        
        String firstName = (String) searchParams.get("firstName");
        if (firstName != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.firstName like :firstName ";
        }
        
        String lastName = (String) searchParams.get("lastName");
        if (lastName != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.lastName like :lastName ";
        }

        Query query = em.createQuery(select + from + where + orderby);

        if (lastName != null) {
            query.setParameter("lastName","%" + lastName + "%");
        }
        if (firstName != null) {
            query.setParameter("firstName","%" + firstName + "%");
        }
        if (login != null) {
            query.setParameter("login","%" + login + "%");
        }
        if (email != null) {
            query.setParameter("email","%" + email + "%");
        }
        if (idUser != null) {
            query.setParameter("idUser",idUser);
        }
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Users getUserByLogin(String login) {
        try {
            return em.createQuery(
                    "SELECT u FROM Users u WHERE u.login = :login",
                    Users.class
            )
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Users getUserFromDatabase(String login, String password) {
        try {
            return em.createQuery("SELECT u FROM Users u WHERE u.login = :login AND u.password = :password", Users.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Users> findAllWithRoles() {
    return em.createQuery("""
        SELECT DISTINCT u
        FROM Users u
        LEFT JOIN FETCH u.userRoleCollection
        LEFT JOIN FETCH u.userRoleCollection.roles
    """, Users.class).getResultList();
}


}
