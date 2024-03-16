package Conexion1;
import java.sql.*;
public class Conexion {
public static void main(String[] args) {
    String ruta="jdbc:mysql://localhost:3306/tienda";
    try { 
        //Creo la conexion con la base de datos
        Connection miPrimeraConexion = DriverManager.getConnection(ruta, "root","12345678" );
        //Crear objeto Statement
        Statement statement = miPrimeraConexion.createStatement();

        //Ejecutar sql
        ResultSet resultSet=statement.executeQuery("select  * from PRODUCTO;");

        //Recorrer el resultado de la query
        while (resultSet.next()) {
            System.out.println("El id es: "+resultSet.getString("id")+" "+
            "El el nombre es: "+resultSet.getString("nombre")+" "+
            "El precio es: "+resultSet.getString("precio")+" "+ 
            "El id_fabricante es: "+resultSet.getString(3));
        }

    } catch (Exception e) {
        System.out.println("Algo ha fallado en la conexion");
        e.printStackTrace();

    }
}
    
}