/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author PC
 */
public class JPAUtil {

    private static JPAUtil instance;
    private EntityManagerFactory emf;

    // Constructor privado para evitar instanciación externa
    private JPAUtil() {
        try {
            emf = Persistence.createEntityManagerFactory("NubulaMusicPU");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear EntityManagerFactory", e);
        }
    }

    // Método para obtener la única instancia
    public static synchronized JPAUtil getInstance() {
        if (instance == null) {
            instance = new JPAUtil();
        }
        return instance;
    }

    // Crear EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Cerrar factory cuando la aplicación termine
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
