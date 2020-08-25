/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Conexion;
import clases.Cl_Contrato;
import clases.Cl_Persona;
import clases.Cl_Tipo_Trabajador;
import clases.Cl_Trabajador;
import clases.Cl_Usuario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Zeit
 */
public class DaoTrabajador {

    Cl_Conexion conexion;

    public DaoTrabajador() throws Exception {
        conexion = new Cl_Conexion();
    }

    public int ingresarTrabajador(Cl_Persona persona, Cl_Contrato contrato, Cl_Trabajador trabajador, Cl_Usuario usuario) throws Exception {
        CallableStatement cstmt = null;
        try {
            String sql = "{call PKG_TRABAJADORES.INGRESAR_TRABAJADOR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            cstmt = conexion.Conectar().prepareCall(sql);

            cstmt.setString(1, persona.getRut());
            cstmt.setString(2, persona.getNombre());
            cstmt.setString(3, persona.getApellido());
            cstmt.setInt(4, persona.getEdad());
            cstmt.setString(5, persona.getDireccion());
            cstmt.setString(6, persona.getCorreo());
            cstmt.setInt(7, persona.getIdComuna());
            cstmt.setInt(8, trabajador.getSueldo());

            cstmt.setString(9, contrato.getRutaAfp());
            cstmt.setString(10, contrato.getRutaSalud());
            cstmt.setString(11, contrato.getRutaCarnet());
            cstmt.setString(12, contrato.getRutaCerNacimiento());
            cstmt.setString(13, contrato.getRutaCerResidencia());

            cstmt.setInt(14, usuario.getIdTipoUsuario());
            cstmt.setString(15, usuario.getUser());
            cstmt.setString(16, usuario.getPass());

            cstmt.execute();

            //OracleCommand cmd = operaciones.execSP("PKG_TRABAJADORES.INGRESAR_TRABAJADOR", parametros);
            return 1;

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (cstmt != null) {
                cstmt.close();
            }
        }
    }

    public List<Cl_Trabajador> listarTrabajadores() throws Exception {

        List<Cl_Trabajador> listaTrabajadores;
        listaTrabajadores = new ArrayList<>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call PKG_TRABAJADORES.OBTENER_TRABAJADORES(?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {

                Cl_Trabajador trabajador = new Cl_Trabajador();
                trabajador.setIdTrabajador(rs.getInt(1));
                trabajador.setRut(rs.getString(2));
                trabajador.setNombre(rs.getString(3));

                trabajador.setCargo(rs.getString(4));
                trabajador.setDireccion(rs.getString(5));
                trabajador.setCorreo(rs.getString(6));
                Date fecIngreso = new SimpleDateFormat("mm/dd/yyyy").parse(rs.getString(7));
                trabajador.setFechaIngreso(fecIngreso);
                //System.out.println(rs.getDate(7));
                trabajador.setSueldo(rs.getInt(8));
                trabajador.setEstado(rs.getString(9));
                trabajador.setIdUsuario(rs.getInt(10));
                listaTrabajadores.add(trabajador);

            }

            return listaTrabajadores;
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

    public List<Cl_Tipo_Trabajador> obtenerTipoTrabajador() throws Exception {
        List<Cl_Trabajador> listaTrabajadores;
        listaTrabajadores = new ArrayList<>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Cl_Tipo_Trabajador> listaTipo = new ArrayList<>();
        String sql = "{call PKG_TRABAJADORES.OBTENER_TIPO_TRABAJO(?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cl_Tipo_Trabajador tipoTrabajador = new Cl_Tipo_Trabajador();
                tipoTrabajador.setIdTipoTrabajo(rs.getInt(1));
                tipoTrabajador.setTipoTrabajo(rs.getString(2));
                listaTipo.add(tipoTrabajador);
            }
            return listaTipo;
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

}
