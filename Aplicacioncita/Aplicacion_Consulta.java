import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.sql.*;;

public class Aplicacion_Consulta {
    public static void main(String[] args) {
        JFrame marco = new Marco_Aplicacion();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);
    }
}

/**
 * Marco_Aplicacion
 */
class Marco_Aplicacion extends JFrame {

    public Marco_Aplicacion(){
        setTitle("Consulta BBDD");
        setBounds(500,300,400,400);
        setLayout(new BorderLayout());
        
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout()); 
        
        localidad = new JComboBox<String>();
        localidad.setEditable(false);
        localidad.addItem("Todos");
        
         vendedor = new JComboBox<String>();
        vendedor.setEditable(false);
        vendedor.addItem("Todos");
        
         resultado = new JTextArea(4, 50);
        resultado.setEditable(false);
        
        JButton botonConsulta = new JButton("Consulta");
        
        menu.add(localidad);
        menu.add(vendedor);
        botonConsulta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                ejecutaConsulta();
            }
        });
        menu.add(botonConsulta);
        
        add(menu, BorderLayout.NORTH);
        
        add(resultado, BorderLayout.CENTER);
try {
     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/curso", "root", "12345678");
    java.sql.Statement sentencia = conn.createStatement();
    ResultSet rs = sentencia.executeQuery("Select distinct localidad from clientes");

    while (rs.next()) {
            localidad.addItem(rs.getString(1));
    }
    rs.close();
    rs = sentencia.executeQuery("Select distinct vendedor_no from clientes");

    while (rs.next()) {
        vendedor.addItem(rs.getString(1));
    }
    rs.close();
} catch (Exception e) {
    e.printStackTrace();
}
    }
    private void ejecutaConsulta(){
        ResultSet rs=null;
        try {
            resultado.setText("");
            String localidadS=(String) localidad.getSelectedItem();
            String vendedorS=(String) vendedor.getSelectedItem();
            if (!localidadS.equals("Todos") && vendedorS.equals("Todos")) {

                enviaConsulta=conn.prepareStatement(consulta1);
                enviaConsulta.setString(1, localidadS);
                rs=enviaConsulta.executeQuery();
                
                while (rs.next()) {
                    resultado.append(rs.getString(1));
                    resultado.append(",");
                    resultado.append(rs.getString(2));
                    resultado.append(",");
                    resultado.append(rs.getString(3));
                    resultado.append(",");
                    resultado.append(rs.getString(4));
                    resultado.append("\n");
                
                }
                rs.close();
                
            } else if (localidadS.equals("Todos") && !vendedorS.equals("Todos")) {
                enviaConsulta=conn.prepareStatement(consulta2);
                enviaConsulta.setString(1, vendedorS);
                rs=enviaConsulta.executeQuery();
                
                while (rs.next()) {
                    resultado.append(rs.getString(1));
                    resultado.append(",");
                    resultado.append(rs.getString(2));
                    resultado.append(",");
                    resultado.append(rs.getString(3));
                    resultado.append(",");
                    resultado.append(rs.getString(4));
                    resultado.append("\n");
                
                }
                rs.close();
                
            }else  if (!localidadS.equals("Todos") && !vendedorS.equals("Todos")) {

                enviaConsulta=conn.prepareStatement(consulta3);
                enviaConsulta.setString(1, localidadS);
                enviaConsulta.setString(2, vendedorS);
                rs=enviaConsulta.executeQuery();
                
                while (rs.next()) {
                    resultado.append(rs.getString(1));
                    resultado.append(",");
                    resultado.append(rs.getString(2));
                    resultado.append(",");
                    resultado.append(rs.getString(3));
                    resultado.append(",");
                    resultado.append(rs.getString(4));
                    resultado.append("\n");
                
                }
                rs.close();
            }
            

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    private JTextArea resultado;
    private JComboBox vendedor;
    private JComboBox localidad;
    private Connection conn;
    private PreparedStatement enviaConsulta;
    private final String consulta1="Select * from clientes where localidad=? ";
    private final String consulta2="Select * from clientes where vendedor_no=? ";
    private final String consulta3="Select * from clientes where localidad=? && vendedor_no=? ";

}
