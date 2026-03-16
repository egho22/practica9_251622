/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.controllers;

import com.mycompany.nubulamusicwebaplication.models.Usuario;
import com.mycompany.nubulamusicwebaplication.service.IUsuarioService;
import com.mycompany.nubulamusicwebaplication.service.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.rmi.ServerError;
import java.rmi.ServerException;

/**
 *
 * @author martinbl
 */
@WebServlet(name = "AutenticacionServlet", urlPatterns = {"/autenticacion"})
public class AutenticacionServlet extends HttpServlet {

    private final IUsuarioService usuarioservice = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");

        Usuario usuario = usuarioservice.autenticar(correo, contrasenia);

        try {
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("usuario", usuario);
            sesion.setAttribute("correo", usuario.getCorreo());
            sesion.setAttribute("nombre", usuario.getNombre());
            //response.sendRedirect(request.getContextPath() + "/index.jsp");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/auth/iniciar-sesion.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al autenticar el usuario", e);
        }
    }

}
