/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author vladm
 */
@Embeddable
public class ProductOrderPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "order_id_order")
    private long orderIdOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_id_product")
    private long productIdProduct;

    public ProductOrderPK() {
    }

    public ProductOrderPK(long orderIdOrder, long productIdProduct) {
        this.orderIdOrder = orderIdOrder;
        this.productIdProduct = productIdProduct;
    }

    public long getOrderIdOrder() {
        return orderIdOrder;
    }

    public void setOrderIdOrder(long orderIdOrder) {
        this.orderIdOrder = orderIdOrder;
    }

    public long getProductIdProduct() {
        return productIdProduct;
    }

    public void setProductIdProduct(long productIdProduct) {
        this.productIdProduct = productIdProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderIdOrder;
        hash += (int) productIdProduct;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductOrderPK)) {
            return false;
        }
        ProductOrderPK other = (ProductOrderPK) object;
        if (this.orderIdOrder != other.orderIdOrder) {
            return false;
        }
        if (this.productIdProduct != other.productIdProduct) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.ProductOrderPK[ orderIdOrder=" + orderIdOrder + ", productIdProduct=" + productIdProduct + " ]";
    }
    
}
