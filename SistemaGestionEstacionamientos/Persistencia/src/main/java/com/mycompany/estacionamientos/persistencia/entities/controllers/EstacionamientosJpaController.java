/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.persistencia.entities.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.estacionamientos.persistencia.entities.Propietarios;
import com.mycompany.estacionamientos.persistencia.entities.Tarifas;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.estacionamientos.persistencia.entities.Empleados;
import com.mycompany.estacionamientos.persistencia.entities.Espacios;
import com.mycompany.estacionamientos.persistencia.entities.Estacionamientos;
import com.mycompany.estacionamientos.persistencia.entities.controllers.exceptions.IllegalOrphanException;
import com.mycompany.estacionamientos.persistencia.entities.controllers.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tiber
 */
public class EstacionamientosJpaController implements Serializable {

    public EstacionamientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estacionamientos estacionamientos) {
        if (estacionamientos.getTarifasList() == null) {
            estacionamientos.setTarifasList(new ArrayList<Tarifas>());
        }
        if (estacionamientos.getEmpleadosList() == null) {
            estacionamientos.setEmpleadosList(new ArrayList<Empleados>());
        }
        if (estacionamientos.getEspaciosList() == null) {
            estacionamientos.setEspaciosList(new ArrayList<Espacios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propietarios propietariosIdpropietarios = estacionamientos.getPropietariosIdpropietarios();
            if (propietariosIdpropietarios != null) {
                propietariosIdpropietarios = em.getReference(propietariosIdpropietarios.getClass(), propietariosIdpropietarios.getIdpropietarios());
                estacionamientos.setPropietariosIdpropietarios(propietariosIdpropietarios);
            }
            List<Tarifas> attachedTarifasList = new ArrayList<Tarifas>();
            for (Tarifas tarifasListTarifasToAttach : estacionamientos.getTarifasList()) {
                tarifasListTarifasToAttach = em.getReference(tarifasListTarifasToAttach.getClass(), tarifasListTarifasToAttach.getIdtarifas());
                attachedTarifasList.add(tarifasListTarifasToAttach);
            }
            estacionamientos.setTarifasList(attachedTarifasList);
            List<Empleados> attachedEmpleadosList = new ArrayList<Empleados>();
            for (Empleados empleadosListEmpleadosToAttach : estacionamientos.getEmpleadosList()) {
                empleadosListEmpleadosToAttach = em.getReference(empleadosListEmpleadosToAttach.getClass(), empleadosListEmpleadosToAttach.getIdempleados());
                attachedEmpleadosList.add(empleadosListEmpleadosToAttach);
            }
            estacionamientos.setEmpleadosList(attachedEmpleadosList);
            List<Espacios> attachedEspaciosList = new ArrayList<Espacios>();
            for (Espacios espaciosListEspaciosToAttach : estacionamientos.getEspaciosList()) {
                espaciosListEspaciosToAttach = em.getReference(espaciosListEspaciosToAttach.getClass(), espaciosListEspaciosToAttach.getIdespacios());
                attachedEspaciosList.add(espaciosListEspaciosToAttach);
            }
            estacionamientos.setEspaciosList(attachedEspaciosList);
            em.persist(estacionamientos);
            if (propietariosIdpropietarios != null) {
                propietariosIdpropietarios.getEstacionamientosList().add(estacionamientos);
                propietariosIdpropietarios = em.merge(propietariosIdpropietarios);
            }
            for (Tarifas tarifasListTarifas : estacionamientos.getTarifasList()) {
                Estacionamientos oldEstacionamientosIdestacionamientosOfTarifasListTarifas = tarifasListTarifas.getEstacionamientosIdestacionamientos();
                tarifasListTarifas.setEstacionamientosIdestacionamientos(estacionamientos);
                tarifasListTarifas = em.merge(tarifasListTarifas);
                if (oldEstacionamientosIdestacionamientosOfTarifasListTarifas != null) {
                    oldEstacionamientosIdestacionamientosOfTarifasListTarifas.getTarifasList().remove(tarifasListTarifas);
                    oldEstacionamientosIdestacionamientosOfTarifasListTarifas = em.merge(oldEstacionamientosIdestacionamientosOfTarifasListTarifas);
                }
            }
            for (Empleados empleadosListEmpleados : estacionamientos.getEmpleadosList()) {
                Estacionamientos oldEstacionamientosIdestacionamientosOfEmpleadosListEmpleados = empleadosListEmpleados.getEstacionamientosIdestacionamientos();
                empleadosListEmpleados.setEstacionamientosIdestacionamientos(estacionamientos);
                empleadosListEmpleados = em.merge(empleadosListEmpleados);
                if (oldEstacionamientosIdestacionamientosOfEmpleadosListEmpleados != null) {
                    oldEstacionamientosIdestacionamientosOfEmpleadosListEmpleados.getEmpleadosList().remove(empleadosListEmpleados);
                    oldEstacionamientosIdestacionamientosOfEmpleadosListEmpleados = em.merge(oldEstacionamientosIdestacionamientosOfEmpleadosListEmpleados);
                }
            }
            for (Espacios espaciosListEspacios : estacionamientos.getEspaciosList()) {
                Estacionamientos oldEstacionamientosIdestacionamientosOfEspaciosListEspacios = espaciosListEspacios.getEstacionamientosIdestacionamientos();
                espaciosListEspacios.setEstacionamientosIdestacionamientos(estacionamientos);
                espaciosListEspacios = em.merge(espaciosListEspacios);
                if (oldEstacionamientosIdestacionamientosOfEspaciosListEspacios != null) {
                    oldEstacionamientosIdestacionamientosOfEspaciosListEspacios.getEspaciosList().remove(espaciosListEspacios);
                    oldEstacionamientosIdestacionamientosOfEspaciosListEspacios = em.merge(oldEstacionamientosIdestacionamientosOfEspaciosListEspacios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estacionamientos estacionamientos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estacionamientos persistentEstacionamientos = em.find(Estacionamientos.class, estacionamientos.getIdestacionamientos());
            Propietarios propietariosIdpropietariosOld = persistentEstacionamientos.getPropietariosIdpropietarios();
            Propietarios propietariosIdpropietariosNew = estacionamientos.getPropietariosIdpropietarios();
            List<Tarifas> tarifasListOld = persistentEstacionamientos.getTarifasList();
            List<Tarifas> tarifasListNew = estacionamientos.getTarifasList();
            List<Empleados> empleadosListOld = persistentEstacionamientos.getEmpleadosList();
            List<Empleados> empleadosListNew = estacionamientos.getEmpleadosList();
            List<Espacios> espaciosListOld = persistentEstacionamientos.getEspaciosList();
            List<Espacios> espaciosListNew = estacionamientos.getEspaciosList();
            List<String> illegalOrphanMessages = null;
            for (Tarifas tarifasListOldTarifas : tarifasListOld) {
                if (!tarifasListNew.contains(tarifasListOldTarifas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tarifas " + tarifasListOldTarifas + " since its estacionamientosIdestacionamientos field is not nullable.");
                }
            }
            for (Empleados empleadosListOldEmpleados : empleadosListOld) {
                if (!empleadosListNew.contains(empleadosListOldEmpleados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleados " + empleadosListOldEmpleados + " since its estacionamientosIdestacionamientos field is not nullable.");
                }
            }
            for (Espacios espaciosListOldEspacios : espaciosListOld) {
                if (!espaciosListNew.contains(espaciosListOldEspacios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Espacios " + espaciosListOldEspacios + " since its estacionamientosIdestacionamientos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (propietariosIdpropietariosNew != null) {
                propietariosIdpropietariosNew = em.getReference(propietariosIdpropietariosNew.getClass(), propietariosIdpropietariosNew.getIdpropietarios());
                estacionamientos.setPropietariosIdpropietarios(propietariosIdpropietariosNew);
            }
            List<Tarifas> attachedTarifasListNew = new ArrayList<Tarifas>();
            for (Tarifas tarifasListNewTarifasToAttach : tarifasListNew) {
                tarifasListNewTarifasToAttach = em.getReference(tarifasListNewTarifasToAttach.getClass(), tarifasListNewTarifasToAttach.getIdtarifas());
                attachedTarifasListNew.add(tarifasListNewTarifasToAttach);
            }
            tarifasListNew = attachedTarifasListNew;
            estacionamientos.setTarifasList(tarifasListNew);
            List<Empleados> attachedEmpleadosListNew = new ArrayList<Empleados>();
            for (Empleados empleadosListNewEmpleadosToAttach : empleadosListNew) {
                empleadosListNewEmpleadosToAttach = em.getReference(empleadosListNewEmpleadosToAttach.getClass(), empleadosListNewEmpleadosToAttach.getIdempleados());
                attachedEmpleadosListNew.add(empleadosListNewEmpleadosToAttach);
            }
            empleadosListNew = attachedEmpleadosListNew;
            estacionamientos.setEmpleadosList(empleadosListNew);
            List<Espacios> attachedEspaciosListNew = new ArrayList<Espacios>();
            for (Espacios espaciosListNewEspaciosToAttach : espaciosListNew) {
                espaciosListNewEspaciosToAttach = em.getReference(espaciosListNewEspaciosToAttach.getClass(), espaciosListNewEspaciosToAttach.getIdespacios());
                attachedEspaciosListNew.add(espaciosListNewEspaciosToAttach);
            }
            espaciosListNew = attachedEspaciosListNew;
            estacionamientos.setEspaciosList(espaciosListNew);
            estacionamientos = em.merge(estacionamientos);
            if (propietariosIdpropietariosOld != null && !propietariosIdpropietariosOld.equals(propietariosIdpropietariosNew)) {
                propietariosIdpropietariosOld.getEstacionamientosList().remove(estacionamientos);
                propietariosIdpropietariosOld = em.merge(propietariosIdpropietariosOld);
            }
            if (propietariosIdpropietariosNew != null && !propietariosIdpropietariosNew.equals(propietariosIdpropietariosOld)) {
                propietariosIdpropietariosNew.getEstacionamientosList().add(estacionamientos);
                propietariosIdpropietariosNew = em.merge(propietariosIdpropietariosNew);
            }
            for (Tarifas tarifasListNewTarifas : tarifasListNew) {
                if (!tarifasListOld.contains(tarifasListNewTarifas)) {
                    Estacionamientos oldEstacionamientosIdestacionamientosOfTarifasListNewTarifas = tarifasListNewTarifas.getEstacionamientosIdestacionamientos();
                    tarifasListNewTarifas.setEstacionamientosIdestacionamientos(estacionamientos);
                    tarifasListNewTarifas = em.merge(tarifasListNewTarifas);
                    if (oldEstacionamientosIdestacionamientosOfTarifasListNewTarifas != null && !oldEstacionamientosIdestacionamientosOfTarifasListNewTarifas.equals(estacionamientos)) {
                        oldEstacionamientosIdestacionamientosOfTarifasListNewTarifas.getTarifasList().remove(tarifasListNewTarifas);
                        oldEstacionamientosIdestacionamientosOfTarifasListNewTarifas = em.merge(oldEstacionamientosIdestacionamientosOfTarifasListNewTarifas);
                    }
                }
            }
            for (Empleados empleadosListNewEmpleados : empleadosListNew) {
                if (!empleadosListOld.contains(empleadosListNewEmpleados)) {
                    Estacionamientos oldEstacionamientosIdestacionamientosOfEmpleadosListNewEmpleados = empleadosListNewEmpleados.getEstacionamientosIdestacionamientos();
                    empleadosListNewEmpleados.setEstacionamientosIdestacionamientos(estacionamientos);
                    empleadosListNewEmpleados = em.merge(empleadosListNewEmpleados);
                    if (oldEstacionamientosIdestacionamientosOfEmpleadosListNewEmpleados != null && !oldEstacionamientosIdestacionamientosOfEmpleadosListNewEmpleados.equals(estacionamientos)) {
                        oldEstacionamientosIdestacionamientosOfEmpleadosListNewEmpleados.getEmpleadosList().remove(empleadosListNewEmpleados);
                        oldEstacionamientosIdestacionamientosOfEmpleadosListNewEmpleados = em.merge(oldEstacionamientosIdestacionamientosOfEmpleadosListNewEmpleados);
                    }
                }
            }
            for (Espacios espaciosListNewEspacios : espaciosListNew) {
                if (!espaciosListOld.contains(espaciosListNewEspacios)) {
                    Estacionamientos oldEstacionamientosIdestacionamientosOfEspaciosListNewEspacios = espaciosListNewEspacios.getEstacionamientosIdestacionamientos();
                    espaciosListNewEspacios.setEstacionamientosIdestacionamientos(estacionamientos);
                    espaciosListNewEspacios = em.merge(espaciosListNewEspacios);
                    if (oldEstacionamientosIdestacionamientosOfEspaciosListNewEspacios != null && !oldEstacionamientosIdestacionamientosOfEspaciosListNewEspacios.equals(estacionamientos)) {
                        oldEstacionamientosIdestacionamientosOfEspaciosListNewEspacios.getEspaciosList().remove(espaciosListNewEspacios);
                        oldEstacionamientosIdestacionamientosOfEspaciosListNewEspacios = em.merge(oldEstacionamientosIdestacionamientosOfEspaciosListNewEspacios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estacionamientos.getIdestacionamientos();
                if (findEstacionamientos(id) == null) {
                    throw new NonexistentEntityException("The estacionamientos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estacionamientos estacionamientos;
            try {
                estacionamientos = em.getReference(Estacionamientos.class, id);
                estacionamientos.getIdestacionamientos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estacionamientos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tarifas> tarifasListOrphanCheck = estacionamientos.getTarifasList();
            for (Tarifas tarifasListOrphanCheckTarifas : tarifasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estacionamientos (" + estacionamientos + ") cannot be destroyed since the Tarifas " + tarifasListOrphanCheckTarifas + " in its tarifasList field has a non-nullable estacionamientosIdestacionamientos field.");
            }
            List<Empleados> empleadosListOrphanCheck = estacionamientos.getEmpleadosList();
            for (Empleados empleadosListOrphanCheckEmpleados : empleadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estacionamientos (" + estacionamientos + ") cannot be destroyed since the Empleados " + empleadosListOrphanCheckEmpleados + " in its empleadosList field has a non-nullable estacionamientosIdestacionamientos field.");
            }
            List<Espacios> espaciosListOrphanCheck = estacionamientos.getEspaciosList();
            for (Espacios espaciosListOrphanCheckEspacios : espaciosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estacionamientos (" + estacionamientos + ") cannot be destroyed since the Espacios " + espaciosListOrphanCheckEspacios + " in its espaciosList field has a non-nullable estacionamientosIdestacionamientos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Propietarios propietariosIdpropietarios = estacionamientos.getPropietariosIdpropietarios();
            if (propietariosIdpropietarios != null) {
                propietariosIdpropietarios.getEstacionamientosList().remove(estacionamientos);
                propietariosIdpropietarios = em.merge(propietariosIdpropietarios);
            }
            em.remove(estacionamientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estacionamientos> findEstacionamientosEntities() {
        return findEstacionamientosEntities(true, -1, -1);
    }

    public List<Estacionamientos> findEstacionamientosEntities(int maxResults, int firstResult) {
        return findEstacionamientosEntities(false, maxResults, firstResult);
    }

    private List<Estacionamientos> findEstacionamientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estacionamientos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Estacionamientos findEstacionamientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estacionamientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstacionamientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estacionamientos> rt = cq.from(Estacionamientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
