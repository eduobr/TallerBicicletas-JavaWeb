/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Conexion;
import clases.Cl_Despacho;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Zeit
 */
public class DaoDespacho {
    Cl_Conexion conexion;

    public DaoDespacho() {
        conexion = new Cl_Conexion();
    }
    
    public List<Cl_Despacho> obtenerTrazaDespacho(int idPersona) throws Exception{
        CallableStatement cstmt = null;
        ResultSet rs = null;
        //java.sql.Date sqlFechaInicio = java.sql.Date.valueOf(fechaInicio);
        String sql = "{call PKG_DESPACHO.TRAZA_DESPACHO(?,?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, idPersona);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(2);
            List<Cl_Despacho> listaDespacho=new ArrayList<>();
            while (rs.next()) {
                Cl_Despacho despacho = new Cl_Despacho();
                despacho.setTipoProducto(rs.getString(1));
                despacho.setModelo(rs.getString(2));
                despacho.setFechaPedido(rs.getString(3));
                despacho.setFechaDespacho(rs.getString(4));
                despacho.setFechaRecepcion(rs.getString(5));
                listaDespacho.add(despacho);
            }
            return listaDespacho;
        }catch(Exception ex){
            throw new Exception(ex);
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
