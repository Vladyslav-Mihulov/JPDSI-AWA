/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author vladm
 */
@Entity
@Table(name = "product_order")
@NamedQueries({
    @NamedQuery(name = "ProductOrder.findAll", query = "SELECT p FROM ProductOrder p"),
    @NamedQuery(name = "ProductOrder.findByOrderIdOrder", query = "SELECT p FROM ProductOrder p WHERE p.productOrderPK.orderIdOrder = :orderIdOrder"),
    @NamedQuery(name = "ProductOrder.findByProductIdProduct", query = "SELECT p FROM ProductOrder p WHERE p.productOrderPK.productIdProduct = :productIdProduct"),
    @NamedQuery(name = "ProductOrder.findByQuantity", query = "SELECT p FROM ProductOrder p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "ProductOrder.findByPrice", query = "SELECT p FROM ProductOrder p WHERE p.price = :price")})
public class ProductOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductOrderPK productOrderPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @JoinColumn(name = "order_id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;
    @JoinColumn(name = "product_id_product", referencedColumnName = "id_product", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Products products;

    public ProductOrder() {
    }

    public ProductOrder(ProductOrderPK productOrderPK) {
        this.productOrderPK = productOrderPK;
    }

    public ProductOrder(ProductOrderPK productOrderPK, int quantity, double price) {
        this.productOrderPK = productOrderPK;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductOrder(long orderIdOrder, long productIdProduct) {
        this.productOrderPK = new ProductOrderPK(orderIdOrder, productIdProduct);
    }

    public ProductOrderPK getProductOrderPK() {
        return productOrderPK;
    }

    public void setProductOrderPK(ProductOrderPK productOrderPK) {
        this.productOrderPK = productOrderPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productOrderPK != null ? productOrderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductOrder)) {
            return false;
        }
        ProductOrder other = (ProductOrder) object;
        if ((this.productOrderPK == null && other.productOrderPK != null) || (this.productOrderPK != null && !this.productOrderPK.equals(other.productOrderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.ProductOrder[ productOrderPK=" + productOrderPK + " ]";
    }
    
}
