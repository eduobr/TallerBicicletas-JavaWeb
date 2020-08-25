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
public class Cl_Tipo_Trabajador {
    private int idTipoTrabajo;
    private String tipoTrabajo;

    public Cl_Tipo_Trabajador() {
    }

    public int getIdTipoTrabajo() {
        return idTipoTrabajo;
    }

    public void setIdTipoTrabajo(int idTipoTrabajo) {
        this.idTipoTrabajo = idTipoTrabajo;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    @Override
    public String toString() {
        return "Cl_Tipo_Trabajador{" + "idTipoTrabajo=" + idTipoTrabajo + ", tipoTrabajo=" + tipoTrabajo + '}';
    }
}
