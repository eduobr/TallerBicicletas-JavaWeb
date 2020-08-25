/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.DaoCompra;
import DAO.DaoProducto;
import clases.Cl_Detalle_Compra;
import clases.Cl_Producto;
import clases.Cl_Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zeit
 */
@WebServlet(name = "ServletGestion", urlPatterns = {"/ServletGestion"})
public class ServletGestion extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession sesion = request.getSession();
            Cl_Usuario usuario = (Cl_Usuario) sesion.getAttribute("usuario");
            String boton = request.getParameter("btnAccion");
            if (boton.equals("Ver Detalle")) {
                String txtIdCompra = request.getParameter("txtIdCompra");
                int idCompra = Integer.valueOf(txtIdCompra);
                DaoCompra daoCompra = new DaoCompra();
                List<Cl_Detalle_Compra> listaDetalleCompra = daoCompra.obtenerDetalleCompra(idCompra);
                request.setAttribute("listaDetalle", listaDetalleCompra);
                request.getRequestDispatcher("listarDetalleCompra.jsp").forward(request, response);
            }
            if (boton.equals("Comprar")) {
                String txtIdCompra = request.getParameter("txtIdCompra");
                int idCompra = Integer.valueOf(txtIdCompra);
                DaoCompra daoCompra = new DaoCompra();
                int resp = daoCompra.cambiarEstadoCompra(idCompra, 13,usuario.getIdTrabajador());
                if (resp==1) {
                    request.setAttribute("mensaje", "La compra se realizo correctamente");
                    request.getRequestDispatcher("gestionarProductos.jsp").forward(request, response);
                }
            }
            if (boton.equals("Rechazar")) {
                String txtIdCompra = request.getParameter("txtIdCompra");
                int idCompra = Integer.valueOf(txtIdCompra);
                DaoCompra daoCompra = new DaoCompra();
                int resp = daoCompra.cambiarEstadoCompra(idCompra, 6, usuario.getIdTrabajador());
                if (resp==1) {
                    request.setAttribute("mensaje", "La compra se rechazo correctamente");
                    request.getRequestDispatcher("gestionarProductos.jsp").forward(request, response);
                }
            }
            if (boton.equals("Aceptar Bicicleta")) {
                String txtIdBicicleta = request.getParameter("txtIdBicicleta");
                int idBicicleta = Integer.valueOf(txtIdBicicleta);
                DaoCompra daoCompra = new DaoCompra();
                int resp = daoCompra.cambiarEstadoBici(idBicicleta, 13,usuario.getIdTrabajador());
                if (resp==1) {
                    request.setAttribute("mensaje", "Se aprobo la bicicleta correctamente");
                    request.getRequestDispatcher("gestionarProductos.jsp").forward(request, response);
                }
            }
            if (boton.equals("Rechazar Bicicleta")) {
                String txtIdBicicleta = request.getParameter("txtIdBicicleta");
                int idBicicleta = Integer.valueOf(txtIdBicicleta);
                DaoCompra daoCompra = new DaoCompra();
                int resp = daoCompra.cambiarEstadoBici(idBicicleta, 6, usuario.getIdTrabajador());
                if (resp==1) {
                    request.setAttribute("mensaje", "Se rechazo la bicicleta correctamente");
                    request.getRequestDispatcher("gestionarProductos.jsp").forward(request, response);
                }
            }
            if (boton.equals("Poner En Venta")) {
                String txtIdProducto = request.getParameter("txtIdProd");
                String txtPrecio = request.getParameter("txtPrecProd");
                String txtDescuento = request.getParameter("txtDescuentoProd");
                int idProducto = Integer.valueOf(txtIdProducto);
                int precio = Integer.valueOf(txtPrecio);
                int descuento = Integer.valueOf(txtDescuento);
                DaoProducto daoProducto = new DaoProducto();
                int resp = daoProducto.ponerEnVenta(idProducto, precio, descuento);
                if (resp==1) {
                    request.setAttribute("mensaje", "Se puso el producto en venta");
                    request.getRequestDispatcher("listarProductosEnEspera.jsp").forward(request, response);
                }
                
            }
        } catch (Exception err) {
            request.setAttribute("mensaje", err);
            request.getRequestDispatcher("gestionarProductos.jsp").forward(request, response);
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
