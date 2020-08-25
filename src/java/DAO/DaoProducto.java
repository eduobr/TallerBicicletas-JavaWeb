/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Conexion;
import clases.Cl_Producto;
import clases.Cl_Tipo_Producto;
import clases.Cl_Tipo_Trabajador;
import clases.Cl_Trabajador;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Zeit
 */
public class DaoProducto {

    Cl_Conexion conexion;

    public DaoProducto() {
        conexion = new Cl_Conexion();
    }

    public List<Cl_Producto> obtenerProductos() throws Exception {
        List<Cl_Producto> listaProductos;
        listaProductos = new ArrayList<>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            String sql = "{call PKG_PRODUCTOS.OBTENER_PRODUCTOS(?)}";
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cl_Producto producto = new Cl_Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setModelo(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setRutaFoto(rs.getString(5));
                String encode = Base64.getEncoder().encodeToString(imgToByteArray(producto.getRutaFoto()));
                producto.setImagen(encode);
                producto.setPrecio(rs.getInt(6));
                producto.setDescuento(rs.getInt(7));
                producto.setCantidad(rs.getInt(8));
                producto.setAro(rs.getInt(9));
                producto.setStock(obtenerStock(producto.getIdProducto()));
                listaProductos.add(producto);
            }
            return listaProductos;
        } catch (Exception err) {
            throw new Exception(err);
        } finally {
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public List<Cl_Tipo_Producto> obtenerTipoProdTecnico() throws Exception {
        List<Cl_Tipo_Producto> listaTipoProd;
        listaTipoProd = new ArrayList<>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call PKG_PRODUCTOS.OBTENER_TIPO_PROD_TECNICO(?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cl_Tipo_Producto tipoProducto = new Cl_Tipo_Producto();
                tipoProducto.setIdTipoProducto(rs.getInt(1));
                tipoProducto.setTipoProducto(rs.getString(2));
                listaTipoProd.add(tipoProducto);
            }
            return listaTipoProd;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public List<Cl_Producto> obtenerProductosEnEspera() throws Exception {
        List<Cl_Producto> listaProductos;
        listaProductos = new ArrayList<>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            String sql = "{call PKG_PRODUCTOS.OBTENER_PRODUCTOS_EN_ESPERA(?)}";
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cl_Producto producto = new Cl_Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setModelo(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setRutaFoto(rs.getString(5));
                String encode = Base64.getEncoder().encodeToString(imgToByteArray(producto.getRutaFoto()));
                producto.setImagen(encode);
                producto.setPrecio(rs.getInt(6));
                producto.setDescuento(rs.getInt(7));
                producto.setAro(rs.getInt(8));
                producto.setEstado(rs.getString(9));
                listaProductos.add(producto);
            }
            return listaProductos;
        } catch (Exception err) {
            throw new Exception(err);
        } finally {
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public int ponerEnVenta(int idProducto, int precio, int descuento) throws Exception{
        CallableStatement cstmt = null;
        try {
            String sql = "{call PKG_GESTION.PONER_EN_VENTA(?,?,?)}";
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, idProducto);
            cstmt.setInt(2, precio);
            cstmt.setInt(3, descuento);
            cstmt.execute();
            return 1;
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            conexion.Conectar().close();
            if (cstmt!=null) {
                cstmt.close();
            }
        }
    }

    private static byte[] imgToByteArray(java.lang.String ruta) {
        wsUsuario.Service1 service = new wsUsuario.Service1();
        wsUsuario.IServicio port = service.getBasicHttpBindingIServicio();
        return port.imgToByteArray(ruta);
    }

    private static Integer obtenerStock(java.lang.Integer idProducto) {
        wsUsuario.Service1 service = new wsUsuario.Service1();
        wsUsuario.IServicio port = service.getBasicHttpBindingIServicio();
        return port.obtenerStock(idProducto);
    }

}
