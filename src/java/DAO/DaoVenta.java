/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Zeit
 */
public class DaoVenta {

    Cl_Conexion conexion;

    public DaoVenta() {
        conexion = new Cl_Conexion();
    }

    public int insertarVenta(int idPersona, int total) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call PKG_VENTA_CLIENTE.INSERTAR_VENTA_CLIENTE(?,?,?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1,idPersona);
            cstmt.setInt(2,total);
            cstmt.registerOutParameter(3, OracleTypes.INTEGER);
            cstmt.execute();
            int idVenta = cstmt.getInt(3);
            return idVenta;
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
        }
    }
    
    public int insertarDetalleVenta(int cantidad, int total, int idVenta, int idProducto) throws Exception{
        CallableStatement cstmt = null;
        String sql = "{call PKG_VENTA_CLIENTE.INSERTAR_DET_VENTA_CLI(?,?,?,?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1,cantidad);
            cstmt.setInt(2,total);
            cstmt.setInt(3,idVenta);
            cstmt.setInt(4,idProducto);
            cstmt.execute();
            return 1;
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
        }
    }

}
