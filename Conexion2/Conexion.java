package Conexion2;
import java.sql.*;

public class Conexion {
    public static void main(String[] args) {
        String ruta="jdbc:mysql://localhost:3306/curso";//Ruta donde estamos conectandonos a la base de datos en mi caso local y el puerto , tambien a la base de datos cuales vamos hacer las peticiones
        String usuario="root";
        //Ejemplo de consulta algo mas compleja
        String sentencia="SELECT  NOMBRE,cliente_no,localidad, VENDEDOR_NO from clientes where vendedor_no in (select VENDEDOR_NO from empleados );";


        try{
            //Llamamos a la interfaz Connection y a la clase DriverManager estos se encargan de conectarnos a la base de datos
            Connection con = DriverManager.getConnection(ruta,usuario, "12345678");
            
            //Crear un objeto de statement
            Statement statement= con.createStatement();

            //Llamamos a la interfaz Resultset para obtener el resultado de las sentencias sql
            ResultSet resultado= statement.executeQuery(sentencia);

            //Recorremos el resultado de la sentencia y la mostramos por pantalla
            while (resultado.next()) {
                System.out.println(
                    resultado.getString(1)+" "+
                    resultado.getString(2)+" "+
                    resultado.getString(3)+" "+
                    resultado.getString(4)+" "
                );
            }


        }catch(Exception e){
            System.out.println("Algo ha fallado en la conexion");
            e.printStackTrace();
        }
    }

}
