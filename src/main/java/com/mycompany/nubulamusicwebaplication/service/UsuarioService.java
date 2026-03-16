/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.service;

import com.mycompany.nubulamusicwebaplication.dao.IUsuarioDAO;
import com.mycompany.nubulamusicwebaplication.dao.UsuarioDAO;
import com.mycompany.nubulamusicwebaplication.models.Usuario;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UsuarioService implements IUsuarioService {
    
    private final IUsuarioDAO usuarioDAO = new UsuarioDAO();
    
    @Override
    public void registrar(String nombre,
            String correo,
            String contrasenia,
            String pseudonimo,
            String estado,
            String cuenta,
            LocalDate fechaNacimiento,
            boolean terminosAceptados) {
        
        validarNombre(nombre);
        validarCorreo(correo);
        validarContrasenia(contrasenia);
        validarPseudonimo(pseudonimo);
        validarEstado(estado);
        validarCuenta(cuenta);
        validarFechaNacimiento(fechaNacimiento);
        validarTerminos(terminosAceptados);
        
        if (usuarioDAO.buscarPorCorreo(correo) != null) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }
        
        if (usuarioDAO.buscarPorPseudonimo(pseudonimo) != null) {
            throw new IllegalArgumentException("El pseudónimo ya está en uso.");
        }
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre.trim());
        usuario.setCorreo(correo.trim().toLowerCase());
        usuario.setContrasenia(contrasenia);
        usuario.setPseudonimo(pseudonimo.trim());
        usuario.setEstado(estado.trim().toLowerCase());
        usuario.setCuenta(cuenta.trim().toLowerCase());
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setTerminosAceptados(terminosAceptados);
        
        usuarioDAO.guardar(usuario);
    }
    
    @Override
    public Usuario autenticar(String correo, String contrasenia) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }
        
        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }
        
        Usuario usuario = usuarioDAO.buscarPorCorreoYContrasenia(
                correo.trim().toLowerCase(),
                contrasenia
        );
        
        if (usuario == null) {
            throw new IllegalArgumentException("Correo o contraseña incorrectos.");
        }
        
        if (!"activo".equalsIgnoreCase(usuario.getEstado())) {
            throw new IllegalArgumentException("La cuenta no está activa.");
        }
        
        return usuario;
    }
    
    @Override
    public Usuario buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del usuario no es válido.");
        }
        return usuarioDAO.buscarPorId(id);
    }
    
    @Override
    public Usuario buscarPorCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }
        return usuarioDAO.buscarPorCorreo(correo.trim().toLowerCase());
    }
    
    @Override
    public List<Usuario> listarTodos() {
        return usuarioDAO.listarTodos();
    }
    
    @Override
    public void actualizarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        
        if (usuario.getId() == null || usuario.getId() <= 0) {
            throw new IllegalArgumentException("El id del usuario no es válido.");
        }
        
        validarNombre(usuario.getNombre());
        validarCorreo(usuario.getCorreo());
        validarPseudonimo(usuario.getPseudonimo());
        validarEstado(usuario.getEstado());
        validarCuenta(usuario.getCuenta());
        validarFechaNacimiento(usuario.getFechaNacimiento());
        
        usuarioDAO.actualizar(usuario);
    }
    
    @Override
    public void eliminarUsuario(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del usuario no es válido.");
        }
        usuarioDAO.eliminar(id);
    }
    
    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        
        String nombreLimpio = nombre.trim();
        
        if (nombreLimpio.length() < 10 || nombreLimpio.length() > 100) {
            throw new IllegalArgumentException("El nombre debe tener entre 10 y 100 caracteres.");
        }
    }
    
    private void validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }
        
        String correoLimpio = correo.trim();
        
        if (correoLimpio.length() < 5 || correoLimpio.length() > 100) {
            throw new IllegalArgumentException("El correo debe tener entre 5 y 100 caracteres.");
        }
        
        if (!correoLimpio.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El formato del correo no es válido.");
        }
    }
    
    private void validarContrasenia(String contrasenia) {
        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }
        
        if (contrasenia.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        
        if (contrasenia.length() > 100) {
            throw new IllegalArgumentException("La contraseña no puede exceder 100 caracteres.");
        }
    }
    
    private void validarPseudonimo(String pseudonimo) {
        if (pseudonimo == null || pseudonimo.trim().isEmpty()) {
            throw new IllegalArgumentException("El pseudónimo es obligatorio.");
        }
        
        String pseudonimoLimpio = pseudonimo.trim();
        
        if (!pseudonimoLimpio.matches("^[a-zA-Z]{3,10}-[0-9]{2,10}$")) {
            throw new IllegalArgumentException("El pseudónimo debe cumplir el formato Letras-Números, por ejemplo: Nebula-2025.");
        }
    }
    
    private void validarEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }
        
        String estadoLimpio = estado.trim().toLowerCase();
        
        if (!estadoLimpio.equals("activo")
                && !estadoLimpio.equals("inactivo")
                && !estadoLimpio.equals("pendiente")) {
            throw new IllegalArgumentException("El estado seleccionado no es válido.");
        }
    }
    
    private void validarCuenta(String cuenta) {
        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de cuenta es obligatorio.");
        }
        
        String cuentaLimpia = cuenta.trim().toLowerCase();
        
        if (!cuentaLimpia.equals("gratis")
                && !cuentaLimpia.equals("basica")
                && !cuentaLimpia.equals("premium")) {
            throw new IllegalArgumentException("El tipo de cuenta seleccionado no es válido.");
        }
    }
    
    private void validarFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }
        
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        
        if (edad < 13) {
            throw new IllegalArgumentException("Debes tener al menos 13 años para registrarte.");
        }
    }
    
    private void validarTerminos(boolean terminosAceptados) {
        if (!terminosAceptados) {
            throw new IllegalArgumentException("Debes aceptar los términos y condiciones.");
        }
    }
    
    @Override
    public List<Usuario> listaTop(int limite) {
        if (limite < 0) {
            limite = 10;
        }
        
        return usuarioDAO.listaTop(limite);
    }
    
    @Override
    public List<Usuario> listarPaginado(int pagina, int tamanioPagina) {
        if (pagina <= 0) {
            pagina = 1;
        }
        if (tamanioPagina <= 0) {
            tamanioPagina = 10;
        }
        return usuarioDAO.listarPaginado(pagina, tamanioPagina);
    }
    
    @Override
    public long contarUsuarios() {
        return usuarioDAO.contarUsuarios();
    }
}
