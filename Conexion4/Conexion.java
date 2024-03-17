package Conexion4;

import java.sql.*;

public class Conexion {
public static void main(String[] args) {

    try{
        //Hacemos la conexion 

        Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/curso", "root", "12345678");

        PreparedStatement sentencia= con.prepareStatement(null);
    }catch(Exception e){

    }
    
}
}
