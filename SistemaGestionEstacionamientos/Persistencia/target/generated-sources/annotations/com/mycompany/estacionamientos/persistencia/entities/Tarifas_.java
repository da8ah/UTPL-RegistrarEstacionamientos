package com.mycompany.estacionamientos.persistencia.entities;

import com.mycompany.estacionamientos.persistencia.entities.Estacionamientos;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T19:53:54")
@StaticMetamodel(Tarifas.class)
public class Tarifas_ { 

    public static volatile SingularAttribute<Tarifas, String> descripcion;
    public static volatile SingularAttribute<Tarifas, Estacionamientos> estacionamientosIdestacionamientos;
    public static volatile SingularAttribute<Tarifas, String> cantidadTiempo;
    public static volatile SingularAttribute<Tarifas, BigDecimal> costo;
    public static volatile SingularAttribute<Tarifas, Integer> idtarifas;

}