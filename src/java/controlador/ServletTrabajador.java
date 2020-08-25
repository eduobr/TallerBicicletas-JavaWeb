/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.DaoBicicleta;
import DAO.DaoReparacion;
import DAO.DaoTrabajador;
import DAO.DaoUsuario;
import clases.Cl_Contrato;
import clases.Cl_Persona;
import clases.Cl_Trabajador;
import clases.Cl_Usuario;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
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
import javax.xml.bind.JAXBElement;
import javax.xml.ws.WebServiceRef;
import wsUsuario.ObjectFactory;
import wsUsuario.Service1;

/**
 *
 * @author Zeit
 */
@WebServlet(name = "ServletTrabajador", urlPatterns = {"/ServletTrabajador"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class ServletTrabajador extends HttpServlet {

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
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        String boton = request.getParameter("btnAccion");
        try {
            /* TODO output your page here. You may use following sample code. */
            if (boton.equals("Guardar Trabajador")) {
                //Crear Persona
                String rut = request.getParameter("txtRut");
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
                persona.setCorreo(correo);
                persona.setDireccion(direccion);
                persona.setIdComuna(idComuna);

                //Crear Trabajador
                //Convertir el sueldo en int
                String txtSueldo = request.getParameter("txtSueldo");
                int sueldo = Integer.valueOf(txtSueldo);
                Cl_Trabajador trabajador = new Cl_Trabajador();
                trabajador.setSueldo(sueldo);

                //Crear Contrato
                Part fileCotAfp = request.getPart("flAfp");
                Part fileSalud = request.getPart("flSalud");
                Part fileCarnet = request.getPart("flCarnet");
                Part fileCerNacimiento = request.getPart("flCerNacimiento");
                Part fileCerResidencia = request.getPart("flCerResidencia");

                //Cambiar el tipo de trabajador
                String tipoTrabajador = request.getParameter("cboTipoTrabajador");
                int tp = Integer.valueOf(tipoTrabajador);

                //Crear Usuario
                String user = request.getParameter("txtUsuario");
                String pass = request.getParameter("txtPass");

                Cl_Usuario usuario = new Cl_Usuario();
                usuario.setUser(user);
                usuario.setPass(pass);
                usuario.setIdTipoUsuario(tp);

                //Crear Imagenes como Byte
                InputStream fileContent = fileCotAfp.getInputStream();
                byte[] cotAfpByte = getBytesFromInputStream(fileContent);
                fileContent = fileSalud.getInputStream();
                byte[] saludByte = getBytesFromInputStream(fileContent);
                fileContent = fileCarnet.getInputStream();
                byte[] carnetByte = getBytesFromInputStream(fileContent);
                fileContent = fileCerNacimiento.getInputStream();
                byte[] cerNacimientoByte = getBytesFromInputStream(fileContent);
                fileContent = fileCerResidencia.getInputStream();
                byte[] cerResidenciaByte = getBytesFromInputStream(fileContent);

                String afp = "img/contratos/" + rut + "cotizaciones-afp.jpg";
                String salud = "img/contratos/" + rut + "salud.jpg";
                String carnet = "img/contratos/" + rut + "carnet.jpg";
                String cerNacimiento = "img/contratos/" + rut + "certificado-nacimiento.jpg";
                String cerResidencia = "img/contratos/" + rut + "certificado-residencia.jpg";

                Cl_Contrato contrato = new Cl_Contrato();
                contrato.setRutaAfp(afp);
                contrato.setRutaSalud(salud);
                contrato.setRutaCarnet(carnet);
                contrato.setRutaCerNacimiento(cerNacimiento);
                contrato.setRutaCerResidencia(cerResidencia);

                DaoTrabajador daoTrabajador = new DaoTrabajador();
                int resp = daoTrabajador.ingresarTrabajador(persona, contrato, trabajador, usuario);

                if (resp == 1) {
                    guardarImagen(cotAfpByte, afp);
                    guardarImagen(saludByte, salud);
                    guardarImagen(carnetByte, carnet);
                    guardarImagen(cerNacimientoByte, cerNacimiento);
                    guardarImagen(cerResidenciaByte, cerResidencia);
                    request.setAttribute("mensaje", "El trabajador se ingreso correctamente");
                    request.getRequestDispatcher("./ingresarTrabajador.jsp").forward(request, response);
                }

            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e);
            request.getRequestDispatcher("./ingresarTrabajador.jsp").forward(request, response);
        }

        try {
            if (boton.equals("Armar Bicicleta")) {
                String modelo = request.getParameter("txtModelo");
                String descripcion = request.getParameter("txtDescripcion");
                String txtTipoBici = request.getParameter("cboTipoBici");
                int tipoBici = Integer.valueOf(txtTipoBici);
                String txtCantidad = request.getParameter("txtCantidad");
                int cantidad = Integer.valueOf(txtCantidad);
                String txtAro = request.getParameter("txtAro");
                int aro = Integer.valueOf(txtAro);
                Part fileBici = request.getPart("flBicicleta");
                InputStream fileContent = fileBici.getInputStream();
                byte[] biciByte = getBytesFromInputStream(fileContent);

                HttpSession sesion = request.getSession();
                Cl_Usuario usuario = (Cl_Usuario) sesion.getAttribute("usuario");

                String archivo = "img/bicicletas-armadas/" + txtTipoBici + "-" + modelo + ".jpg";
                DaoBicicleta daoBicicleta = new DaoBicicleta();
                int resp = daoBicicleta.armarBicicleta(modelo, descripcion, cantidad, archivo, aro, tipoBici, usuario.getIdTrabajador());
                if (resp == 1) {
                    request.setAttribute("mensaje", "La bicicleta se ingreso correctamente");
                    guardarImagen(biciByte, archivo);
                } else {
                    request.setAttribute("mensaje", "La bicicleta No se pudo Ingresar");
                }
                request.getRequestDispatcher("./armarBicicleta.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e);
            request.getRequestDispatcher("./armarBicicleta.jsp").forward(request, response);
        }

        try {

            if (boton.equals("Despedir")) {
                String idTrab = request.getParameter("txtIdTrab");
                int idTrabajador = Integer.valueOf(idTrab);
                DaoUsuario daoUsuario = new DaoUsuario();
                int resp = daoUsuario.darBajaUsuario(idTrabajador);
                if (resp == 1) {
                    request.setAttribute("mensaje", "El trabajador se dio de baja correctamente");
                } else {
                    request.setAttribute("mensaje", "El trabajador no se pudo dar de baja");
                }
                request.getRequestDispatcher("./listarTrabajadores.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e);
            request.getRequestDispatcher("./listarTrabajadores.jsp").forward(request, response);
        }
        try {
            if (boton.equals("Responder Solicitud")) {
                String idReparacion = request.getParameter("txtIdReparacion");
                request.setAttribute("idReparacion", idReparacion);
                request.getRequestDispatcher("./responderReparacion.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e);
            request.getRequestDispatcher("./responderReparacion.jsp").forward(request, response);
        }

        try {

            if (boton.equals("Responder")) {
                String txtIdReparacion = request.getParameter("txtIdReparacion");
                int idReparacion = Integer.valueOf(txtIdReparacion);
                String respuesta = request.getParameter("cboRespuestaRep");
                String descripcion = request.getParameter("txtDescripcionRep");
                DaoReparacion daoReparacion = new DaoReparacion();
                int resp = daoReparacion.ingresarRespuesta(idReparacion, respuesta, descripcion);
                if (resp == 1) {
                    request.setAttribute("mensaje", "La reparación se ha constestado correctamente");
                } else {
                    request.setAttribute("mensaje", "El trabajador no se pudo dar de baja");
                }
                request.getRequestDispatcher("./listarReparaciones.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", e);
            request.getRequestDispatcher("./listarReparaciones.jsp").forward(request, response);
        }

    }

    public static byte[] getBytesFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        for (int len = is.read(buffer); len != -1; len = is.read(buffer)) {
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
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

    private Boolean guardarImagen(byte[] imagen, java.lang.String nombreArchivo) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsUsuario.IServicio port = service.getBasicHttpBindingIServicio();
        return port.guardarImagen(imagen, nombreArchivo);
    }
}
