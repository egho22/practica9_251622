/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.apirest;

import com.mycompany.nubulamusicwebaplication.dto.ResponseMessageDTO;
import com.mycompany.nubulamusicwebaplication.dto.UsuarioAuthDTO;
import com.mycompany.nubulamusicwebaplication.models.Usuario;
import com.mycompany.nubulamusicwebaplication.service.IUsuarioService;
import com.mycompany.nubulamusicwebaplication.service.UsuarioService;
import com.mycompany.nubulamusicwebaplication.util.JSONMapper;
import com.mycompany.nubulamusicwebaplication.util.JWTUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
@WebServlet(name = "AuthServletAPI", urlPatterns = {"/api/auth/*"})
public class AuthServletAPI extends HttpServlet {

    private IUsuarioService usuarioService = new UsuarioService();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Lee el cuerpo de la petición JSON y lo convierte a un objeto DTO
        UsuarioAuthDTO req = JSONMapper.mapper.readValue(request.getInputStream(), UsuarioAuthDTO.class);

        // Intenta autenticar al usuario con correo y contraseña
        Usuario user = usuarioService.autenticar(req.getCorreo(), req.getContrasenia());
        ResponseMessageDTO mensaje = new ResponseMessageDTO();

        // Si el usuario no existe o los datos son incorrectos, devuelve 401 (Unauthorized)
        if (user == null) {
            response.setStatus(401);
            mensaje.setSuccess(false);
            mensaje.setMessage("Credenciales invalidas");
            JSONMapper.mapper.writeValue(response.getWriter(), mensaje);
            return;
        }

        // Si todo está bien, genera el token JWT usando el correo del usuario
        String token = JWTUtil.generarToken(user.getCorreo());

        //Prepara el objeto de respuesta
        mensaje.setSuccess(true);
        mensaje.setMessage(token);

        // Envía la respuesta como application/json
        JSONMapper.mapper.writeValue(response.getWriter(), mensaje);
    }

}
