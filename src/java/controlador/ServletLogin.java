/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.DaoUsuario;
import clases.Cl_Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import wsUsuario.Service1;

/**
 *
 * @author Zeit
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_50304/Servicio.svc.wsdl")
    private Service1 service;

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String user = request.getParameter("txtUsuario");
            String password = request.getParameter("txtPass");
            DaoUsuario daoUsuario = new DaoUsuario();
            Cl_Usuario usuario = daoUsuario.obtenerUsuario(user, password);
            int idTipoUsuario = usuario.getIdTipoUsuario();
            HttpSession sesion = request.getSession();
            if (idTipoUsuario==1) {
                request.setAttribute("mensaje", "Bienvenido usuario "+usuario.getUser());
                sesion.setAttribute("usuario", usuario);
                response.sendRedirect("./listarTrabajadores.jsp");
                //request.getRequestDispatcher("./gerente/listarTrabajadores.jsp").forward(request, response);
            }else if(idTipoUsuario==7){
                sesion.setAttribute("usuario", usuario);
                response.sendRedirect("./listarProductos.jsp");
            }
            else if(idTipoUsuario==4){
                sesion.setAttribute("usuario", usuario);
                response.sendRedirect("./listarReparaciones.jsp");
            }
            else if(idTipoUsuario==2){
                sesion.setAttribute("usuario", usuario);
                response.sendRedirect("./gestionarProductos.jsp");
            }
            else{
                request.setAttribute("mensaje", "El usuario ingresado no es valido");
                request.getRequestDispatcher("./listarProductos.jsp").forward(request, response);
            }
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
