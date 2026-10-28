
package com.mycompany.infinityforce;
 
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

    
public class Conexion {
     String bd="Infinity Force";
     String url="jbdc:mysql://127.0.0.1:3306/";
     String user=("root");
     String password="";
     String driver="com.mysql.cj.jdbc.Driver";
     Connection cx;

     
     public Conexion(){
           
     }
         public Connection conectar() throws SQLException{
             try{
                 Class.forName(driver);
                 cx = DriverManager.getConnection(url+bd, user, password);
                 System.out.println("SE CONECTO A BD" +bd);
                 
                 
             } catch (ClassNotFoundException ex) {
                  System.out.println("NO SE CONECTO A BD" +bd);
             Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
         }
     return cx;
     }
    
         

public void desconectar(){
    try{
       if (cx != null && ! cx.isClosed()){
            cx.close();
            cx = null;  
            }
     }catch (SQLException ex) {
         Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
     } 
}

public static void main(String[]args){
    

        Conexion conexion = new Conexion();
        try {
             conexion.conectar();
          }catch (SQLException ex)   {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
             
      }
    } 
 }
