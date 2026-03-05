package com.sauap.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Time;

public class AsignacionDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("default");

    public long contarTraslapes(int idProfesor, String dia, Time inicio, Time fin) {
        EntityManager em = emf.createEntityManager();
        try {
            // Empalme si: inicioNuevo < finExistente AND finNuevo > inicioExistente
            String jpql =
                    "SELECT COUNT(a) " +
                            "FROM Asignacion a " +
                            "WHERE a.profesor.id = :idProfesor " +
                            "AND a.dia = :dia " +
                            "AND :inicio < a.horaFin " +
                            "AND :fin > a.horaInicio";

            return em.createQuery(jpql, Long.class)
                    .setParameter("idProfesor", idProfesor)
                    .setParameter("dia", dia)
                    .setParameter("inicio", inicio)
                    .setParameter("fin", fin)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public void guardar(com.sauap.entidad.Asignacion asignacion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(asignacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}