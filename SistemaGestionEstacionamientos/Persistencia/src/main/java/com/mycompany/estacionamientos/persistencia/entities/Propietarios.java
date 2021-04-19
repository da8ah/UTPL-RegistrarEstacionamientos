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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Tiber
 */
@Entity
@Table(name = "propietarios")
@NamedQueries({
    @NamedQuery(name = "Propietarios.findAll", query = "SELECT p FROM Propietarios p"),
    @NamedQuery(name = "Propietarios.findByIdpropietarios", query = "SELECT p FROM Propietarios p WHERE p.idpropietarios = :idpropietarios"),
    @NamedQuery(name = "Propietarios.findByNombres", query = "SELECT p FROM Propietarios p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Propietarios.findByDireccion", query = "SELECT p FROM Propietarios p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Propietarios.findByEmail", query = "SELECT p FROM Propietarios p WHERE p.email = :email"),
    @NamedQuery(name = "Propietarios.findByLogin", query = "SELECT p FROM Propietarios p WHERE p.login = :login"),
    @NamedQuery(name = "Propietarios.findByPassword", query = "SELECT p FROM Propietarios p WHERE p.password = :password")})
public class Propietarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpropietarios")
    private Integer idpropietarios;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propietariosIdpropietarios")
    private List<Estacionamientos> estacionamientosList;

    public Propietarios() {
    }

    public Propietarios(Integer idpropietarios) {
        this.idpropietarios = idpropietarios;
    }

    public Propietarios(Integer idpropietarios, String nombres, String email, String login, String password) {
        this.idpropietarios = idpropietarios;
        this.nombres = nombres;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Integer getIdpropietarios() {
        return idpropietarios;
    }

    public void setIdpropietarios(Integer idpropietarios) {
        this.idpropietarios = idpropietarios;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Estacionamientos> getEstacionamientosList() {
        return estacionamientosList;
    }

    public void setEstacionamientosList(List<Estacionamientos> estacionamientosList) {
        this.estacionamientosList = estacionamientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpropietarios != null ? idpropietarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propietarios)) {
            return false;
        }
        Propietarios other = (Propietarios) object;
        if ((this.idpropietarios == null && other.idpropietarios != null) || (this.idpropietarios != null && !this.idpropietarios.equals(other.idpropietarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.estacionamientos.persistencia.entities.Propietarios[ idpropietarios=" + idpropietarios + " ]";
    }
    
}
