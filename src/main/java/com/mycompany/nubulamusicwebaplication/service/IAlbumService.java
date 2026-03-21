/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.service;

import com.mycompany.nubulamusicwebaplication.models.Album;
import java.util.List;

/**
 *
 * @author martinbl
 */
public interface IAlbumService {

    void crearAlbum(Album album);

    void actualizarAlbum(Album album, Long usuarioLogueadoId);

    void eliminarAlbum(Long id, Long usuarioLogueadoId);

    Album obtenerAlbum(Long id, Long usuarioLogueadoId);

    List<Album> obtenerAlbumsUsuario(Long usuarioId);
}
