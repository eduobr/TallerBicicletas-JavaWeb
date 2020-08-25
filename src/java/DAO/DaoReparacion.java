/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import clases.Cl_Conexion;
import clases.Cl_Reparacion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Zeit
 */
public class DaoReparacion {

    Cl_Conexion conexion;

    public DaoReparacion() {
        conexion = new Cl_Conexion();
    }

    public int ingesarReparacion(String descripcion, Date fechaEntrega, String rutaProducto, int idPersona) throws Exception {
        CallableStatement cstmt = null;
        //java.sql.Date sqlFechaInicio = java.sql.Date.valueOf(fechaInicio);
        String sql = "{call PKG_CLIENTE.INSERTAR_REPARACION(?,?,?,?)}";
        try {
            java.sql.Date fecEntrega = new java.sql.Date(fechaEntrega.getTime());
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setString(1, descripcion);
            cstmt.setDate(2, fecEntrega);
            cstmt.setString(3, rutaProducto);
            cstmt.setInt(4, idPersona);
            cstmt.execute();
            return 1;
        } catch (Exception err) {
            throw new Exception(err);
        } finally {
            conexion.Conectar().close();
            if (cstmt != null) {
                cstmt.close();
            }
        }
    }

    public List<Cl_Reparacion> obtenerReparaciones() throws Exception {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call PKG_TECNICO.OBTENER_REPARACIONES(?)}";
        List<Cl_Reparacion> listaReparacion = new ArrayList<>();
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cl_Reparacion reparacion = new Cl_Reparacion();
                reparacion.setIdReparacion(rs.getInt(1));
                reparacion.setRut(rs.getString(2));
                reparacion.setNombre(rs.getString(3));
                reparacion.setDescripcion(rs.getString(4));
                reparacion.setFechaPedido(rs.getDate(5));
                reparacion.setFechaEntrega(rs.getDate(6));
                reparacion.setEstado(rs.getString(7));
                reparacion.setRutaFoto(rs.getString(8));
                String encode = Base64.getEncoder().encodeToString(imgToByteArray(reparacion.getRutaFoto()));
                reparacion.setImagen(encode);
                listaReparacion.add(reparacion);
            }
            return listaReparacion;
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

    public List<Cl_Reparacion> obtenerReparacionesCli(int idPersona) throws Exception {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call PKG_CLIENTE.OBTENER_REPARACIONES_CLIENTE(?,?)}";
        List<Cl_Reparacion> listaReparacion = new ArrayList<>();
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, idPersona);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(2);
            while (rs.next()) {
                Cl_Reparacion reparacion = new Cl_Reparacion();
                reparacion.setIdReparacion(rs.getInt(1));
                reparacion.setRut(rs.getString(2));
                reparacion.setNombre(rs.getString(3));
                reparacion.setDescripcion(rs.getString(4));
                reparacion.setFechaPedido(rs.getDate(5));
                reparacion.setFechaEntrega(rs.getDate(6));
                reparacion.setEstado(rs.getString(7));
                reparacion.setRutaFoto(rs.getString(8));
                String encode = Base64.getEncoder().encodeToString(imgToByteArray(reparacion.getRutaFoto()));
                reparacion.setImagen(encode);
                listaReparacion.add(reparacion);
            }
            return listaReparacion;
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

    public int ingresarRespuesta(int idReparacion, String respuesta, String descripcion) throws Exception {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call PKG_TECNICO.RESPONDER_REPARACION(?,?,?)}";
        try {
            cstmt = conexion.Conectar().prepareCall(sql);
            cstmt.setInt(1, idReparacion);
            cstmt.setString(2, respuesta);
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
            cstmt.executeUpdate();
            String destinatario = cstmt.getString(3);
            enviarCorreo(destinatario, descripcion);
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

    private static byte[] imgToByteArray(java.lang.String ruta) {
        wsUsuario.Service1 service = new wsUsuario.Service1();
        wsUsuario.IServicio port = service.getBasicHttpBindingIServicio();
        return port.imgToByteArray(ruta);
    }

    public int enviarCorreo(String destinatario, String descripcion) throws NoSuchProviderException, MessagingException {
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        propiedad.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "pruebacorr2019@gmail.com";
        String contrasena = "prueba2019";
        String receptor = destinatario;
        String asunto = "Respuesta Reparacion";
        String mensaje = descripcion;

        MimeMessage mail = new MimeMessage(sesion);
        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mail.setSubject(asunto);
            mail.setText(mensaje);

            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia, contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transportar.close();

            return 1;

        } catch (AddressException ex) {
            Logger.getLogger(DaoReparacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(DaoReparacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
