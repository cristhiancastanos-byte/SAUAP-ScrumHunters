package com.sauap.persistencia;

import com.sauap.entidad.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");


    public Usuario buscarPorUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {

            String jpql = "SELECT u FROM Usuario u WHERE u.username = :username";
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("username", username);


            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }
}