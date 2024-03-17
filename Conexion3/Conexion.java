package Conexion3;

import java.sql.*;

public class Conexion {
    
    public static void main(String[] args) {

        try {
            String nombre="curso";
            String ruta="jdbc:mysql://localhost:3306/"+nombre;
            String usuario="root";
            String pass="12345678";
            String sentencia="SELECT * from clientes";

            //Creamos la conexion 
            Connection con = DriverManager.getConnection(ruta, usuario, pass);

            //Creamos objeto de statement
            Statement statement=con.createStatement();

            ResultSet resultado=statement.executeQuery(sentencia);

            while (resultado.next()) {
                System.out.println(
                    resultado.getString(1)+" "+
                    resultado.getString(2)+" "+
                    resultado.getString(3)+" "+
                    resultado.getString(4)+" "
                );
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
