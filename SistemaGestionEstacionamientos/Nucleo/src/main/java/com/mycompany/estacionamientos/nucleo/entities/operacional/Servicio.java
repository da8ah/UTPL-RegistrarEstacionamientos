/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.nucleo.entities.operacional;

import com.mycompany.estacionamientos.nucleo.entities.Espacio;
import com.mycompany.estacionamientos.nucleo.entities.Tarifa;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class Servicio {

    private Espacio espacio;
    private PagoFabrica pagoDelServicio;
    private Tarifa tarifa;

    private String estado;
    private Date fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private String placa;
    private double costoTarifa;

    public Servicio(Espacio espacio, String estado, Date fecha, LocalTime horaEntrada, String placa) {
        this.espacio = espacio;
        this.estado = estado;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.placa = placa;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getCostoTarifa() {
        return costoTarifa;
    }

    public void setCostoTarifa(double costoTarifa) {
        this.costoTarifa = costoTarifa;
    }

    public PagoFabrica getPagoDelServicio() {
        return pagoDelServicio;
    }

    public void setPagoDelServicio(PagoFabrica pagoDelServicio) {
        this.pagoDelServicio = pagoDelServicio;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

}
