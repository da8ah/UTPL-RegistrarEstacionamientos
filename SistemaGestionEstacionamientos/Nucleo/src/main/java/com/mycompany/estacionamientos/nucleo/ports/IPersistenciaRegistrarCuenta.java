package com.mycompany.estacionamientos.nucleo.ports;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
import com.mycompany.estacionamientos.nucleo.entities.Estacionamiento;

public interface IPersistenciaRegistrarCuenta {

    public boolean guardarCuentaCreada(Estacionamiento estacionamiento);
    public boolean guardarCuentaConPropietarioExistente(Estacionamiento estacionamiento);

}
