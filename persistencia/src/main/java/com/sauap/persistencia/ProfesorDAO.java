package com.sauap.persistencia;

import com.sauap.entidad.Profesor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProfesorDAO {
    // Asumiendo una persistencia estándar de JPA
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void guardar(Profesor profesor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(profesor); // Esto ejecuta el INSERT en la base de datos
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public boolean existeRFC(String rfc) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(p) FROM Profesor p WHERE p.rfc = :rfc", Long.class)
                    .setParameter("rfc", rfc)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }
}
