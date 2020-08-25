/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Date;



/**
 *
 * @author Zeit
 */
public class Cl_Reparacion {
    private int idReparacion;
    private String rut;
    private String nombre;
    private String descripcion;
    private Date fechaPedido;
    private Date fechaEntrega;
    private String estado;
    private String rutaFoto;
    private String imagen;
    
    public Cl_Reparacion() {
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public int getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(int idReparacion) {
        this.idReparacion = idReparacion;
    }
    
    @Override
    public String toString() {
        return "Cl_Reparacion{" + "idReparacion=" + idReparacion + ", rut=" + rut + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fechaPedido + ", fechaEntrega=" + fechaEntrega + ", estado=" + estado + ", rutaFoto=" + rutaFoto + '}';
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
