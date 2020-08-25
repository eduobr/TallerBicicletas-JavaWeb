/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.DaoVenta;
import clases.Cl_Producto;
import clases.Cl_Usuario;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "ServletCarrito", urlPatterns = {"/ServletCarrito"})
public class ServletCarrito extends HttpServlet {

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
        String boton = request.getParameter("btnAccion");
        if (boton.equals("Agregar al Carrito")) {
            HttpSession sesion = request.getSession();

            String txtIdProd = request.getParameter("txtIdProd");
            String txtNomProd = request.getParameter("txtNomProd");
            String txtModelo = request.getParameter("txtModeloProd");
            String txtDescProd = request.getParameter("txtDescProd");
            String txtPrecProd = request.getParameter("txtPrecProd");
            String txtDescuentoProd = request.getParameter("txtDescuentoProd");
            String txtFoto = request.getParameter("txtFoto");
            int precio = Integer.valueOf(txtPrecProd);
            int descuento = Integer.valueOf(txtDescuentoProd);
            int idProd = Integer.valueOf(txtIdProd);

            Cl_Producto producto = new Cl_Producto();
            producto.setIdProducto(idProd);
            producto.setNombre(txtNomProd);
            producto.setDescripcion(txtDescProd);
            producto.setModelo(txtModelo);
            producto.setDescripcion(txtDescProd);
            producto.setPrecio(precio);
            producto.setDescuento(descuento);
            producto.setImagen(txtFoto);
            List<Cl_Producto> listaProd = (List<Cl_Producto>) sesion.getAttribute("carrito");

            if (listaProd == null) {
                listaProd = new ArrayList<Cl_Producto>();
                sesion.setAttribute("carrito", listaProd);
            }
            for (Cl_Producto prodTemp : listaProd) {
                if (prodTemp.getIdProducto() == idProd) {
                    request.setAttribute("mensaje", "El producto ya se encuentra agregado");
                    request.getRequestDispatcher("carrito-compras.jsp").forward(request, response);
                    return;
                }
            }
            if (listaProd != null) {
                listaProd.add(producto);
            }
            //request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
            request.getRequestDispatcher("carrito-compras.jsp").forward(request, response);
        }
        if (boton.equals("Comprar Producto")) {
            HttpSession sesion = request.getSession();
            List<Cl_Producto> listaProd = (List<Cl_Producto>) sesion.getAttribute("carrito");
            List<Cl_Producto> listaModificada = new ArrayList<Cl_Producto>();
            int total = 0;
            for (Cl_Producto prodTemp : listaProd) {
                String txtCantidad = request.getParameter("txtCantidad" + prodTemp.getIdProducto());
                int cantidad = Integer.valueOf(txtCantidad);
                prodTemp.setCantidad(cantidad);
                listaModificada.add(prodTemp);
                total += (prodTemp.getCantidad() * prodTemp.getPrecio()) - ((prodTemp.getCantidad() * prodTemp.getPrecio() * (prodTemp.getDescuento() / 100)));
            }
            listaProd = listaModificada;
            sesion.setAttribute("carrito", listaProd);
            request.setAttribute("total", total);
            request.getRequestDispatcher("confirmacion-compra.jsp").forward(request, response);
        }
        if (boton.equals("Eliminar Producto")) {
            HttpSession sesion = request.getSession();
            String txtIdProd = request.getParameter("txtIdProd");
            int idProducto = Integer.valueOf(txtIdProd);
            List<Cl_Producto> listaProd = (List<Cl_Producto>) sesion.getAttribute("carrito");
            List<Cl_Producto> listaModificada = new ArrayList<Cl_Producto>();
            for (Cl_Producto prodTemp : listaProd) {
                if (prodTemp.getIdProducto() == idProducto) {
                    listaProd.remove(prodTemp);
                    request.setAttribute("mensaje", "Se elimino el producto del carrito");
                    if (listaProd.size() == 0) {
                        sesion.setAttribute("carrito", null);
                    }
                    request.getRequestDispatcher("carrito-compras.jsp").forward(request, response);
                }
            }
            sesion.setAttribute("carrito", listaProd);
        }
        if (boton.equals("Confirmar Compra")) {
            HttpSession sesion = request.getSession();
            List<Cl_Producto> listaProd = (List<Cl_Producto>) sesion.getAttribute("carrito");
            Cl_Usuario usuario = (Cl_Usuario) sesion.getAttribute("usuario");
            boolean correcto = false;
            try {
                DaoVenta daoVenta = new DaoVenta();
                int total = 0;
                String txtTotal = request.getParameter("txtTotal");
                total = Integer.valueOf(txtTotal);
                int idVenta = daoVenta.insertarVenta(usuario.getIdPersona(), total);
                if (idVenta > 0) {
                    correcto = true;
                }
                for (Cl_Producto prodTemp : listaProd) {
                    DaoVenta daoDetalle = new DaoVenta();
                    int totalPrecio = prodTemp.getPrecio()*prodTemp.getCantidad();
                    int resp = daoDetalle.insertarDetalleVenta(prodTemp.getCantidad(), totalPrecio, idVenta, prodTemp.getIdProducto());
                    if (resp == 1) {
                        correcto = true;
                    } else {
                        correcto = false;
                    }
                }
                if (correcto) {
                    request.setAttribute("mensaje", "La venta se ingreso correctamente");
                    request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("mensaje", e.getMessage());
                request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
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
