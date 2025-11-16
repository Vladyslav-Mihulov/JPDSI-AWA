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
public class UserRolePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id_user")
    private long userIdUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id_role")
    private long roleIdRole;

    public UserRolePK() {
    }

    public UserRolePK(long userIdUser, long roleIdRole) {
        this.userIdUser = userIdUser;
        this.roleIdRole = roleIdRole;
    }

    public long getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(long userIdUser) {
        this.userIdUser = userIdUser;
    }

    public long getRoleIdRole() {
        return roleIdRole;
    }

    public void setRoleIdRole(long roleIdRole) {
        this.roleIdRole = roleIdRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userIdUser;
        hash += (int) roleIdRole;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRolePK)) {
            return false;
        }
        UserRolePK other = (UserRolePK) object;
        if (this.userIdUser != other.userIdUser) {
            return false;
        }
        if (this.roleIdRole != other.roleIdRole) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.UserRolePK[ userIdUser=" + userIdUser + ", roleIdRole=" + roleIdRole + " ]";
    }
    
}
