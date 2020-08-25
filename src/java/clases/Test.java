/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import DAO.DaoArriendo;
import DAO.DaoBicicleta;
import DAO.DaoCliente;
import DAO.DaoPersona;
import DAO.DaoProducto;
import DAO.DaoReparacion;
import DAO.DaoTrabajador;
import DAO.DaoUsuario;
import DAO.DaoVenta;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Zeit
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //Cl_Conexion conexion = new Cl_Conexion();
        //conexion.Conectar();
        try {
            //Ingresar Trabajador
            /*DaoTrabajador dt = new DaoTrabajador();
            Cl_Persona p = new Cl_Persona();
            p.setRut("18763871-3");
            p.setNombre("Jordan");
            p.setApellido("Cid");
            p.setEdad(25);
            p.setDireccion("Villa Primavera");
            p.setCorreo("jo.cid@alumnos.duoc.cl");
            p.setIdComuna(1);
            Cl_Contrato contrato = new Cl_Contrato();
            contrato.setRutaAfp("cerAfp");
            contrato.setRutaSalud("cerSalud");
            contrato.setRutaCarnet("cerCarnet");
            contrato.setRutaCerNacimiento("cerNacimiento");
            contrato.setRutaCerResidencia("cerResidencia");
            Cl_Usuario usuario = new Cl_Usuario();
            usuario.setIdTipoUsuario(7);
            usuario.setUser("jordan");
            usuario.setPass("cid");*/
            
            //dt.ingresarTrabajador(null, null, null, null)
            
            //Listar Trabajadores
            /*
            DaoTrabajador dt = new DaoTrabajador();
            List<Cl_Trabajador> lista = dt.listarTrabajadores();
            for (Cl_Trabajador item : lista) {
                System.out.println(item.toString());
            }*/
            
            /*DaoArriendo daoArriendo = new DaoArriendo();
            List<Cl_Arriendo> listaArriendo = daoArriendo.listarArriendos(4);
            for(Cl_Arriendo item:listaArriendo)
            {
                System.out.println(item.toString());
            }*/
            /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String stringFechaInicio = "20/05/2019";
            Date fechaInicio = sdf.parse(stringFechaInicio);
            String stringFechaTermino = "30/05/2019";
            Date fechaTermino = sdf.parse(stringFechaTermino);
            int resp= daoArriendo.insertarArriento("La Florida", fechaInicio, fechaTermino, "Efectivo", 30000, 20000, 4, 1);
            if (resp==1) {
                System.out.println("El arriendo se inserto correctamente");
            }*/
            
            /*DaoProducto daoProducto = new DaoProducto();
            List<Cl_Producto> listaProd=daoProducto.obtenerProductos();
            for (Cl_Producto item : listaProd) {
                System.out.println(item.toString());
            }*/
            /*DaoUsuario daoUsuario = new DaoUsuario();
            Cl_Usuario ususario = daoUsuario.obtenerUsuario("cliente", "cliente123");
            System.out.println(ususario.toString());*/
            
            /*DaoVenta daoVenta = new DaoVenta();
            int resp = daoVenta.insertarVenta(4, 5000);
            System.out.println(resp);*/
            
            /*DaoPersona daoPersona = new DaoPersona();
            List<Cl_Comuna> listaComunas = daoPersona.obtenerComunas();
            for (Cl_Comuna item : listaComunas) {
                System.out.println(item.toString());
            }*/
            //DaoCliente daoCliente = new DaoCliente();
            //daoCliente.ingresarCliente(null, null)
           
            /*DaoBicicleta daoBicicleta = new DaoBicicleta();
            List<Cl_Bicicleta_Armada> listaBicis = daoBicicleta.obtenerBicicletasArmadas();
            for (Cl_Bicicleta_Armada item : listaBicis) {
                System.out.println(item.toString());
            }*/
            DaoReparacion daoReparacion = new DaoReparacion();
            //daoReparacion.enviarCorreo("pruebacorr2019@gmail.com", "correo de prueba");
            daoReparacion.enviarCorreo("ed.obreque@alumnos.duoc.cl", "correo de prueba");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
