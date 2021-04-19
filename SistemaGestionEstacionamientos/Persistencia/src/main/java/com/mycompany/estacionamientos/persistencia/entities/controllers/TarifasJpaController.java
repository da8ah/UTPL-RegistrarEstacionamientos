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
import com.mycompany.estacionamientos.persistencia.entities.Estacionamientos;
import com.mycompany.estacionamientos.persistencia.entities.Tarifas;
import com.mycompany.estacionamientos.persistencia.entities.controllers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tiber
 */
public class TarifasJpaController implements Serializable {

    public TarifasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarifas tarifas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estacionamientos estacionamientosIdestacionamientos = tarifas.getEstacionamientosIdestacionamientos();
            if (estacionamientosIdestacionamientos != null) {
                estacionamientosIdestacionamientos = em.getReference(estacionamientosIdestacionamientos.getClass(), estacionamientosIdestacionamientos.getIdestacionamientos());
                tarifas.setEstacionamientosIdestacionamientos(estacionamientosIdestacionamientos);
            }
            em.persist(tarifas);
            if (estacionamientosIdestacionamientos != null) {
                estacionamientosIdestacionamientos.getTarifasList().add(tarifas);
                estacionamientosIdestacionamientos = em.merge(estacionamientosIdestacionamientos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarifas tarifas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarifas persistentTarifas = em.find(Tarifas.class, tarifas.getIdtarifas());
            Estacionamientos estacionamientosIdestacionamientosOld = persistentTarifas.getEstacionamientosIdestacionamientos();
            Estacionamientos estacionamientosIdestacionamientosNew = tarifas.getEstacionamientosIdestacionamientos();
            if (estacionamientosIdestacionamientosNew != null) {
                estacionamientosIdestacionamientosNew = em.getReference(estacionamientosIdestacionamientosNew.getClass(), estacionamientosIdestacionamientosNew.getIdestacionamientos());
                tarifas.setEstacionamientosIdestacionamientos(estacionamientosIdestacionamientosNew);
            }
            tarifas = em.merge(tarifas);
            if (estacionamientosIdestacionamientosOld != null && !estacionamientosIdestacionamientosOld.equals(estacionamientosIdestacionamientosNew)) {
                estacionamientosIdestacionamientosOld.getTarifasList().remove(tarifas);
                estacionamientosIdestacionamientosOld = em.merge(estacionamientosIdestacionamientosOld);
            }
            if (estacionamientosIdestacionamientosNew != null && !estacionamientosIdestacionamientosNew.equals(estacionamientosIdestacionamientosOld)) {
                estacionamientosIdestacionamientosNew.getTarifasList().add(tarifas);
                estacionamientosIdestacionamientosNew = em.merge(estacionamientosIdestacionamientosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tarifas.getIdtarifas();
                if (findTarifas(id) == null) {
                    throw new NonexistentEntityException("The tarifas with id " + id + " no longer exists.");
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
            Tarifas tarifas;
            try {
                tarifas = em.getReference(Tarifas.class, id);
                tarifas.getIdtarifas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarifas with id " + id + " no longer exists.", enfe);
            }
            Estacionamientos estacionamientosIdestacionamientos = tarifas.getEstacionamientosIdestacionamientos();
            if (estacionamientosIdestacionamientos != null) {
                estacionamientosIdestacionamientos.getTarifasList().remove(tarifas);
                estacionamientosIdestacionamientos = em.merge(estacionamientosIdestacionamientos);
            }
            em.remove(tarifas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarifas> findTarifasEntities() {
        return findTarifasEntities(true, -1, -1);
    }

    public List<Tarifas> findTarifasEntities(int maxResults, int firstResult) {
        return findTarifasEntities(false, maxResults, firstResult);
    }

    private List<Tarifas> findTarifasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarifas.class));
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

    public Tarifas findTarifas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarifas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarifasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarifas> rt = cq.from(Tarifas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
