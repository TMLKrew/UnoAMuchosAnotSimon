package es.albarregas.controllers;

import es.albarregas.beans.Persona;
import es.albarregas.beans.Libro;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO gdao = daof.getGenericoDAO();
        Persona persona = new Persona();
        List<Libro> libros = new ArrayList<Libro>();
        Libro libro = new Libro();
        String url = null;
        switch (request.getParameter("op")) {
            case "add":

                try {
                    BeanUtils.populate(persona, request.getParameterMap());
                    libro = new Libro();
                    libro.setTitulo(request.getParameter("libro01"));
                    libros.add(libro);

                    libro = new Libro();
                    libro.setTitulo(request.getParameter("libro02"));
                    libros.add(libro);

                    libro = new Libro();
                    libro.setTitulo(request.getParameter("libro03"));
                    libros.add(libro);

                    persona.setLibros(libros);

                } catch (IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
                gdao.insertUpdate(persona);
                url = "index.html";
                break;
            case "delete":
//                profesor = adao.getOne(Integer.parseInt(request.getParameter("registro")));
//                adao.delete(profesor);
                persona = (Persona) gdao.select(Long.parseLong(request.getParameter("registro")), Persona.class);
                gdao.delete(persona);
                url = "index.html";
                break;
            case "update":
                persona = (Persona) gdao.select(Long.parseLong(request.getParameter("registro")), Persona.class);
                request.setAttribute("persona", persona);
                url = "JSP/formularioActualizar.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
