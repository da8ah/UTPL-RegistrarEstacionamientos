/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aplicacion;

import com.mycompany.estacionamientos.nucleo.entities.Espacio;
import com.mycompany.estacionamientos.nucleo.entities.Estacionamiento;
import com.mycompany.estacionamientos.nucleo.entities.Tarifa;
import com.mycompany.estacionamientos.nucleo.entities.usuarios.Persona;
import com.mycompany.estacionamientos.nucleo.entities.usuarios.Propietario;
import com.mycompany.estacionamientos.nucleo.usecases.RegistrarCuenta;
import com.mycompany.estacionamientos.persistencia.adapters.PersistenciaDeCuentaBasica;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;
import spark.template.freemarker.FreeMarkerEngine;

/**
 *
 * @author Danilo Alejandro Ochoa Hidalgo
 */
public class AppWebBackEnd {

    public static String usuario = "";
    public static String estacionamiento = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /* RUTA PARA ENCONTRAR RECURSOS ***************************************/
        staticFileLocation("/public");

        /* APP ****************************************************************/
        String signupLinkElement = "<a href='signup' id='signup' class='property-sub'>Sign Up</a>";

        // Inicio        
        get("/", (req, res) -> {

            String vista = "index.ftl";
            Map<String, Object> modelo = new HashMap<>();

            modelo.put("signup", signupLinkElement);
            modelo.put("estacionamiento", "Estacionamiento");

            if (!usuario.equals("")) {
                modelo.put("signup", "<a href='#' id='signup' class='property-sub'>" + usuario + "</a>");
            }
            if (!estacionamiento.equals("")) {
                modelo.put("estacionamiento", estacionamiento);
            }

            return new ModelAndView(modelo, vista);
        }, new FreeMarkerEngine());

        get("index.html", (req, res) -> {

            String vista = "index.ftl";
            Map<String, Object> modelo = new HashMap<>();

            res.redirect("/");

            return new ModelAndView(modelo, vista);
        }, new FreeMarkerEngine());

        // Registro
        get("signup", (req, res) -> {

            String vista = "signup.ftl";

            Map<String, Object> modelo = new HashMap<>();

            return new ModelAndView(modelo, vista);
        }, new FreeMarkerEngine());

        post("signup/registrar-cuenta", (req, res) -> {

            String vista = "signup.ftl";
            Map<String, Object> modelo = new HashMap<>();

            try {
                if (!req.body().equals("")) {

                    // Obtener datos desde el cliente
                    String requestBody = req.body();
                    String[] requestBodyParser = requestBody.split("&");

                    // Mapear parametros
                    Map<String, String> params = new HashMap<>();
                    for (String param : requestBodyParser) {
                        params.put(param.split("=")[0], param.split("=")[1]);
                    }
                    String propietario_nombres = params.get("propietario_nombres");
                    String propietario_apellidos = params.get("propietario_apellidos");
                    String propietario_cedula = params.get("propietario_cedula");
                    String propietario_email = params.get("propietario_email");
                    String propietario_direccion = params.get("propietario_direccion");
                    String propietario_usuario = params.get("propietario_usuario");
                    String propietario_contrasena1 = params.get("propietario_contrasena1");
                    String propietario_contrasena2 = params.get("propietario_contrasena2");
                    String estacionamiento_nombre = params.get("estacionamiento_nombre");
                    String estacionamiento_direccion = params.get("estacionamiento_direccion");
                    String estacionamiento_telefono = params.get("estacionamiento_telefono");
                    String estacionamiento_espacios = params.get("estacionamiento_espacios");
                    String estacionamiento_tiempo = params.get("estacionamiento_tiempo");
                    String estacionamiento_tarifa = params.get("estacionamiento_tarifa");

                    // Comprobar datos
                    if (propietario_contrasena1.equals(propietario_contrasena2)) {

                        // Crear Espacios
                        int espacios = Integer.parseInt(estacionamiento_espacios);
                        ArrayList<Espacio> listaEspacios = new ArrayList<>();
                        for (int i = 1; i <= espacios; i++) {
                            listaEspacios.add(new Espacio(String.valueOf(i), estacionamiento_nombre, "DISPONIBLE"));
                        }

                        // MAPEO
                        // Propietario
                        Propietario objPropietario = new Propietario(new Date());
                        Persona objPersona = new Persona();
                        objPersona.setCedula(propietario_cedula);
                        objPersona.setNombre(propietario_nombres + " " + propietario_apellidos);
                        objPersona.setEmail(propietario_email);
                        objPersona.setDireccion(propietario_direccion);
                        objPropietario.setPersona(objPersona);
                        objPropietario.setLogin(propietario_usuario);
                        objPropietario.setPassword(propietario_contrasena1);

                        // Estacionamiento
                        Estacionamiento objEstacionamiento = new Estacionamiento(estacionamiento_nombre, estacionamiento_direccion, estacionamiento_telefono);

                        // Construir el objeto
                        RegistrarCuenta usecaseRegistrarCuenta = new RegistrarCuenta();

                        int tiempoMinutos = Integer.parseInt(estacionamiento_tiempo);
                        double costo = Double.parseDouble(estacionamiento_tarifa);
                        ArrayList<Tarifa> listaTarifas = new ArrayList<>();
                        listaTarifas.add(new Tarifa(1, estacionamiento_nombre, tiempoMinutos, costo));
                        objEstacionamiento = usecaseRegistrarCuenta.crearCuentaNueva(objPropietario, objEstacionamiento, listaEspacios, listaTarifas);

                        // Persistir en la BD
                        if (usecaseRegistrarCuenta.guardarCuentaCreada(objEstacionamiento, new PersistenciaDeCuentaBasica())) {
                            usuario = objPropietario.getLogin();
                            estacionamiento = objEstacionamiento.getNombre();

                            vista = "index.ftl";
                            res.redirect("/");
                        } else {
                            System.out.println("Error: ¡No se pudo guardar el registro!");
                            res.redirect("/signup");
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
                res.redirect("/signup");
            }
            return new ModelAndView(modelo, vista);
        }, new FreeMarkerEngine());
    }

}
