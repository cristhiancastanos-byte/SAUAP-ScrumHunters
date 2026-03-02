package com.sauap.persistencia;

import com.sauap.entidad.UnidadAprendizaje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class UnidadDAO {
    // Asegúrate que el nombre "default" coincida con tu persistence.xml
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

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
            return em.createQuery("SELECT u FROM UnidadAprendizaje u", UnidadAprendizaje.class).getResultList();
        } finally {
            em.close();
        }
    }
}