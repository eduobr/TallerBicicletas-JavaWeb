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
public class Cl_Arriendo {
    private int idArriendo;
    private String municipalidad;
    private Date fechaInicio;
    private Date fechaTermino;
    private String tipoPago;
    private int total;
    private int depositoGarantia;
    private int idPersona;
    private int idProducto;

    public Cl_Arriendo() {
    }

    public Cl_Arriendo(int idArriendo, String municipalidad, Date fechaInicio, Date fechaTermino, String tipoPago, int total, int depositoGarantia, int idPersona, int idProducto) {
        this.idArriendo = idArriendo;
        this.municipalidad = municipalidad;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.tipoPago = tipoPago;
        this.total = total;
        this.depositoGarantia = depositoGarantia;
        this.idPersona = idPersona;
        this.idProducto = idProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdArriendo() {
        return idArriendo;
    }

    public void setIdArriendo(int idArriendo) {
        this.idArriendo = idArriendo;
    }

    public String getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(String municipalidad) {
        this.municipalidad = municipalidad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDepositoGarantia() {
        return depositoGarantia;
    }

    public void setDepositoGarantia(int depositoGarantia) {
        this.depositoGarantia = depositoGarantia;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "Cl_Arriendo{" + "idArriendo=" + idArriendo + ", municipalidad=" + municipalidad + ", fechaInicio=" + fechaInicio + ", fechaTermino=" + fechaTermino + ", tipoPago=" + tipoPago + ", total=" + total + ", depositoGarantia=" + depositoGarantia + ", idPersona=" + idPersona + ", idProducto=" + idProducto + '}';
    }
    
}
