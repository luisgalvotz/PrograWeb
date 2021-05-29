/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbconnection.controllers;

import com.dbconnection.dao.UserDAO;
import com.dbconnection.models.UserModel;
import com.dbconnection.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Este servlet controlara los Usuarios, verifiquen el web.xml
 * este es el primer archivo en llamarse, alli exsite un apartado llamado
 * welcome file list donde pueden colocar que archivo quieren que sea el primero
 * en ser cargado
 * en este momento es este
 * @author magoc
 */
@WebServlet(name = "UsersController", urlPatterns = {"/UsersController"})
@MultipartConfig(maxFileSize = 1000 * 1000 * 5, maxRequestSize = 1000 * 1000 * 25, fileSizeThreshold = 1000 * 1000)
public class UsersController extends HttpServlet {

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
        // Obtenemos los usuarios del DAO
        List<UserModel> users = null;
		try {
			users = UserDAO.getUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Lo agregamos como atributo al request
        request.setAttribute("users", users);
        // Enviamos el request a index.jsp con la informacion
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        
        String name = request.getParameter("name"); // Obtenemos el nombre debe coincidir con el name del input
       
        String password = request.getParameter("password");  // Obtenemos el contraseña debe coincidir con el name del input

        String path = request.getServletContext().getRealPath("");
        
        File fileSaveDir = new File(path + FileUtils.RUTE_USER_IMAGE); // Obtenemos la Direccion donde deseamos guardarlo
        // Sino existe el directorio la creamos
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        
        Part file = request.getPart("imagen"); // Obtenemos la imagen, debe coincidir con el name del input
        String contentType = file.getContentType();
        System.out.println("Imagen: " + file.getName());
        
        
        String nameImage = file.getName() + System.currentTimeMillis() + FileUtils.GetExtension(contentType); // Remplazamos el nombre que tiene para que no existan duplicados
        String fullPath = path + FileUtils.RUTE_USER_IMAGE + "/" + nameImage;
        
        file.write(fullPath); // Copiamos la imagen en la ruta especificada
        UserModel user = new UserModel(name, password, FileUtils.RUTE_USER_IMAGE + "/" + nameImage);
        try {
			UserDAO.insertUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        response.sendRedirect("index.jsp"); // Retornamos al index
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
