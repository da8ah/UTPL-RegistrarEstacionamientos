/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.nucleo.entities.usuarios;

import com.mycompany.estacionamientos.nucleo.entities.Estacionamiento;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class Propietario extends Usuario {

    private Date fechaCreacion;
    private List<Estacionamiento> listaPropiedades;

    public Propietario(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Estacionamiento> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(List<Estacionamiento> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
