/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.nucleo.entities.operacional;

import java.util.Date;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class PagoFabrica {

    private Date fecha;
    private double valorTotalPago;
    private MetodoPago metodoPago;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValorTotalPago() {
        return valorTotalPago;
    }

    public void setValorTotalPago(double valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public MetodoPago pagarMediante(String tipo) {

        switch (tipo) {
            case "Efectivo":
                metodoPago = new Efectivo();
                break;
            case "Tarjeta":
                metodoPago = new Tarjeta();
                break;
        }
        return metodoPago;

    }

}
