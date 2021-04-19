/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.estacionamientos.persistencia.entities.controllers;

import com.mycompany.estacionamientos.persistencia.entities.Espacios;
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
public class EspaciosJpaController implements Serializable {

    public EspaciosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Espacios espacios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estacionamientos estacionamientosIdestacionamientos = espacios.getEstacionamientosIdestacionamientos();
            if (estacionamientosIdestacionamientos != null) {
                estacionamientosIdestacionamientos = em.getReference(estacionamientosIdestacionamientos.getClass(), estacionamientosIdestacionamientos.getIdestacionamientos());
                espacios.setEstacionamientosIdestacionamientos(estacionamientosIdestacionamientos);
            }
            em.persist(espacios);
            if (estacionamientosIdestacionamientos != null) {
                estacionamientosIdestacionamientos.getEspaciosList().add(espacios);
                estacionamientosIdestacionamientos = em.merge(estacionamientosIdestacionamientos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Espacios espacios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Espacios persistentEspacios = em.find(Espacios.class, espacios.getIdespacios());
            Estacionamientos estacionamientosIdestacionamientosOld = persistentEspacios.getEstacionamientosIdestacionamientos();
            Estacionamientos estacionamientosIdestacionamientosNew = espacios.getEstacionamientosIdestacionamientos();
            if (estacionamientosIdestacionamientosNew != null) {
                estacionamientosIdestacionamientosNew = em.getReference(estacionamientosIdestacionamientosNew.getClass(), estacionamientosIdestacionamientosNew.getIdestacionamientos());
                espacios.setEstacionamientosIdestacionamientos(estacionamientosIdestacionamientosNew);
            }
            espacios = em.merge(espacios);
            if (estacionamientosIdestacionamientosOld != null && !estacionamientosIdestacionamientosOld.equals(estacionamientosIdestacionamientosNew)) {
                estacionamientosIdestacionamientosOld.getEspaciosList().remove(espacios);
                estacionamientosIdestacionamientosOld = em.merge(estacionamientosIdestacionamientosOld);
            }
            if (estacionamientosIdestacionamientosNew != null && !estacionamientosIdestacionamientosNew.equals(estacionamientosIdestacionamientosOld)) {
                estacionamientosIdestacionamientosNew.getEspaciosList().add(espacios);
                estacionamientosIdestacionamientosNew = em.merge(estacionamientosIdestacionamientosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = espacios.getIdespacios();
                if (findEspacios(id) == null) {
                    throw new NonexistentEntityException("The espacios with id " + id + " no longer exists.");
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
            Espacios espacios;
            try {
                espacios = em.getReference(Espacios.class, id);
                espacios.getIdespacios();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The espacios with id " + id + " no longer exists.", enfe);
            }
            Estacionamientos estacionamientosIdestacionamientos = espacios.getEstacionamientosIdestacionamientos();
            if (estacionamientosIdestacionamientos != null) {
                estacionamientosIdestacionamientos.getEspaciosList().remove(espacios);
                estacionamientosIdestacionamientos = em.merge(estacionamientosIdestacionamientos);
            }
            em.remove(espacios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Espacios> findEspaciosEntities() {
        return findEspaciosEntities(true, -1, -1);
    }

    public List<Espacios> findEspaciosEntities(int maxResults, int firstResult) {
        return findEspaciosEntities(false, maxResults, firstResult);
    }

    private List<Espacios> findEspaciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Espacios.class));
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

    public Espacios findEspacios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Espacios.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspaciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Espacios> rt = cq.from(Espacios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
