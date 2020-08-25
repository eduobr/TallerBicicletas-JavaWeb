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
public class Cl_Producto {

    private int idProducto;

    private String nombre;

    private String modelo;

    private String descripcion;
    
    private String estado;

    private String rutaFoto;

    private int precio;

    private int descuento;

    private int stock;

    private int cantidad;

    private String imagen;

    private int aro;

    private int idProveedor;

    public Cl_Producto() {
    }

    public Cl_Producto(int idProducto, String nombre, String modelo, String descripcion, String rutaFoto, int precio, int descuento, int stock, int cantidad, String imagen, int aro, int idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.rutaFoto = rutaFoto;
        this.precio = precio;
        this.descuento = descuento;
        this.stock = stock;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.aro = aro;
        this.idProveedor = idProveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getAro() {
        return aro;
    }

    public void setAro(int aro) {
        this.aro = aro;
    }

    @Override
    public String toString() {
        return "Cl_Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", modelo=" + modelo + ", descripcion=" + descripcion + ", rutaFoto=" + rutaFoto + ", precio=" + precio + ", descuento=" + descuento + ", stock=" + stock + ", cantidad=" + cantidad + ", imagen=" + imagen + ", aro=" + aro + ", idProveedor=" + idProveedor + '}';
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
