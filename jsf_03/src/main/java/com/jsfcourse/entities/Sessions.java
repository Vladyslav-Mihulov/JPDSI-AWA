/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author vladm
 */
@Entity
@Table(name = "sessions")
@NamedQueries({
    @NamedQuery(name = "Sessions.findAll", query = "SELECT s FROM Sessions s"),
    @NamedQuery(name = "Sessions.findById", query = "SELECT s FROM Sessions s WHERE s.id = :id"),
    @NamedQuery(name = "Sessions.findByUserId", query = "SELECT s FROM Sessions s WHERE s.userId = :userId"),
    @NamedQuery(name = "Sessions.findByIpAddress", query = "SELECT s FROM Sessions s WHERE s.ipAddress = :ipAddress"),
    @NamedQuery(name = "Sessions.findByLastActivity", query = "SELECT s FROM Sessions s WHERE s.lastActivity = :lastActivity")})
public class Sessions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "id")
    private String id;
    @Column(name = "user_id")
    private BigInteger userId;
    @Size(max = 45)
    @Column(name = "ip_address")
    private String ipAddress;
    @Lob
    @Size(max = 65535)
    @Column(name = "user_agent")
    private String userAgent;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "payload")
    private String payload;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_activity")
    private int lastActivity;

    public Sessions() {
    }

    public Sessions(String id) {
        this.id = id;
    }

    public Sessions(String id, String payload, int lastActivity) {
        this.id = id;
        this.payload = payload;
        this.lastActivity = lastActivity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public int getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(int lastActivity) {
        this.lastActivity = lastActivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sessions)) {
            return false;
        }
        Sessions other = (Sessions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Sessions[ id=" + id + " ]";
    }
    
}
