package com.mycompany.estacionamientos.persistencia.adapters;

import com.mycompany.estacionamientos.nucleo.entities.Espacio;
import com.mycompany.estacionamientos.nucleo.entities.Estacionamiento;
import com.mycompany.estacionamientos.nucleo.entities.Tarifa;
import com.mycompany.estacionamientos.nucleo.entities.usuarios.Empleado;
import com.mycompany.estacionamientos.nucleo.entities.usuarios.Propietario;
import com.mycompany.estacionamientos.nucleo.ports.IPersistenciaRegistrarCuenta;
import com.mycompany.estacionamientos.persistencia.entities.Empleados;
import com.mycompany.estacionamientos.persistencia.entities.Espacios;
import com.mycompany.estacionamientos.persistencia.entities.Estacionamientos;
import com.mycompany.estacionamientos.persistencia.entities.Propietarios;
import com.mycompany.estacionamientos.persistencia.entities.Tarifas;
import com.mycompany.estacionamientos.persistencia.entities.controllers.EmpleadosJpaController;
import com.mycompany.estacionamientos.persistencia.entities.controllers.EspaciosJpaController;
import com.mycompany.estacionamientos.persistencia.entities.controllers.EstacionamientosJpaController;
import com.mycompany.estacionamientos.persistencia.entities.controllers.PropietariosJpaController;
import com.mycompany.estacionamientos.persistencia.entities.controllers.TarifasJpaController;
import java.math.BigDecimal;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class PersistenciaDeCuentaBasica implements IPersistenciaRegistrarCuenta {

    @Override
    public boolean guardarCuentaCreada(Estacionamiento estacionamiento) {

        // Se deben llamar las funciones en un orden específico.
        // Registro de Propietario
        if (guardarPropietario(estacionamiento.getPropietario())) {
            // Registro de Estacionamiento
            guardarEstacionamiento(estacionamiento);
            return true;
        }
        return false;
    }

    @Override
    public boolean guardarCuentaConPropietarioExistente(Estacionamiento estacionamiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean guardarPropietario(Propietario propietario) {
        PropietariosJpaController jpaPropietariosDB = new PropietariosJpaController(ConexionesDB.getInstance().getEMF());
        Propietarios objPropietariosDB = new Propietarios();

        objPropietariosDB.setIdpropietarios(Integer.parseInt(propietario.getPersona().getCedula()));
        objPropietariosDB.setNombres(propietario.getPersona().getNombre());
        objPropietariosDB.setDireccion(propietario.getPersona().getDireccion());
        objPropietariosDB.setEmail(propietario.getPersona().getEmail());
        objPropietariosDB.setLogin(propietario.getLogin());
        objPropietariosDB.setPassword(propietario.getPassword());

        try {
            jpaPropietariosDB.create(objPropietariosDB);
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    private boolean guardarEstacionamiento(Estacionamiento estacionamiento) {
        EstacionamientosJpaController jpaEstacionamientosDB = new EstacionamientosJpaController(ConexionesDB.getInstance().getEMF());
        Estacionamientos objEstacionamientosDB = new Estacionamientos();

        PropietariosJpaController jpaPropietariosDB = new PropietariosJpaController(ConexionesDB.getInstance().getEMF());
        Propietarios objPropietariosDB = jpaPropietariosDB.findPropietarios(Integer.parseInt(estacionamiento.getPropietario().getPersona().getCedula()));

        // Datos del Estacionamiento
        objEstacionamientosDB.setIdestacionamientos(jpaEstacionamientosDB.getEstacionamientosCount() + 1);
        objEstacionamientosDB.setNombre(estacionamiento.getNombre());
        objEstacionamientosDB.setDireccion(estacionamiento.getDireccion());
        objEstacionamientosDB.setTelefono(estacionamiento.getTelefono());
        objEstacionamientosDB.setPropietariosIdpropietarios(objPropietariosDB); // Referencia al propietario

        jpaEstacionamientosDB.create(objEstacionamientosDB);

        // Espacios
        EspaciosJpaController jpaEspaciosDB = new EspaciosJpaController(ConexionesDB.getInstance().getEMF());

        Espacios objEspaciosDB = new Espacios();
        for (Espacio espacio : estacionamiento.getListaEspacios()) {
            objEspaciosDB.setIdespacios(jpaEspaciosDB.getEspaciosCount() + 1);
            objEspaciosDB.setReferenciaIdentificacion(espacio.getReferenciaIdentificacion());
            objEspaciosDB.setDescripcion(espacio.getDescripcion());
            objEspaciosDB.setDisponible(espacio.getDisponible());
            objEspaciosDB.setEstacionamientosIdestacionamientos(objEstacionamientosDB); // Referencia al estacionamiento

            jpaEspaciosDB.create(objEspaciosDB);
        }

        // Tarifas
        TarifasJpaController jpaTarifasDB = new TarifasJpaController(ConexionesDB.getInstance().getEMF());

        Tarifas objTarifasDB = new Tarifas();
        for (Tarifa tarifa : estacionamiento.getListaTarifas()) {
            objTarifasDB.setIdtarifas(jpaTarifasDB.getTarifasCount() + 1);
            objTarifasDB.setDescripcion(tarifa.getDescripcion());
            objTarifasDB.setCantidadTiempo(String.valueOf(tarifa.getTiempoEnMinutos()));
            objTarifasDB.setCosto(BigDecimal.valueOf(tarifa.getCosto()));
            objTarifasDB.setEstacionamientosIdestacionamientos(objEstacionamientosDB); // Referencia al estacionamiento

            jpaTarifasDB.create(objTarifasDB);
        }
        
        return true;
    }

}
