/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Compra;
import clases.Cl_Conexion;
import clases.Cl_Detalle_Compra;
import java.sql.Array;
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
public class DaoCompra {

    Cl_Conexion conexion;

    public DaoCompra() {
        conexion = new Cl_Conexion();
    }

    public List<Cl_Compra> obtenerCompras() throws Exception {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Cl_Compra> listaCompra;
        String sql = "{call PKG_GESTION.OBTENER_COMPRAS(?)}";
        try {
            listaCompra = new ArrayList<>();
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cl_Compra compra = new Cl_Compra();
                compra.setIdCompra(rs.getInt(1));
                compra.setEstado(rs.getString(2));
                compra.setFecha(rs.getDate(3));
                compra.setTotal(rs.getInt(4));
                listaCompra.add(compra);
            }
            return listaCompra;
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

    public List<Cl_Detalle_Compra> obtenerDetalleCompra(int idCompra) throws Exception {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Cl_Detalle_Compra> listaDetalle;
        String sql = "{call PKG_GESTION.OBTENER_DETALLE_COMPRA(?,?)}";
        try {
            listaDetalle = new ArrayList<>();
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, idCompra);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(2);
            while (rs.next()) {
                Cl_Detalle_Compra detalleCompra = new Cl_Detalle_Compra();
                detalleCompra.setIdDetalleCompra(rs.getInt(1));
                detalleCompra.setNombre(rs.getString(2));
                detalleCompra.setModelo(rs.getString(3));
                detalleCompra.setPrecio(rs.getInt(4));
                detalleCompra.setCantidad(rs.getInt(5));
                detalleCompra.setProveedor(rs.getString(6));
                detalleCompra.setRutaFoto(rs.getString(7));
                String encode = Base64.getEncoder().encodeToString(imgToByteArray(detalleCompra.getRutaFoto()));
                detalleCompra.setImagen(encode);
                listaDetalle.add(detalleCompra);
            }
            return listaDetalle;
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

    public int cambiarEstadoCompra(int idCompra,int idEstado, int idTrabajador) throws Exception {
        CallableStatement cstmt = null;
        try {
            String sql = "{call PKG_GESTION.CAMBIAR_ESTADO_COMPRA(?,?,?)}";
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, idCompra);
            cstmt.setInt(2, idEstado);
            cstmt.setInt(3, idTrabajador);
            cstmt.execute();
            return 1;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            conexion.Conectar().close();
            if (cstmt!=null) {
                cstmt.close();
            }
        }
    }
    
    public int cambiarEstadoBici(int idBici,int idEstado, int idTrabajador) throws Exception {
        CallableStatement cstmt = null;
        try {
            String sql = "{call PKG_GESTION.CAMBIAR_ESTADO_BICI_ARMADA(?,?,?)}";
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, idBici);
            cstmt.setInt(2, idEstado);
            cstmt.setInt(3, idTrabajador);
            cstmt.execute();
            return 1;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
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

}
