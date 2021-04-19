package com.mycompany.estacionamientos.persistencia.entities;

import com.mycompany.estacionamientos.persistencia.entities.Estacionamientos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T19:53:54")
@StaticMetamodel(Propietarios.class)
public class Propietarios_ { 

    public static volatile SingularAttribute<Propietarios, String> password;
    public static volatile SingularAttribute<Propietarios, String> direccion;
    public static volatile SingularAttribute<Propietarios, Integer> idpropietarios;
    public static volatile SingularAttribute<Propietarios, String> login;
    public static volatile SingularAttribute<Propietarios, String> email;
    public static volatile SingularAttribute<Propietarios, String> nombres;
    public static volatile ListAttribute<Propietarios, Estacionamientos> estacionamientosList;

}