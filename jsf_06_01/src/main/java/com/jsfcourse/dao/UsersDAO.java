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

    // Dependency injection (no setter method is needed)
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

        // 1. Build query string with parameters
        String select = "select u ";
        String from = "from Users u ";
        String where = "";
        String orderby = "order by u.lastName asc, u.firstName asc  ";

        // search for surname
        String lastName = (String) searchParams.get("lastName");
        if (lastName != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "u.lastName like :lastName ";
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

        // ... other parameters ... 
        // 2. Create query object
        Query query = em.createQuery(select + from + where + orderby);

        // 3. Set configured parameters
        if (lastName != null) {
            query.setParameter("lastName", lastName + "%");
        }
        if (firstName != null) {
            query.setParameter("firstName", firstName + "%");
        }

        // ... other parameters ... 
        // 4. Execute query and retrieve list of Person objects
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
