/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Conexion;
import clases.Cl_Persona;
import clases.Cl_Usuario;
import java.sql.CallableStatement;

/**
 *
 * @author Zeit
 */
public class DaoCliente {
    Cl_Conexion conexion;
    
    public DaoCliente() {
        conexion = new Cl_Conexion();
    }
    
    public int ingresarCliente(Cl_Persona persona,Cl_Usuario usuario) throws Exception{
           CallableStatement cstmt = null;
         try {
            Object[] parametros = new Object[16];
            String sql = "{call PKG_CLIENTE.REGISTRAR_CLIENTE(?,?,?,?,?,?,?,?,?)}";
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setString(1, persona.getRut());
            cstmt.setString(2, persona.getNombre());
            cstmt.setString(3, persona.getApellido());
            cstmt.setInt(4, persona.getEdad());
            cstmt.setString(5, persona.getDireccion());
            cstmt.setString(6, persona.getCorreo());
            cstmt.setInt(7, persona.getIdComuna());
            cstmt.setString(8, usuario.getUser());
            cstmt.setString(9, usuario.getPass());

            cstmt.execute();

            //OracleCommand cmd = operaciones.execSP("PKG_TRABAJADORES.INGRESAR_TRABAJADOR", parametros);
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
