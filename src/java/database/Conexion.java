/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion {
    
    private String url="jdbc:mysql://localhost:3306/tienda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String usuario="root";
    private String clave=""; 
    private Connection conexion=null;


    public Conexion(){
        
    }
    
    public Conexion(String usuario, String clave){
        this.usuario = usuario;
        this.clave = clave;
    }
    
    public Connection conectar(){   
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection( url, usuario,clave);
        }  catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {   
           ex.printStackTrace();
        }   
        return conexion;
    }
    
    public void desconectar(){
        
        try{
            conexion.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public ResultSet ejecutar(String query){
        
        try{
            
            PreparedStatement prepare = conexion.prepareStatement(query);
            ResultSet dataset = prepare.executeQuery();
            
            if (dataset == null)
                return null;
            
            
            return dataset;
            
        }catch(Exception e){
            
            e.printStackTrace();
            return null;
        }
    }
}
