/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.dao;

import com.mycompany.nubulamusicwebaplication.models.Usuario;
import com.mycompany.nubulamusicwebaplication.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author PC
 */
public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public void guardar(Usuario usuario) {

        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {

            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new RuntimeException("Error al guardar usuario", e);

        } finally {
            em.close();
        }
    }

    @Override
    public Usuario buscarPorId(Long id) {

        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {

        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {

            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :correo",
                    Usuario.class
            );

            query.setParameter("correo", correo);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        } finally {
            em.close();
        }
    }

    @Override
    public Usuario buscarPorCorreoYContrasenia(String correo, String contrasenia) {

        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {

            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasenia = :contrasenia",
                    Usuario.class
            );

            query.setParameter("correo", correo);
            query.setParameter("contrasenia", contrasenia);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        } finally {
            em.close();
        }
    }

    @Override
    public Usuario buscarPorPseudonimo(String pseudonimo) {

        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {

            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.pseudonimo = :pseudonimo",
                    Usuario.class
            );

            query.setParameter("pseudonimo", pseudonimo);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        } finally {
            em.close();
        }
    }

    @Override
    public List<Usuario> listarTodos() {

        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {

            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u",
                    Usuario.class
            );

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Usuario usuario) {

        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {

            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new RuntimeException("Error al actualizar usuario", e);

        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {

        EntityManager em = JPAUtil.getInstance().getEntityManager();

        try {

            em.getTransaction().begin();

            Usuario usuario = em.find(Usuario.class, id);

            if (usuario != null) {
                em.remove(usuario);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new RuntimeException("Error al eliminar usuario", e);

        } finally {
            em.close();
        }
    }

    @Override
    public List<Usuario> listaTop(int limite) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u ORDER BY u.id DESC", Usuario.class);
            query.setMaxResults(10);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Usuario> listarPaginado(int pagina, int tamanioPagina) {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            int inicio = (pagina - 1) * tamanioPagina;
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u ORDER BY u.id DESC", Usuario.class);
            query.setFirstResult(inicio);
            query.setMaxResults(tamanioPagina);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public long contarUsuarios() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM Usuario u", Long.class);
            return query.getSingleResult();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }
}
