/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.nucleo.entities.operacional;

import com.mycompany.estacionamientos.nucleo.entities.Espacio;
import java.util.Date;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class Reservacion {

    private Espacio espacio;

    private String codigo;
    private Date fechaReservacion;
    private String placa;
    private int tiempoEnMinutos;

    public Reservacion(Espacio espacio, String codigo, Date fechaReservacion,
            String placa, int tiempoEnMinutos) {
        this.espacio = espacio;
        this.codigo = codigo;
        this.fechaReservacion = fechaReservacion;
        this.placa = placa;
        this.tiempoEnMinutos = tiempoEnMinutos;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(Date fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getTiempoEnMinutos() {
        return tiempoEnMinutos;
    }

    public void setTiempoEnMinutos(int tiempoEnMinutos) {
        this.tiempoEnMinutos = tiempoEnMinutos;
    }

}
