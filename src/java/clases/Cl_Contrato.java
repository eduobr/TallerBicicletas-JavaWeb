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
public class Cl_Contrato {

    private int idContrato;
    private String rutaAfp;
    private String rutaSalud;
    private String rutaCarnet;
    private String rutaCerNacimiento;
    private String rutaCerResidencia;

    public Cl_Contrato() {
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getRutaAfp() {
        return rutaAfp;
    }

    public void setRutaAfp(String rutaAfp) {
        this.rutaAfp = rutaAfp;
    }

    public String getRutaSalud() {
        return rutaSalud;
    }

    public void setRutaSalud(String rutaSalud) {
        this.rutaSalud = rutaSalud;
    }

    public String getRutaCarnet() {
        return rutaCarnet;
    }

    public void setRutaCarnet(String rutaCarnet) {
        this.rutaCarnet = rutaCarnet;
    }

    public String getRutaCerNacimiento() {
        return rutaCerNacimiento;
    }

    public void setRutaCerNacimiento(String rutaCerNacimiento) {
        this.rutaCerNacimiento = rutaCerNacimiento;
    }

    public String getRutaCerResidencia() {
        return rutaCerResidencia;
    }

    public void setRutaCerResidencia(String rutaCerResidencia) {
        this.rutaCerResidencia = rutaCerResidencia;
    }

}
