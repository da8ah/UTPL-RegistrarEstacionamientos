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
import com.mycompany.estacionamientos.persistencia.entities.Propietarios;
import com.mycompany.estacionamientos.persistencia.entities.controllers.exceptions.IllegalOrphanException;
import com.mycompany.estacionamientos.persistencia.entities.controllers.exceptions.NonexistentEntityException;
import com.mycompany.estacionamientos.persistencia.entities.controllers.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Tiber
 */
public class PropietariosJpaController implements Serializable {

    public PropietariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Propietarios propietarios) throws PreexistingEntityException, Exception {
        if (propietarios.getEstacionamientosList() == null) {
            propietarios.setEstacionamientosList(new ArrayList<Estacionamientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estacionamientos> attachedEstacionamientosList = new ArrayList<Estacionamientos>();
            for (Estacionamientos estacionamientosListEstacionamientosToAttach : propietarios.getEstacionamientosList()) {
                estacionamientosListEstacionamientosToAttach = em.getReference(estacionamientosListEstacionamientosToAttach.getClass(), estacionamientosListEstacionamientosToAttach.getIdestacionamientos());
                attachedEstacionamientosList.add(estacionamientosListEstacionamientosToAttach);
            }
            propietarios.setEstacionamientosList(attachedEstacionamientosList);
            em.persist(propietarios);
            for (Estacionamientos estacionamientosListEstacionamientos : propietarios.getEstacionamientosList()) {
                Propietarios oldPropietariosIdpropietariosOfEstacionamientosListEstacionamientos = estacionamientosListEstacionamientos.getPropietariosIdpropietarios();
                estacionamientosListEstacionamientos.setPropietariosIdpropietarios(propietarios);
                estacionamientosListEstacionamientos = em.merge(estacionamientosListEstacionamientos);
                if (oldPropietariosIdpropietariosOfEstacionamientosListEstacionamientos != null) {
                    oldPropietariosIdpropietariosOfEstacionamientosListEstacionamientos.getEstacionamientosList().remove(estacionamientosListEstacionamientos);
                    oldPropietariosIdpropietariosOfEstacionamientosListEstacionamientos = em.merge(oldPropietariosIdpropietariosOfEstacionamientosListEstacionamientos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPropietarios(propietarios.getIdpropietarios()) != null) {
                throw new PreexistingEntityException("Propietarios " + propietarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Propietarios propietarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propietarios persistentPropietarios = em.find(Propietarios.class, propietarios.getIdpropietarios());
            List<Estacionamientos> estacionamientosListOld = persistentPropietarios.getEstacionamientosList();
            List<Estacionamientos> estacionamientosListNew = propietarios.getEstacionamientosList();
            List<String> illegalOrphanMessages = null;
            for (Estacionamientos estacionamientosListOldEstacionamientos : estacionamientosListOld) {
                if (!estacionamientosListNew.contains(estacionamientosListOldEstacionamientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estacionamientos " + estacionamientosListOldEstacionamientos + " since its propietariosIdpropietarios field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estacionamientos> attachedEstacionamientosListNew = new ArrayList<Estacionamientos>();
            for (Estacionamientos estacionamientosListNewEstacionamientosToAttach : estacionamientosListNew) {
                estacionamientosListNewEstacionamientosToAttach = em.getReference(estacionamientosListNewEstacionamientosToAttach.getClass(), estacionamientosListNewEstacionamientosToAttach.getIdestacionamientos());
                attachedEstacionamientosListNew.add(estacionamientosListNewEstacionamientosToAttach);
            }
            estacionamientosListNew = attachedEstacionamientosListNew;
            propietarios.setEstacionamientosList(estacionamientosListNew);
            propietarios = em.merge(propietarios);
            for (Estacionamientos estacionamientosListNewEstacionamientos : estacionamientosListNew) {
                if (!estacionamientosListOld.contains(estacionamientosListNewEstacionamientos)) {
                    Propietarios oldPropietariosIdpropietariosOfEstacionamientosListNewEstacionamientos = estacionamientosListNewEstacionamientos.getPropietariosIdpropietarios();
                    estacionamientosListNewEstacionamientos.setPropietariosIdpropietarios(propietarios);
                    estacionamientosListNewEstacionamientos = em.merge(estacionamientosListNewEstacionamientos);
                    if (oldPropietariosIdpropietariosOfEstacionamientosListNewEstacionamientos != null && !oldPropietariosIdpropietariosOfEstacionamientosListNewEstacionamientos.equals(propietarios)) {
                        oldPropietariosIdpropietariosOfEstacionamientosListNewEstacionamientos.getEstacionamientosList().remove(estacionamientosListNewEstacionamientos);
                        oldPropietariosIdpropietariosOfEstacionamientosListNewEstacionamientos = em.merge(oldPropietariosIdpropietariosOfEstacionamientosListNewEstacionamientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = propietarios.getIdpropietarios();
                if (findPropietarios(id) == null) {
                    throw new NonexistentEntityException("The propietarios with id " + id + " no longer exists.");
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
            Propietarios propietarios;
            try {
                propietarios = em.getReference(Propietarios.class, id);
                propietarios.getIdpropietarios();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The propietarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estacionamientos> estacionamientosListOrphanCheck = propietarios.getEstacionamientosList();
            for (Estacionamientos estacionamientosListOrphanCheckEstacionamientos : estacionamientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Propietarios (" + propietarios + ") cannot be destroyed since the Estacionamientos " + estacionamientosListOrphanCheckEstacionamientos + " in its estacionamientosList field has a non-nullable propietariosIdpropietarios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(propietarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Propietarios> findPropietariosEntities() {
        return findPropietariosEntities(true, -1, -1);
    }

    public List<Propietarios> findPropietariosEntities(int maxResults, int firstResult) {
        return findPropietariosEntities(false, maxResults, firstResult);
    }

    private List<Propietarios> findPropietariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Propietarios.class));
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

    public Propietarios findPropietarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Propietarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getPropietariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Propietarios> rt = cq.from(Propietarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
