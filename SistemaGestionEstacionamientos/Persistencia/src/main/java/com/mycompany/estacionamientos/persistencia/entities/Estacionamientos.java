/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.persistencia.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Tiber
 */
@Entity
@Table(name = "estacionamientos")
@NamedQueries({
    @NamedQuery(name = "Estacionamientos.findAll", query = "SELECT e FROM Estacionamientos e"),
    @NamedQuery(name = "Estacionamientos.findByIdestacionamientos", query = "SELECT e FROM Estacionamientos e WHERE e.idestacionamientos = :idestacionamientos"),
    @NamedQuery(name = "Estacionamientos.findByNombre", query = "SELECT e FROM Estacionamientos e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estacionamientos.findByDireccion", query = "SELECT e FROM Estacionamientos e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Estacionamientos.findByTelefono", query = "SELECT e FROM Estacionamientos e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Estacionamientos.findByUrl", query = "SELECT e FROM Estacionamientos e WHERE e.url = :url"),
    @NamedQuery(name = "Estacionamientos.findByLatitud", query = "SELECT e FROM Estacionamientos e WHERE e.latitud = :latitud"),
    @NamedQuery(name = "Estacionamientos.findByLongitud", query = "SELECT e FROM Estacionamientos e WHERE e.longitud = :longitud")})
public class Estacionamientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestacionamientos")
    private Integer idestacionamientos;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "url")
    private String url;
    @Column(name = "latitud")
    private String latitud;
    @Column(name = "longitud")
    private String longitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacionamientosIdestacionamientos")
    private List<Tarifas> tarifasList;
    @JoinColumn(name = "propietarios_idpropietarios", referencedColumnName = "idpropietarios")
    @ManyToOne(optional = false)
    private Propietarios propietariosIdpropietarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacionamientosIdestacionamientos")
    private List<Empleados> empleadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacionamientosIdestacionamientos")
    private List<Espacios> espaciosList;

    public Estacionamientos() {
    }

    public Estacionamientos(Integer idestacionamientos) {
        this.idestacionamientos = idestacionamientos;
    }

    public Estacionamientos(Integer idestacionamientos, String nombre, String direccion, String telefono) {
        this.idestacionamientos = idestacionamientos;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Integer getIdestacionamientos() {
        return idestacionamientos;
    }

    public void setIdestacionamientos(Integer idestacionamientos) {
        this.idestacionamientos = idestacionamientos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public List<Tarifas> getTarifasList() {
        return tarifasList;
    }

    public void setTarifasList(List<Tarifas> tarifasList) {
        this.tarifasList = tarifasList;
    }

    public Propietarios getPropietariosIdpropietarios() {
        return propietariosIdpropietarios;
    }

    public void setPropietariosIdpropietarios(Propietarios propietariosIdpropietarios) {
        this.propietariosIdpropietarios = propietariosIdpropietarios;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    public List<Espacios> getEspaciosList() {
        return espaciosList;
    }

    public void setEspaciosList(List<Espacios> espaciosList) {
        this.espaciosList = espaciosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestacionamientos != null ? idestacionamientos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estacionamientos)) {
            return false;
        }
        Estacionamientos other = (Estacionamientos) object;
        if ((this.idestacionamientos == null && other.idestacionamientos != null) || (this.idestacionamientos != null && !this.idestacionamientos.equals(other.idestacionamientos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.estacionamientos.persistencia.entities.Estacionamientos[ idestacionamientos=" + idestacionamientos + " ]";
    }
    
}
