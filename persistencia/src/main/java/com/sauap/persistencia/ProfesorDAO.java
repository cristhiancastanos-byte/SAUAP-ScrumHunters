package com.sauap.persistencia;

import com.sauap.entidad.Profesor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProfesorDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("default");

    public void guardar(Profesor profesor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(profesor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean existeRFC(String rfc) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT COUNT(p) FROM Profesor p WHERE p.rfc = :rfc";
            long count = em.createQuery(jpql, Long.class)
                    .setParameter("rfc", rfc)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public List<Profesor> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p FROM Profesor p ORDER BY p.apellidoPaterno, p.apellidoMaterno, p.nombre";
            return em.createQuery(jpql, Profesor.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Profesor buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Profesor.class, id);
        } finally {
            em.close();
        }
    }
}