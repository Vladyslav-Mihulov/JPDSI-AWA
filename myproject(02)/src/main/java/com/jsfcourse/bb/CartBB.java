package com.jsfcourse.bb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.servlet.http.HttpSession;
import jakarta.faces.application.FacesMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jsfcourse.dao.OrdersDAO;
import com.jsfcourse.dao.ProductOrderDAO;
import com.jsfcourse.dao.ProductsDAO;
import com.jsfcourse.entities.Orders;
import com.jsfcourse.entities.ProductOrder;
import com.jsfcourse.entities.ProductOrderPK;
import com.jsfcourse.entities.Products;
import com.jsfcourse.entities.Users;

@Named
@SessionScoped
public class CartBB implements Serializable {

    @Inject
    private OrdersDAO ordersDAO;

    @Inject
    private ProductOrderDAO productOrderDAO;

    @Inject
    private ProductsDAO productsDAO;

    private Orders cartOrder;

    @PostConstruct
    public void init() {
        cartOrder = null;
    }

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


    public Orders getCart() {
        Users user = getCurrentUser();
        if (user == null) {
            return null;
        }

        if (cartOrder == null) {
  
            List<Orders> orders = ordersDAO.findByUserAndStatus(user, 0); 
            if (!orders.isEmpty()) {
                cartOrder = orders.get(0);
            } else {
                cartOrder = new Orders();
                cartOrder.setUserIdUser(user);
                cartOrder.setStatus(0);
                cartOrder.setDateOrder(new java.util.Date());
                cartOrder.setTotalPrice(0f);
                cartOrder.setProductOrderCollection(new ArrayList<>()); 
                ordersDAO.createOrder(cartOrder);
            }
        }
        return cartOrder;
    }


    public List<ProductOrder> getCartItems() {
        Orders order = getCart();
        if (order != null && order.getProductOrderCollection() != null) {
            return new ArrayList<>(order.getProductOrderCollection());
        }
        return new ArrayList<>();
    }


    public void addToCart(Long productId, int quantity) {
        Orders order = getCart();
        if (order == null) {
            return;
        }

        Products product = productsDAO.findById(productId);
        if (product == null) {
            return;
        }

        ProductOrder item = order.getProductOrderCollection().stream()
                .filter(po -> po.getProducts().getIdProduct().equals(productId))
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
            item.setPrice(product.getPrice() * item.getQuantity());
            productOrderDAO.merge(item);
        } else {
            item = new ProductOrder();
            item.setProductOrderPK(new ProductOrderPK(order.getIdOrder(), product.getIdProduct()));
            item.setOrders(order);
            item.setProducts(product);
            item.setQuantity(quantity);
            item.setPrice(product.getPrice() * quantity);
            productOrderDAO.create(item);
            order.getProductOrderCollection().add(item);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Produkt został dodany do koszyka!", null));
        updateTotalPrice();
    }

    public void removeFromCart(ProductOrder item) {
        if (item == null) {
            return;
        }

        Orders order = getCart();
        if (order == null) {
            return;
        }

        int newQuantity = item.getQuantity() - 1;

        if (newQuantity > 0) {
            item.setQuantity(newQuantity);
            item.setPrice(item.getProducts().getPrice() * newQuantity);
            productOrderDAO.merge(item);
        } else {
            productOrderDAO.delete(item.getProductOrderPK());

            order.getProductOrderCollection()
                    .removeIf(po
                            -> po.getProductOrderPK().equals(item.getProductOrderPK())
                    );
        }

        updateTotalPrice();
    }


    public double getTotalPrice() {
        Orders order = getCart();
        if (order == null) {
            return 0.0;
        }

        return order.getProductOrderCollection().stream()
                .mapToDouble(ProductOrder::getPrice)
                .sum();
    }


    public String checkout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Orders order = getCart();
        if (order == null) {
            ctx.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_WARN, "Przed złożeniem zamówienia dodaj produkty do koszyka", null ));
            return null;
        }

        order.setStatus(1); 
        order.setDateOrder(new java.util.Date());
        ordersDAO.mergeOrder(order);

        cartOrder = null; 

        ctx.getExternalContext().getFlash().setKeepMessages(true);
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zamówienie zostało utworzone","Dziękujemy za zakup!"));

        return "/pages/public/home-page?faces-redirect=true";
    }


    private void updateTotalPrice() {
        Orders order = getCart();
        if (order == null) {
            return;
        }

        double total = order.getProductOrderCollection().stream()
                .mapToDouble(ProductOrder::getPrice)
                .sum();
        order.setTotalPrice((float) total);
        ordersDAO.mergeOrder(order);
    }
}
