package com.mycompany.estacionamientos.persistencia.entities;

import com.mycompany.estacionamientos.persistencia.entities.Estacionamientos;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T19:53:54")
@StaticMetamodel(Espacios.class)
public class Espacios_ { 

    public static volatile SingularAttribute<Espacios, String> referenciaIdentificacion;
    public static volatile SingularAttribute<Espacios, String> descripcion;
    public static volatile SingularAttribute<Espacios, Estacionamientos> estacionamientosIdestacionamientos;
    public static volatile SingularAttribute<Espacios, Integer> idespacios;
    public static volatile SingularAttribute<Espacios, String> disponible;

}