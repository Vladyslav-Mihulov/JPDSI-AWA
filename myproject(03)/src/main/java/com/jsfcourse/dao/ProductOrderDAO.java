package com.jsfcourse.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import com.jsfcourse.entities.Orders;
import com.jsfcourse.entities.ProductOrder;
import com.jsfcourse.entities.ProductOrderPK;
/**
 *
 * @author vladm
 */
@Stateless
public class ProductOrderDAO {
    @PersistenceContext(unitName = "jsf_course_sushiPU")
    private EntityManager em;

    public void create(ProductOrder productOrder) {
        em.persist(productOrder);
    }

    public ProductOrder merge(ProductOrder productOrder) {
        return em.merge(productOrder);
    }
    
    public void delete(ProductOrderPK pk) {
        em.createQuery("""
            DELETE FROM ProductOrder p
            WHERE p.productOrderPK = :pk
        """)
        .setParameter("pk", pk)
        .executeUpdate();
    }
    
    public void remove(ProductOrder productOrder) {
        em.remove(em.merge(productOrder));
    }

    public ProductOrder find(ProductOrderPK pk) {
        return em.find(ProductOrder.class, pk);
    }

    public List<ProductOrder> findByOrderId(Long orderId) {
        return em.createQuery("SELECT po FROM ProductOrder po WHERE po.orders.idOrder = :orderId", ProductOrder.class)
                 .setParameter("orderId", orderId)
                 .getResultList();
    }
}
