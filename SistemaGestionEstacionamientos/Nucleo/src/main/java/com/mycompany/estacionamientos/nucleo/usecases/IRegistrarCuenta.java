/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.nucleo.usecases;

import com.mycompany.estacionamientos.nucleo.entities.Espacio;
import com.mycompany.estacionamientos.nucleo.entities.Estacionamiento;
import com.mycompany.estacionamientos.nucleo.entities.Tarifa;
import com.mycompany.estacionamientos.nucleo.entities.usuarios.Empleado;
import com.mycompany.estacionamientos.nucleo.entities.usuarios.Propietario;
import com.mycompany.estacionamientos.nucleo.ports.IPersistenciaRegistrarCuenta;
import java.util.List;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public interface IRegistrarCuenta {

    public Estacionamiento crearCuentaNueva(Propietario propietario, Estacionamiento estacionamiento, List<Espacio> listaEspacios, List<Tarifa> listaTarifas, List<Empleado> listaEmpleados);

    public boolean guardarCuentaCreada(Estacionamiento estacionamiento, IPersistenciaRegistrarCuenta iPersistenciaRegistrarCuenta);

}
