/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Zeit
 */
public class Cl_Conexion {
    private Connection conexion;
    String cadena = "jdbc:Oracle:thin:@localhost:1521:XE";

    public Connection Conectar() throws Exception {
        try {
            if (conexion==null) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conexion = DriverManager.getConnection(cadena, "MASTERBIKES","MASTER123");
                if (conexion!=null) {
                    System.out.println("Conexion Exitosa");
                }else{
                    System.out.println("No se pudo Conectar");
                }
                
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return conexion;
    }
    
}
