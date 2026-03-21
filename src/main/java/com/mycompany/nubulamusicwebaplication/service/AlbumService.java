/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.service;

import com.mycompany.nubulamusicwebaplication.dao.AlbumDAO;
import com.mycompany.nubulamusicwebaplication.dao.IAlbumDAO;
import com.mycompany.nubulamusicwebaplication.models.Album;
import java.util.List;

/**
 *
 * @author martinbl
 */
public class AlbumService implements IAlbumService {

    private final IAlbumDAO albumDAO = new AlbumDAO();

    @Override
    public void crearAlbum(Album album) {

        if (album == null) {
            throw new IllegalArgumentException("El álbum no puede ser nulo");
        }

        if (album.getTitulo() == null || album.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }

        if (album.getTitulo().length() > 150) {
            throw new IllegalArgumentException("El título es demasiado largo");
        }

        if (album.getDescripcion() == null || album.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }

        if (album.getImageUrl() == null || album.getImageUrl().trim().isEmpty()) {
            throw new IllegalArgumentException("La imagen es obligatoria");
        }

        if (album.getUsuario() == null) {
            throw new IllegalArgumentException("El álbum debe pertenecer a un usuario");
        }

        albumDAO.guardar(album);
    }

    @Override
    public void actualizarAlbum(Album album, Long usuarioLogueadoId) {

        if (album == null || album.getId() == null) {
            throw new IllegalArgumentException("El álbum no es válido");
        }

        if (usuarioLogueadoId == null) {
            throw new SecurityException("Usuario no autenticado");
        }

        // Obtener álbum real desde BD
        Album existente = albumDAO.buscarPorId(album.getId());

        if (existente == null) {
            throw new IllegalArgumentException("El álbum no existe");
        }

        // Validar propietario
        if (!existente.getUsuario().getId().equals(usuarioLogueadoId)) {
            throw new SecurityException("No puedes editar este álbum");
        }

        // Mantener usuario original
        album.setUsuario(existente.getUsuario());

        albumDAO.actualizar(album);
    }

    @Override
    public void eliminarAlbum(Long id, Long usuarioLogueadoId) {

        if (id == null) {
            throw new IllegalArgumentException("ID inválido");
        }

        if (usuarioLogueadoId == null) {
            throw new SecurityException("Usuario no autenticado");
        }

        Album existente = albumDAO.buscarPorId(id);

        if (existente == null) {
            throw new IllegalArgumentException("El álbum no existe");
        }

        if (!existente.getUsuario().getId().equals(usuarioLogueadoId)) {
            throw new SecurityException("No puedes eliminar este álbum");
        }

        albumDAO.eliminar(id);
    }

    @Override
    public Album obtenerAlbum(Long id, Long usuarioLogueadoId) {

        if (id == null) {
            throw new IllegalArgumentException("ID inválido");
        }

        Album album = albumDAO.buscarPorId(id);

        if (album == null) {
            throw new IllegalArgumentException("El álbum no existe");
        }

        if (!album.getUsuario().getId().equals(usuarioLogueadoId)) {
            throw new SecurityException("No puedes acceder a este álbum");
        }

        return album;
    }

    @Override
    public List<Album> obtenerAlbumsUsuario(Long usuarioId) {

        if (usuarioId == null) {
            throw new IllegalArgumentException("Usuario inválido");
        }

        return albumDAO.buscarPorUsuario(usuarioId);
    }
}
