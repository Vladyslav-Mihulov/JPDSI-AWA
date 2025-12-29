package com.jsfcourse.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jakarta.persistence.TypedQuery;

import com.jsfcourse.entities.Users;
import com.jsfcourse.entities.Orders;
import com.jsfcourse.entities.ProductOrder;
import com.jsfcourse.entities.ProductOrderPK;

/**
 *
 * @author vladm
 */
@Stateless
public class OrdersDAO {

    @PersistenceContext(unitName = "jsf_course_sushiPU")
    private EntityManager em;

    public void createOrder(Orders order) {
        em.persist(order);
    }

    public Orders mergeOrder(Orders order) {
        return em.merge(order);
    }

    public void removeOrder(Orders order) {
        em.remove(em.merge(order));
    }

    public Orders findById(Long id) {
        return em.find(Orders.class, id);
    }

    public List<Orders> findByUserAndStatus(Users user, int status) {
        return em.createQuery(
                "SELECT o FROM Orders o LEFT JOIN FETCH o.productOrderCollection WHERE o.userIdUser = :user AND o.status = :status",
                Orders.class)
                .setParameter("user", user)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Orders> findAll() {
        return em.createQuery("SELECT o FROM Orders o", Orders.class).getResultList();
    }
}
