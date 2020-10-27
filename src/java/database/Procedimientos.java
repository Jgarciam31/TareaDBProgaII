/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Jose1
 */
public class Procedimientos {
      private Conexion conn;
    private Connection cn;
    private PreparedStatement prstmt = null;
    private ResultSet result = null;
    private String[][] usuario;
    private  StringBuffer sb = new StringBuffer();
    
    public Procedimientos(){
        conn= new Conexion();
        cn = conn.conectar();

    }
    public static String LeerCategorias= "call ListaCaterias()";
    public static String LeerMarcas= "call ListaMarcas()";
    public static String ObtenerProductoPorCodigo= "call ObtenerProductoPorCodigo($)";
    public static String MostraProducto= "call MostrarProducto()";
    public static String EliminarProducto= "call EliminarProducto($)";
    public static String AutualizarProducto= "call AutorizarProducto($)";
    public static String CrearProducto= "call CrearProducto($)";
    
     public String CrearProducto( String nombre,String precio,String peso,String comercio,String marca,String categaria){
        String sql = "INSERT INTO producto (`Nombre`, `Precio`, `Peso`, `comercio`, `marca`, `categoria`)   VALUES(?,?,?,?,?,?)";
        try{
             prstmt = cn.prepareStatement(sql); 
            prstmt.setString(1, nombre);
            prstmt.setString(2, precio);
           prstmt.setString(3, peso);
            prstmt.setString(4, comercio);
            prstmt.setString(5, marca);
            prstmt.setString(6, categaria);
          
            
             int resultado = prstmt.executeUpdate(); 
                if(resultado > 0){
                    return "1";
                }else{
                    return"0";
                }
       }catch(SQLException e){
            String error = e.getMessage();  
            if(error.indexOf("ORA-00001") != -1){
                return "0";
            }else{
                return "0";
            }
        }
}
      public StringBuffer MostrarProductos(){   
        String sql="SELECT * FROM producto;";
        try{
        prstmt = cn.prepareStatement(sql);     
            System.out.println("HOla------------------------------------------------------------------------------------------");
            System.out.println(prstmt);
        result = prstmt.executeQuery();            
        
            if (result!=null){
                while (result.next()){
                sb.append("<tr>");
                sb.append("<td >").append(result.getString("Nombre")).append("</td>");
                sb.append("<td >").append(result.getString("Precio")).append("</td>");
                sb.append("<td >").append(result.getString("Peso")).append("</td>");
                sb.append("<td >").append(result.getString("comercio")).append("</td>");
                sb.append("<td >").append(result.getString("marca")).append("</td>");
                sb.append("<td >").append(result.getString("categoria")).append("</td>");
                sb.append("</tr>");
                }
            }else{
                sb.append("error al consultar");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
      return sb;

    }
}
