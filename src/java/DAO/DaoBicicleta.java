/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Bicicleta_Armada;
import clases.Cl_Conexion;
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
public class DaoBicicleta {

    Cl_Conexion conexion;

    public DaoBicicleta() {
        conexion = new Cl_Conexion();
    }

    public int armarBicicleta(String modelo, String descripcion, int cantidad,String foto, int aro, int tipoProd, int idTrabajador) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call PKG_TECNICO.ARMAR_BICICLETA(?,?,?,?,?,?,?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setString(1, modelo);
            cstmt.setString(2, descripcion);
            cstmt.setInt(3, cantidad);
            cstmt.setString(4, foto);
            cstmt.setInt(5, aro);
            cstmt.setInt(6, tipoProd);
            cstmt.setInt(7, idTrabajador);
            cstmt.execute();
            return 1;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
        }
    }
    
    public List<Cl_Bicicleta_Armada> obtenerBicicletasArmadas() throws Exception{
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call PKG_GESTION.OBTENER_BICICLETAS_ARMADAS(?)}";
        List<Cl_Bicicleta_Armada> listaBicis = new ArrayList<>();
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {                
                Cl_Bicicleta_Armada biciArmada = new Cl_Bicicleta_Armada();
                biciArmada.setIdBicicleta(rs.getInt(1));
                biciArmada.setNombre(rs.getString(2));
                biciArmada.setModelo(rs.getString(3));
                biciArmada.setEstado(rs.getString(4));
                biciArmada.setRutaFoto(rs.getString(5));
                String encode = Base64.getEncoder().encodeToString(imgToByteArray(biciArmada.getRutaFoto()));
                biciArmada.setImagen(encode);
                biciArmada.setIdProducto(rs.getInt(6));
                listaBicis.add(biciArmada);
            }
            return listaBicis;
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
            if (rs!=null) {
                rs.close();
            }
        }
    }

    private static byte[] imgToByteArray(java.lang.String ruta) {
        wsUsuario.Service1 service = new wsUsuario.Service1();
        wsUsuario.IServicio port = service.getBasicHttpBindingIServicio();
        return port.imgToByteArray(ruta);
    }
    
    
    
}
