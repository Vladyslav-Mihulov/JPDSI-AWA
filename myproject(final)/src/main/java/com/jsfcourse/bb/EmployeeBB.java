package com.jsfcourse.bb;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Date;
import java.util.List;

import com.jsfcourse.dao.OrdersDAO;
import com.jsfcourse.entities.Orders;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
/**
 *
 * @author vladm
 */
@Named
@RequestScoped
public class EmployeeBB {

    @Inject
    private OrdersDAO ordersDAO;

    private List<Orders> allOrders;


    private List<Orders> activeOrders;

    public List<Orders> getAllOrders() {
        if (allOrders == null) {
            allOrders = ordersDAO.findAllWithItems();
        }
        return allOrders;
    }

    public List<Orders> getActiveOrders() {
        if (activeOrders == null) {
            activeOrders = ordersDAO.findActiveOrdersWithItems();
        }
        return activeOrders;
    }


    public void changeStatus(Long orderId) {
        Orders order = ordersDAO.findById(orderId);
        if (order == null) return;

        int status = order.getStatus();

        if (status < 3) {
            order.setStatus(status + 1);
            if (order.getStatus() == 3) {
                order.setDateEndOrder(new Date());
            }
            ordersDAO.mergeOrder(order);
        }
        
         FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO,
                             "Status zamówienia został zmieniony",
                             "Zamówienie #" + order.getIdOrder() + " teraz ma status " + order.getStatus()));
    }
    
}
