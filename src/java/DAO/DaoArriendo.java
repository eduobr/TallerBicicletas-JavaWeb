/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Arriendo;
import clases.Cl_Conexion;
import clases.Cl_Producto;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Zeit
 */
public class DaoArriendo {
    Cl_Conexion conexion;

    public DaoArriendo() {
        conexion = new Cl_Conexion();
    }
    
    public int insertarArriento(String municipalidad,Date fechaInicio, Date fechaTermino, String tipoPago, int total, int deposito, int idPersona, int idProducto) throws Exception
    {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        //java.sql.Date sqlFechaInicio = java.sql.Date.valueOf(fechaInicio);
        String sql = "{call PKG_CLIENTE.INSERTAR_ARRIENDO(?,?,?,?,?,?,?,?)}";
        try {
            java.sql.Date fecInicio = new java.sql.Date(fechaInicio.getTime());
            java.sql.Date fecTermino = new java.sql.Date(fechaTermino.getTime());
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setString(1, municipalidad);
            cstmt.setDate(2, fecInicio);
            cstmt.setDate(3, fecTermino);
            cstmt.setString(4, tipoPago);
            cstmt.setInt(5, total);
            cstmt.setInt(6, deposito);
            cstmt.setInt(7, idPersona);
            cstmt.setInt(8, idProducto);
            cstmt.execute();
            return 1;
        }catch(Exception err){
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
    
    public List<Cl_Arriendo> listarArriendos(int idPersona) throws Exception{
        List<Cl_Arriendo> listaArriendos;
        listaArriendos = new ArrayList<>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call PKG_CLIENTE.OBTENER_ARRIENDOS(?,?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, idPersona);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(2);
            while (rs.next()) {                
                Cl_Arriendo arriendo = new Cl_Arriendo();
                arriendo.setIdArriendo(rs.getInt(1));
                arriendo.setMunicipalidad(rs.getString(2));
                arriendo.setFechaInicio(rs.getDate(3));
                arriendo.setFechaTermino(rs.getDate(4));
                arriendo.setTipoPago(rs.getString(5));
                arriendo.setTotal(rs.getInt(6));
                arriendo.setDepositoGarantia(rs.getInt(7));
                arriendo.setIdPersona(rs.getInt(8));
                arriendo.setIdProducto(rs.getInt(9));
                listaArriendos.add(arriendo);
            }
            return listaArriendos;
            
        } catch (Exception err) {
            throw new Exception(err);
        }finally{
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public List<Cl_Producto> obtenerBicicleta() throws Exception{
        List<Cl_Producto> listaProductos;
        listaProductos = new ArrayList<>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            String sql = "{call PKG_CLIENTE.OBTENER_BICICLETA_ARRIENDO(?)}";
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
             while (rs.next()) { 
                 Cl_Producto producto = new Cl_Producto();
                 producto.setIdProducto(rs.getInt(1));
                 producto.setModelo(rs.getString(2));
                 producto.setRutaFoto(rs.getString(3));
                 listaProductos.add(producto);
             }
             return listaProductos;
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
}
