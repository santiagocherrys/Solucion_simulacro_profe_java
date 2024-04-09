package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
 public static Connection objConnection =  null;

 public static Connection openConnection(){
     try{
         Class.forName("com.mysql.cj.jdbc.Driver");

         String url = "jdbc:mysql://localhost:3306/simulacro_01";
         String user = "root";
         String password = "Rlwl2023.";

         //Estableces conexión

         objConnection = DriverManager.getConnection(url, user, password);
         System.out.println("Me conectè correctamente");
     }catch (ClassNotFoundException e){
         System.out.println("Error Driver no instalado" + e.getMessage());
     }catch (SQLException e){
         System.out.println("Error al conectar la base de datos " + e.getMessage() );
     }

     return objConnection;
 }

 public static void closeConnection(){
     try{
         if(objConnection != null){
             objConnection.close();
             System.out.println("Se finalizò la conexión con exito");
         }

     }catch (Exception e){
         System.out.println("Error " + e.getMessage());
     }
 }
}
