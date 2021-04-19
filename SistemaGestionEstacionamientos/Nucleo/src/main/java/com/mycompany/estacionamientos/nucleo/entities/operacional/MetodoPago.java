/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.nucleo.entities.operacional;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public abstract class MetodoPago {
    
    private double saldo;
    
    public abstract double comprobarSaldo (double saldo);
    public abstract double descontarPago(double saldo);
    
}
