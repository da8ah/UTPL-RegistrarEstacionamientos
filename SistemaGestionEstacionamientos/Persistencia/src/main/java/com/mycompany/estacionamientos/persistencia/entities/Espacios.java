/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.persistencia.entities;

import java.io.Serializable;
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
@Table(name = "espacios")
@NamedQueries({
    @NamedQuery(name = "Espacios.findAll", query = "SELECT e FROM Espacios e"),
    @NamedQuery(name = "Espacios.findByIdespacios", query = "SELECT e FROM Espacios e WHERE e.idespacios = :idespacios"),
    @NamedQuery(name = "Espacios.findByReferenciaIdentificacion", query = "SELECT e FROM Espacios e WHERE e.referenciaIdentificacion = :referenciaIdentificacion"),
    @NamedQuery(name = "Espacios.findByDescripcion", query = "SELECT e FROM Espacios e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Espacios.findByDisponible", query = "SELECT e FROM Espacios e WHERE e.disponible = :disponible")})
public class Espacios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idespacios")
    private Integer idespacios;
    @Basic(optional = false)
    @Column(name = "referencia_identificacion")
    private String referenciaIdentificacion;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "disponible")
    private String disponible;
    @JoinColumn(name = "estacionamientos_idestacionamientos", referencedColumnName = "idestacionamientos")
    @ManyToOne(optional = false)
    private Estacionamientos estacionamientosIdestacionamientos;

    public Espacios() {
    }

    public Espacios(Integer idespacios) {
        this.idespacios = idespacios;
    }

    public Espacios(Integer idespacios, String referenciaIdentificacion, String descripcion, String disponible) {
        this.idespacios = idespacios;
        this.referenciaIdentificacion = referenciaIdentificacion;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }

    public Integer getIdespacios() {
        return idespacios;
    }

    public void setIdespacios(Integer idespacios) {
        this.idespacios = idespacios;
    }

    public String getReferenciaIdentificacion() {
        return referenciaIdentificacion;
    }

    public void setReferenciaIdentificacion(String referenciaIdentificacion) {
        this.referenciaIdentificacion = referenciaIdentificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
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
        hash += (idespacios != null ? idespacios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Espacios)) {
            return false;
        }
        Espacios other = (Espacios) object;
        if ((this.idespacios == null && other.idespacios != null) || (this.idespacios != null && !this.idespacios.equals(other.idespacios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.estacionamientos.persistencia.entities.Espacios[ idespacios=" + idespacios + " ]";
    }
    
}
