package com.sauap.persistencia;

import com.sauap.entidad.UnidadAprendizaje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UnidadDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("default");

    public void guardar(UnidadAprendizaje unidad) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(unidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<UnidadAprendizaje> obtenerTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT u FROM UnidadAprendizaje u ORDER BY u.nombre";
            return em.createQuery(jpql, UnidadAprendizaje.class).getResultList();
        } finally {
            em.close();
        }
    }

    public UnidadAprendizaje buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(UnidadAprendizaje.class, id);
        } finally {
            em.close();
        }
    }
}