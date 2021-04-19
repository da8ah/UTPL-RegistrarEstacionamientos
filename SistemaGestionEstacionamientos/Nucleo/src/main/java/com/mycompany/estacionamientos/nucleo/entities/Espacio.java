/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.nucleo.entities;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class Espacio {

    private String referenciaIdentificacion;
    private String descripcion;
    private String disponible;

    public Espacio(String referenciaIdentificacion, String descripcion, String disponible) {
        this.referenciaIdentificacion = referenciaIdentificacion;
        this.descripcion = descripcion;
        this.disponible = disponible;
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

}
