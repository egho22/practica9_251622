/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.dao;

import com.mycompany.nubulamusicwebaplication.models.Album;
import com.mycompany.nubulamusicwebaplication.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author martinbl
 */
public class AlbumDAO implements IAlbumDAO {
     EntityManager em = JPAUtil.getInstance().getEntityManager();

    @Override
    public void guardar(Album album){

        em.getTransaction().begin();
        em.persist(album);
        em.getTransaction().commit();

    }

    @Override
    public void actualizar(Album album){

        em.getTransaction().begin();
        em.merge(album);
        em.getTransaction().commit();

    }

    @Override
    public void eliminar(Long id){

        em.getTransaction().begin();

        Album album = em.find(Album.class,id);

        em.remove(album);

        em.getTransaction().commit();

    }

    @Override
    public Album buscarPorId(Long id){

        return em.find(Album.class,id);

    }

    @Override
    public List<Album> buscarPorUsuario(Long usuarioId){

        return em.createQuery(
            "SELECT a FROM Album a WHERE a.usuario.id = :uid",
            Album.class
        )
        .setParameter("uid",usuarioId)
        .getResultList();

    }
}
