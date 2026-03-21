/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.nubulamusicwebaplication.controllers;

import com.mycompany.nubulamusicwebaplication.models.Album;
import com.mycompany.nubulamusicwebaplication.models.Usuario;
import com.mycompany.nubulamusicwebaplication.service.AlbumService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.mycompany.nubulamusicwebaplication.service.IAlbumService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;

/**
 *
 * @author PC
 */
@WebServlet(name = "AlbumServlet", urlPatterns = {"/albums"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 / 2,
        maxRequestSize = 1024 * 1024 * 5
)
public class AlbumServlet extends HttpServlet {

    private final IAlbumService albumservice = new AlbumService();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String action = request.getParameter("accion");
        if (action == null) {
            action = "list";
        }
        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("/views/admin/album-form.jsp").forward(request, response);
                    break;
                case "edit":
                    Long id = Long.parseLong(request.getParameter("id"));
                    Album album = albumservice.obtenerAlbum(id, usuario.getId());
                    request.setAttribute("album", album);
                    request.getRequestDispatcher("/views/admin/album-form.jsp").forward(request, response);
                    break;
                case "delete":
                    Long deleteId = Long.parseLong(request.getParameter("id"));
                    albumservice.eliminarAlbum(deleteId, usuario.getId());
                    response.sendRedirect("/albums");
                    break;
                default:
                    List<Album> albums = albumservice.obtenerAlbumsUsuario(usuario.getId());
                    request.setAttribute("albums", albums);
                    request.getRequestDispatcher("/views/admin/mis-albums.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

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
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String idParam = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        Part filePart = request.getPart("imagen");
        String fileName = filePart.getSubmittedFileName();
        if (!fileName.endsWith(".png")) {
            throw new ServletException("Solo se permiten imagenes en formato.png");
        }
        String newFileName = System.currentTimeMillis() + "_" + fileName;
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String filePath = uploadPath + File.separator + newFileName;
        filePart.write(filePath);
        String imageUrl = "uploads/" + newFileName;
        Album album = new Album();
        album.setTitulo(titulo);
        album.setDescripcion(descripcion);
        album.setImageUrl(imageUrl);
        album.setUsuario(usuario);
        try {
            if (idParam == null || idParam.isEmpty()) {
                albumservice.crearAlbum(album);
            } else {
                album.setId(Long.parseLong(idParam));
                albumservice.actualizarAlbum(album, usuario.getId());
            }
            response.sendRedirect(request.getContextPath() + "/albums");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("album", album);
            request.getRequestDispatcher("/views/admin/album-form.jsp").forward(request, response);
        }
    }

}
