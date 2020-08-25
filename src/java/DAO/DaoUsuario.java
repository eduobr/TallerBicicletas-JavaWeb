/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Conexion;
import clases.Cl_Usuario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Zeit
 */
public class DaoUsuario {

    Cl_Conexion conexion;

    public DaoUsuario() {
        conexion = new Cl_Conexion();
    }

    public Cl_Usuario obtenerUsuario(String usuario, String password) throws Exception {

        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call PKG_USUARIO.OBTENER_USUARIO(?,?,?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setString(1, usuario);
            cstmt.setString(2, password);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(3);
            Cl_Usuario user = new Cl_Usuario();
            while (rs.next()) {
                user.setIdUsuario(rs.getInt(1));
                user.setUser(rs.getString(2));
                user.setIdTipoUsuario(rs.getInt(3));
                user.setIdTrabajador(rs.getInt(4));
                user.setIdPersona(rs.getInt(5));
            }
            return user;

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
    
    public int darBajaUsuario(int id) throws Exception{
        CallableStatement cstmt = null;
        String sql = "{call PKG_USUARIO.DAR_BAJA(?)}";
        try{
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, id);
            cstmt.execute();
            return 1; 
        }catch(Exception err){
            throw new Exception(err);
        }finally{
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
        }
    }

}
