package com.jsfcourse.dao;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.jsfcourse.entities.Products;
/**
 *
 * @author vladm
 */
@Stateless
public class ProductsDAO {
    private static final String UNIT_NAME = "jsf_course_sushiPU";
    
    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;
    
    public List<Products> findAll() {
        return em.createNamedQuery("Products.findAll", Products.class)
                 .getResultList();
    }
    
    public List<Products> findActiveProducts() {
        return em.createQuery(
                "SELECT p FROM Products p WHERE p.ifActive = '1'", Products.class
        ).getResultList();
    }
    
    public Products findById(Long id) {
        return em.find(Products.class, id);
    }
}
