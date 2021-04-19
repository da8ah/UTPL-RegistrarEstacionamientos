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
public class Tarifa {

    private int numero;
    private String descripcion;
    private int tiempoEnMinutos;
    private double costo;

    public Tarifa(int numero, String descripcion, int tiempoEnMinutos, double costo) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.tiempoEnMinutos = tiempoEnMinutos;
        this.costo = costo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTiempoEnMinutos() {
        return tiempoEnMinutos;
    }

    public void setTiempoEnMinutos(int tiempoEnMinutos) {
        this.tiempoEnMinutos = tiempoEnMinutos;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

}
