/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.dto;

import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class UsuarioRequestDTO {

    private String nombre;
    private String correo;
    private String contrasenia;
    private String pseudonimo;
    private String estado;
    private String cuenta;
    private LocalDate fechaNacimiento;
    private boolean terminosAceptados;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(String nombre, String correo, String contrasenia, String pseudonimo, String estado, String cuenta, LocalDate fechaNacimiento, boolean terminosAceptados) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.pseudonimo = pseudonimo;
        this.estado = estado;
        this.cuenta = cuenta;
        this.fechaNacimiento = fechaNacimiento;
        this.terminosAceptados = terminosAceptados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isTerminosAceptados() {
        return terminosAceptados;
    }

    public void setTerminosAceptados(boolean terminosAceptados) {
        this.terminosAceptados = terminosAceptados;
    }
    
    
}
