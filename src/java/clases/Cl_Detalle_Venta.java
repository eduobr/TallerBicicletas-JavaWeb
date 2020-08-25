/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Zeit
 */
public class Cl_Detalle_Venta {

    private byte[] imagen;
    private String nombre;
    private String modelo;
    private String estado;
    private int cantidad;
    private int total;
    private int idProducto;
    private int idDetalleVenta;

    public Cl_Detalle_Venta() {
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    @Override
    public String toString() {
        return "Cl_Detalle_Venta{" + "imagen=" + imagen + ", nombre=" + nombre + ", modelo=" + modelo + ", estado=" + estado + ", cantidad=" + cantidad + ", total=" + total + ", idProducto=" + idProducto + ", idDetalleVenta=" + idDetalleVenta + '}';
    }
    
    
}
