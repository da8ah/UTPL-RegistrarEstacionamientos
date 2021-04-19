/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.nucleo.entities;

import com.mycompany.estacionamientos.nucleo.entities.operacional.Reservacion;
import com.mycompany.estacionamientos.nucleo.entities.operacional.Servicio;
import com.mycompany.estacionamientos.nucleo.entities.usuarios.Empleado;
import com.mycompany.estacionamientos.nucleo.entities.usuarios.Propietario;
import java.util.List;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class Estacionamiento {

    private String nombre;
    private String direccion;
    private String telefono;
    private String url;
    private String email;
    private String latitud;
    private String longitud;

    private Propietario propietario;
    private List<Empleado> listaEmpleados;
    private List<Espacio> listaEspacios;
    private List<Tarifa> listaTarifas;

    private List<Servicio> listaServicios;
    private List<Reservacion> listaReservaciones;

    public Estacionamiento(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Estacionamiento(String nombre, String direccion, String telefono, String url, String email, String latitud, String longitud,
            List<Espacio> listaEspacios, List<Tarifa> listaTarifas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.url = url;
        this.email = email;
        this.latitud = latitud;
        this.longitud = longitud;

        this.listaEspacios = listaEspacios;
        this.listaTarifas = listaTarifas;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Espacio> getListaEspacios() {
        return listaEspacios;
    }

    public void setListaEspacios(List<Espacio> listaEspacios) {
        this.listaEspacios = listaEspacios;
    }

    public List<Tarifa> getListaTarifas() {
        return listaTarifas;
    }

    public void setListaTarifas(List<Tarifa> listaTarifas) {
        this.listaTarifas = listaTarifas;
    }

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public List<Reservacion> getListaReservaciones() {
        return listaReservaciones;
    }

    public void setListaReservaciones(List<Reservacion> listaReservaciones) {
        this.listaReservaciones = listaReservaciones;
    }

    @Override
    public String toString() {
        return "Estacionamiento{" + "nombre=" + nombre + ", direccion=" + direccion + ", propietario=" + propietario + ", listaEspacios=" + listaEspacios + ", listaTarifas=" + listaTarifas + '}';
    }

}
