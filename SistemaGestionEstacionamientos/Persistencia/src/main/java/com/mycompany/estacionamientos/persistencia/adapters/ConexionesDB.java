package com.mycompany.estacionamientos.persistencia.adapters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class ConexionesDB {

    private static ConexionesDB instancia;
    private EntityManagerFactory emf;

    public EntityManagerFactory getEMF() {
        return emf;
    }

    private ConexionesDB() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_estacionamientos.persistencia_jar_1.0-SNAPSHOTPU");
    }

    public static ConexionesDB getInstance() {

        if (instancia == null) {
            instancia = new ConexionesDB();
        }

        return instancia;

    }

}
