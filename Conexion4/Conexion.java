package Conexion4;

import java.sql.*;

public class Conexion {
public static void main(String[] args) {

    try{
        //Hacemos la conexion 

        Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/curso", "root", "12345678");

        //Hacemos el objeto para la consulta
        PreparedStatement sentencia= con.prepareStatement("Select * from clientes where localidad=?");

        //Establezco el parametro de la consulta 
        sentencia.setString(1, "MADRID");

        ResultSet resultado= sentencia.executeQuery();
        ResultSetMetaData datos= resultado.getMetaData();
        int n= datos.getColumnCount();

        while (resultado.next()) {
            String valor="";
            for (int i = 1; i < n; i++) {
                if (resultado.getString(i)==null) {
                    valor+= "nulo ";
                }else{

                    valor+=resultado.getString(i)+" ";
                }
            }
            System.out.println(
            valor
            );
        }
    }catch(Exception e){
            e.printStackTrace();
    }
    
}
}
