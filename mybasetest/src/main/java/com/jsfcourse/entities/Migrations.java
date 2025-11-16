/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author vladm
 */
@Entity
@Table(name = "migrations")
@NamedQueries({
    @NamedQuery(name = "Migrations.findAll", query = "SELECT m FROM Migrations m"),
    @NamedQuery(name = "Migrations.findById", query = "SELECT m FROM Migrations m WHERE m.id = :id"),
    @NamedQuery(name = "Migrations.findByMigration", query = "SELECT m FROM Migrations m WHERE m.migration = :migration"),
    @NamedQuery(name = "Migrations.findByBatch", query = "SELECT m FROM Migrations m WHERE m.batch = :batch")})
public class Migrations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "migration")
    private String migration;
    @Basic(optional = false)
    @NotNull
    @Column(name = "batch")
    private int batch;

    public Migrations() {
    }

    public Migrations(Integer id) {
        this.id = id;
    }

    public Migrations(Integer id, String migration, int batch) {
        this.id = id;
        this.migration = migration;
        this.batch = batch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMigration() {
        return migration;
    }

    public void setMigration(String migration) {
        this.migration = migration;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
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
        if (!(object instanceof Migrations)) {
            return false;
        }
        Migrations other = (Migrations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Migrations[ id=" + id + " ]";
    }
    
}
