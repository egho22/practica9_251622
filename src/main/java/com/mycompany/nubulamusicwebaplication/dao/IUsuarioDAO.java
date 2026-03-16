/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.dao;

import com.mycompany.nubulamusicwebaplication.models.Usuario;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IUsuarioDAO {

    void guardar(Usuario usuario);

    Usuario buscarPorId(Long id);

    Usuario buscarPorCorreo(String correo);

    Usuario buscarPorCorreoYContrasenia(String correo, String contrasenia);

    Usuario buscarPorPseudonimo(String pseudonimo);

    List<Usuario> listarTodos();

    void actualizar(Usuario usuario);

    void eliminar(Long id);

    List<Usuario> listaTop(int limite);

    List<Usuario> listarPaginado(int pagina, int tamanioPagina);
    
    long contarUsuarios();

}
