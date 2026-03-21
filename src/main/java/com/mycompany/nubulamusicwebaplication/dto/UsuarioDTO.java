/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.dto;

/**
 *
 * @author PC
 */
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String pseudonimo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nombre, String correo, String pseudonimo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pseudonimo = pseudonimo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

}
