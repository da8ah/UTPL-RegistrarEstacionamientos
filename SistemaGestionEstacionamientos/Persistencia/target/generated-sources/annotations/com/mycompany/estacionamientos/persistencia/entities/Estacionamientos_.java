package com.mycompany.estacionamientos.persistencia.entities;

import com.mycompany.estacionamientos.persistencia.entities.Empleados;
import com.mycompany.estacionamientos.persistencia.entities.Espacios;
import com.mycompany.estacionamientos.persistencia.entities.Propietarios;
import com.mycompany.estacionamientos.persistencia.entities.Tarifas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T19:53:54")
@StaticMetamodel(Estacionamientos.class)
public class Estacionamientos_ { 

    public static volatile ListAttribute<Estacionamientos, Espacios> espaciosList;
    public static volatile SingularAttribute<Estacionamientos, String> latitud;
    public static volatile SingularAttribute<Estacionamientos, String> longitud;
    public static volatile ListAttribute<Estacionamientos, Empleados> empleadosList;
    public static volatile SingularAttribute<Estacionamientos, Integer> idestacionamientos;
    public static volatile ListAttribute<Estacionamientos, Tarifas> tarifasList;
    public static volatile SingularAttribute<Estacionamientos, String> direccion;
    public static volatile SingularAttribute<Estacionamientos, String> telefono;
    public static volatile SingularAttribute<Estacionamientos, String> nombre;
    public static volatile SingularAttribute<Estacionamientos, Propietarios> propietariosIdpropietarios;
    public static volatile SingularAttribute<Estacionamientos, String> url;

}