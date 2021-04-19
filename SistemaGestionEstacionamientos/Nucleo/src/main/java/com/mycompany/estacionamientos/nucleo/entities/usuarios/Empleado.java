/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.nucleo.entities.usuarios;

import com.mycompany.estacionamientos.nucleo.entities.Estacionamiento;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class Empleado extends Usuario {

    private Estacionamiento lugarDeTrabajo;

    public Estacionamiento getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public void setLugarDeTrabajo(Estacionamiento lugarDeTrabajo) {
        this.lugarDeTrabajo = lugarDeTrabajo;
    }

}
