/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.DaoArriendo;
import DAO.DaoCliente;
import DAO.DaoPersona;
import DAO.DaoReparacion;
import clases.Cl_Persona;
import clases.Cl_Usuario;
import static controlador.ServletTrabajador.getBytesFromInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import wsUsuario.Service1;

/**
 *
 * @author Zeit
 */
@WebServlet(name = "ServletCliente", urlPatterns = {"/ServletCliente"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class ServletCliente extends HttpServlet {
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
        try {
            String boton = request.getParameter("btnAccion");
            if (boton.equals("Registrarse")) {
                String rut=request.getParameter("txtRut");
                String nombre = request.getParameter("txtNombre");
                String apellido = request.getParameter("txtApellido");
                String txtEdad = request.getParameter("txtEdad");
                int edad = Integer.valueOf(txtEdad);
                String direccion = request.getParameter("txtDireccion");
                String correo = request.getParameter("txtCorreo");
                String txtComuna = request.getParameter("cboComuna");
                int idComuna = Integer.valueOf(txtComuna);
                
                Cl_Persona persona = new Cl_Persona();
                persona.setRut(rut);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setEdad(edad);
                persona.setDireccion(direccion);
                persona.setCorreo(correo);
                persona.setIdComuna(idComuna);
                
                String user = request.getParameter("txtUsuario");
                String pass = request.getParameter("txtPass");
                
                Cl_Usuario usuario = new Cl_Usuario();
                usuario.setUser(user);
                usuario.setPass(pass);
                
                DaoCliente daoCliente = new DaoCliente();
                int resp = daoCliente.ingresarCliente(persona, usuario);
                
                if (resp != 1) {
                    request.setAttribute("mensaje", "No se pudo ingresar el Cliente");
                    request.getRequestDispatcher("registro.jsp").forward(request, response);
                }else{
                    request.setAttribute("mensaje", "El trabajador se ingreso correctamente");
                    request.getRequestDispatcher("registro.jsp").forward(request, response);
                }
            }
            if (boton.equals("Ingresar Arriendo")) {
                HttpSession sesion = request.getSession();
                Cl_Usuario usuario = (Cl_Usuario) sesion.getAttribute("usuario");
                String municipalidad = request.getParameter("cboMunicipalidad");
                DaoPersona daoPersona= new DaoPersona();
                Cl_Persona persona = daoPersona.obtenerPersonaIdUsuario(usuario.getIdUsuario());
                int convenio = obtenerConvenio(municipalidad, persona.getRut());
                if (convenio!=1) {
                    request.setAttribute("mensaje", "El trabajador no tiene convenio con la municipalidad");
                    request.getRequestDispatcher("ingresarArriendo.jsp").forward(request, response);
                    return;
                }
                String fechaInicio = request.getParameter("txtFecInicio");
                String fechaTermino = request.getParameter("txtFecTermino");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fecInicio = sdf.parse(fechaInicio);
                Date fecTermino = sdf.parse(fechaTermino);
                String tipoPago = request.getParameter("cboTipoPago");
                String txtDeposito = request.getParameter("txtDeposito");
                int deposito = Integer.valueOf(txtDeposito);
                String txtTotal = request.getParameter("txtTotal");
                int total = Integer.valueOf(txtTotal);
                String cboBiciArriendo = request.getParameter("cboBiciArriendo");
                int biciArriendo = Integer.valueOf(cboBiciArriendo);
                
                DaoArriendo daoArriendo = new DaoArriendo();
                int resp = daoArriendo.insertarArriento(municipalidad, fecInicio, fecTermino, tipoPago, total, deposito, usuario.getIdPersona(), biciArriendo);
                if (resp == 1) {
                    request.setAttribute("mensaje", "El Arriendo se inserto Correctamente");
                    request.getRequestDispatcher("ingresarArriendo.jsp").forward(request, response);
                } else {
                    request.setAttribute("mensaje", "No se pudo insertar el arriendo");
                    request.getRequestDispatcher("ingresarArriendo.jsp").forward(request, response);
                }
                request.setAttribute("mensaje", municipalidad + fecInicio + fecTermino);
                request.getRequestDispatcher("ingresarArriendo.jsp").forward(request, response);

            }
            if (boton.equals("Ingresar Reparacion")) {
                String descripcion = request.getParameter("txtDescripcion");
                String fechaEntrega = request.getParameter("txtFechaEntrega");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fecEntrega = sdf.parse(fechaEntrega);

                Part fileFoto = request.getPart("filFoto");
                InputStream fileContent = fileFoto.getInputStream();
                byte[] fileFotoByte = getBytesFromInputStream(fileContent);
                //File raiz = new File("C:/reparaciones/");

                HttpSession sesion = request.getSession();
                Cl_Usuario usuario = (Cl_Usuario) sesion.getAttribute("usuario");

                //File nuevaCarpeta = new File(raiz, String.valueOf(usuario.getIdUsuario()));
                //nuevaCarpeta.mkdirs();
                int valorRandom = (int) Math.floor(Math.random() * 1000 + 1);
                OutputStream out = null;
                String rutaFoto = "img/reparaciones/"+ String.valueOf(usuario.getIdUsuario())+"foto" +valorRandom;
                //out = new BufferedOutputStream(new FileOutputStream(rutaFoto));
                //out.write(fileFotoByte);
                DaoReparacion daoReparacion = new DaoReparacion();
                int resp = daoReparacion.ingesarReparacion(descripcion, fecEntrega, rutaFoto, usuario.getIdPersona());
                if (resp == 1) {
                    guardarImagen(fileFotoByte, rutaFoto);
                    request.setAttribute("mensaje", "La Reparacion se ingreso correctamente");
                }else{
                    request.setAttribute("mensaje", "La Reparacion NO se pudo ingresar");
                }
                request.getRequestDispatcher("ingresarReparacion.jsp").forward(request, response);
            }
        } catch (Exception err) {
            request.setAttribute("mensaje", err);
            request.getRequestDispatcher("ingresarReparacion.jsp").forward(request, response);
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
            Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletCliente.class.getName()).log(Level.SEVERE, null, ex);
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

    private Integer obtenerConvenio(java.lang.String municipalidad, java.lang.String rut) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsUsuario.IServicio port = service.getBasicHttpBindingIServicio();
        return port.obtenerConvenio(municipalidad, rut);
    }

    private Boolean guardarImagen(byte[] imagen, java.lang.String nombreArchivo) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsUsuario.IServicio port = service.getBasicHttpBindingIServicio();
        return port.guardarImagen(imagen, nombreArchivo);
    }

}
