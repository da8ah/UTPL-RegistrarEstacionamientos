/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.persistencia.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Tiber
 */
@Entity
@Table(name = "tarifas")
@NamedQueries({
    @NamedQuery(name = "Tarifas.findAll", query = "SELECT t FROM Tarifas t"),
    @NamedQuery(name = "Tarifas.findByIdtarifas", query = "SELECT t FROM Tarifas t WHERE t.idtarifas = :idtarifas"),
    @NamedQuery(name = "Tarifas.findByDescripcion", query = "SELECT t FROM Tarifas t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tarifas.findByCantidadTiempo", query = "SELECT t FROM Tarifas t WHERE t.cantidadTiempo = :cantidadTiempo"),
    @NamedQuery(name = "Tarifas.findByCosto", query = "SELECT t FROM Tarifas t WHERE t.costo = :costo")})
public class Tarifas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtarifas")
    private Integer idtarifas;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "cantidad_tiempo")
    private String cantidadTiempo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "costo")
    private BigDecimal costo;
    @JoinColumn(name = "estacionamientos_idestacionamientos", referencedColumnName = "idestacionamientos")
    @ManyToOne(optional = false)
    private Estacionamientos estacionamientosIdestacionamientos;

    public Tarifas() {
    }

    public Tarifas(Integer idtarifas) {
        this.idtarifas = idtarifas;
    }

    public Tarifas(Integer idtarifas, String descripcion, String cantidadTiempo, BigDecimal costo) {
        this.idtarifas = idtarifas;
        this.descripcion = descripcion;
        this.cantidadTiempo = cantidadTiempo;
        this.costo = costo;
    }

    public Integer getIdtarifas() {
        return idtarifas;
    }

    public void setIdtarifas(Integer idtarifas) {
        this.idtarifas = idtarifas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidadTiempo() {
        return cantidadTiempo;
    }

    public void setCantidadTiempo(String cantidadTiempo) {
        this.cantidadTiempo = cantidadTiempo;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Estacionamientos getEstacionamientosIdestacionamientos() {
        return estacionamientosIdestacionamientos;
    }

    public void setEstacionamientosIdestacionamientos(Estacionamientos estacionamientosIdestacionamientos) {
        this.estacionamientosIdestacionamientos = estacionamientosIdestacionamientos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarifas != null ? idtarifas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifas)) {
            return false;
        }
        Tarifas other = (Tarifas) object;
        if ((this.idtarifas == null && other.idtarifas != null) || (this.idtarifas != null && !this.idtarifas.equals(other.idtarifas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.estacionamientos.persistencia.entities.Tarifas[ idtarifas=" + idtarifas + " ]";
    }
    
}
