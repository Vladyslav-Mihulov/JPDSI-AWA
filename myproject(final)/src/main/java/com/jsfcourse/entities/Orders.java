/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author vladm
 */
@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByIdOrder", query = "SELECT o FROM Orders o WHERE o.idOrder = :idOrder"),
    @NamedQuery(name = "Orders.findByStatus", query = "SELECT o FROM Orders o WHERE o.status = :status"),
    @NamedQuery(name = "Orders.findByDateOrder", query = "SELECT o FROM Orders o WHERE o.dateOrder = :dateOrder"),
    @NamedQuery(name = "Orders.findByDateEndOrder", query = "SELECT o FROM Orders o WHERE o.dateEndOrder = :dateEndOrder"),
    @NamedQuery(name = "Orders.findByTotalPrice", query = "SELECT o FROM Orders o WHERE o.totalPrice = :totalPrice")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_order")
    private Long idOrder;
    @Column(name = "status")
    private Integer status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_order")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOrder;
    @Column(name = "date_end_order")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEndOrder;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_price")
    private Float totalPrice;
    @JoinColumn(name = "user_id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private Users userIdUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Collection<ProductOrder> productOrderCollection;

    public Orders() {
    }

    public Orders(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Orders(Long idOrder, Date dateOrder) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDateEndOrder() {
        return dateEndOrder;
    }

    public void setDateEndOrder(Date dateEndOrder) {
        this.dateEndOrder = dateEndOrder;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Users getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(Users userIdUser) {
        this.userIdUser = userIdUser;
    }

    public Collection<ProductOrder> getProductOrderCollection() {
        return productOrderCollection;
    }

    public void setProductOrderCollection(Collection<ProductOrder> productOrderCollection) {
        this.productOrderCollection = productOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Orders[ idOrder=" + idOrder + " ]";
    }
    
}
