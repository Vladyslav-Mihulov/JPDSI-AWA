package com.jsfcourse.bb;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;

import java.io.Serializable;
import java.util.List;

import com.jsfcourse.dao.OrdersDAO;
import com.jsfcourse.entities.Orders;
import com.jsfcourse.entities.Users;
import jakarta.servlet.http.HttpSession;
/**
 *
 * @author vladm
 */
@Named
@SessionScoped
public class OrderHistoryBB implements Serializable {

    @Inject
    private OrdersDAO ordersDAO;

    private List<Orders> userOrders;


    private Users getCurrentUser() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        if (session == null) {
            return null;
        }

        RemoteClient<?> client = RemoteClient.load(session);
        if (client != null) {
            return (Users) client.getDetails();
        }
        return null;
    }

    public List<Orders> getUserOrders() {
        if (userOrders == null) {
            Users user = getCurrentUser();
            if (user != null) {
                userOrders = ordersDAO.findByUser(user);
            }
        }
        return userOrders;
    }

}
