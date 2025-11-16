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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vladm
 */
@Entity
@Table(name = "user_role")
@NamedQueries({
    @NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u"),
    @NamedQuery(name = "UserRole.findByUserIdUser", query = "SELECT u FROM UserRole u WHERE u.userRolePK.userIdUser = :userIdUser"),
    @NamedQuery(name = "UserRole.findByRoleIdRole", query = "SELECT u FROM UserRole u WHERE u.userRolePK.roleIdRole = :roleIdRole"),
    @NamedQuery(name = "UserRole.findByDateStart1", query = "SELECT u FROM UserRole u WHERE u.dateStart1 = :dateStart1"),
    @NamedQuery(name = "UserRole.findByDateEnd1", query = "SELECT u FROM UserRole u WHERE u.dateEnd1 = :dateEnd1")})
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserRolePK userRolePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_start1")
    @Temporal(TemporalType.DATE)
    private Date dateStart1;
    @Column(name = "date_end1")
    @Temporal(TemporalType.DATE)
    private Date dateEnd1;
    @JoinColumn(name = "role_id_role", referencedColumnName = "id_role", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;
    @JoinColumn(name = "user_id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public UserRole() {
    }

    public UserRole(UserRolePK userRolePK) {
        this.userRolePK = userRolePK;
    }

    public UserRole(UserRolePK userRolePK, Date dateStart1) {
        this.userRolePK = userRolePK;
        this.dateStart1 = dateStart1;
    }

    public UserRole(long userIdUser, long roleIdRole) {
        this.userRolePK = new UserRolePK(userIdUser, roleIdRole);
    }

    public UserRolePK getUserRolePK() {
        return userRolePK;
    }

    public void setUserRolePK(UserRolePK userRolePK) {
        this.userRolePK = userRolePK;
    }

    public Date getDateStart1() {
        return dateStart1;
    }

    public void setDateStart1(Date dateStart1) {
        this.dateStart1 = dateStart1;
    }

    public Date getDateEnd1() {
        return dateEnd1;
    }

    public void setDateEnd1(Date dateEnd1) {
        this.dateEnd1 = dateEnd1;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRolePK != null ? userRolePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRole)) {
            return false;
        }
        UserRole other = (UserRole) object;
        if ((this.userRolePK == null && other.userRolePK != null) || (this.userRolePK != null && !this.userRolePK.equals(other.userRolePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.UserRole[ userRolePK=" + userRolePK + " ]";
    }
    
}
