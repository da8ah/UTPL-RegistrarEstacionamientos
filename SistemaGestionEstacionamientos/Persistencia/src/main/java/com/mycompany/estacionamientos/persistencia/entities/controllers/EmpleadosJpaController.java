/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.persistencia.entities.controllers;

import com.mycompany.estacionamientos.persistencia.entities.Empleados;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.estacionamientos.persistencia.entities.Estacionamientos;
import com.mycompany.estacionamientos.persistencia.entities.controllers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tiber
 */
public class EmpleadosJpaController implements Serializable {

    public EmpleadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleados empleados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estacionamientos estacionamientosIdestacionamientos = empleados.getEstacionamientosIdestacionamientos();
            if (estacionamientosIdestacionamientos != null) {
                estacionamientosIdestacionamientos = em.getReference(estacionamientosIdestacionamientos.getClass(), estacionamientosIdestacionamientos.getIdestacionamientos());
                empleados.setEstacionamientosIdestacionamientos(estacionamientosIdestacionamientos);
            }
            em.persist(empleados);
            if (estacionamientosIdestacionamientos != null) {
                estacionamientosIdestacionamientos.getEmpleadosList().add(empleados);
                estacionamientosIdestacionamientos = em.merge(estacionamientosIdestacionamientos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleados empleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados persistentEmpleados = em.find(Empleados.class, empleados.getIdempleados());
            Estacionamientos estacionamientosIdestacionamientosOld = persistentEmpleados.getEstacionamientosIdestacionamientos();
            Estacionamientos estacionamientosIdestacionamientosNew = empleados.getEstacionamientosIdestacionamientos();
            if (estacionamientosIdestacionamientosNew != null) {
                estacionamientosIdestacionamientosNew = em.getReference(estacionamientosIdestacionamientosNew.getClass(), estacionamientosIdestacionamientosNew.getIdestacionamientos());
                empleados.setEstacionamientosIdestacionamientos(estacionamientosIdestacionamientosNew);
            }
            empleados = em.merge(empleados);
            if (estacionamientosIdestacionamientosOld != null && !estacionamientosIdestacionamientosOld.equals(estacionamientosIdestacionamientosNew)) {
                estacionamientosIdestacionamientosOld.getEmpleadosList().remove(empleados);
                estacionamientosIdestacionamientosOld = em.merge(estacionamientosIdestacionamientosOld);
            }
            if (estacionamientosIdestacionamientosNew != null && !estacionamientosIdestacionamientosNew.equals(estacionamientosIdestacionamientosOld)) {
                estacionamientosIdestacionamientosNew.getEmpleadosList().add(empleados);
                estacionamientosIdestacionamientosNew = em.merge(estacionamientosIdestacionamientosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleados.getIdempleados();
                if (findEmpleados(id) == null) {
                    throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados empleados;
            try {
                empleados = em.getReference(Empleados.class, id);
                empleados.getIdempleados();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
            }
            Estacionamientos estacionamientosIdestacionamientos = empleados.getEstacionamientosIdestacionamientos();
            if (estacionamientosIdestacionamientos != null) {
                estacionamientosIdestacionamientos.getEmpleadosList().remove(empleados);
                estacionamientosIdestacionamientos = em.merge(estacionamientosIdestacionamientos);
            }
            em.remove(empleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleados> findEmpleadosEntities() {
        return findEmpleadosEntities(true, -1, -1);
    }

    public List<Empleados> findEmpleadosEntities(int maxResults, int firstResult) {
        return findEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<Empleados> findEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleados.class));
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

    public Empleados findEmpleados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleados> rt = cq.from(Empleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
