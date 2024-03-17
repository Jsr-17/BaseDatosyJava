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
            ResultSetMetaData datos= resultado.getMetaData();
            int n= datos.getColumnCount();

            while (resultado.next()) {
                String valor="";
                for (int i = 1; i < n; i++) {
                    if (resultado.getString(i)== null) {
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
    public void consultaPreparada(){
        
    }
}
