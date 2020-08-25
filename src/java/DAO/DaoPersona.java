/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Comuna;
import clases.Cl_Conexion;
import clases.Cl_Persona;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Zeit
 */
public class DaoPersona {
    Cl_Conexion conexion;

    public DaoPersona() {
        conexion = new Cl_Conexion();
    }
    
    public Cl_Persona obtenerPersonaIdUsuario(int idUsuario) throws Exception{
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call PKG_USUARIO.OBTENER_PERSONA(?,?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, idUsuario);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(2);
            Cl_Persona persona = new Cl_Persona();
            while (rs.next()) {
                persona.setIdPersona(rs.getInt(1));
                persona.setRut(rs.getString(2));
                persona.setNombre(rs.getString(3));
                persona.setApellido(rs.getString(4));
                persona.setEdad(rs.getInt(5));
                persona.setDireccion(rs.getString(6));
                persona.setCorreo(rs.getString(7));
                persona.setComuna(rs.getString(9));
            }
            return persona;
        }catch (Exception ex){
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
    
    public List<Cl_Comuna> obtenerComunas() throws Exception{
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Cl_Comuna> listaComuna = new ArrayList<>();
        String sql = "{call PKG_PRINCIPAL.OBTENER_COMUNAS(?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cl_Comuna comuna = new Cl_Comuna();
                comuna.setIdComuna(rs.getInt(1));
                comuna.setComuna(rs.getString(2));
                listaComuna.add(comuna);
            }
            return listaComuna;
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
