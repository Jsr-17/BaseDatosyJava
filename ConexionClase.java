import java.sql.*;

public class ConexionClase {
    
    private String ruta="jdbc:mysql://localhost:3306/";
    public Connection con;

    public ConexionClase (String base){
        try {
             con = DriverManager.getConnection(ruta+base , "root", "12345678");

        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    public void hacerConsulta(String consulta){

        try{
            Statement state = con.createStatement();
            ResultSet resultado=state.executeQuery(consulta);

            while (resultado.next()) {
                System.out.println(
                    resultado.getString(1)+" "+
                    resultado.getString(2)+" "+
                    resultado.getString(3)+" "+
                    resultado.getString(4)+" "
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
