/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author giba01
 */
public class DbConnection {
    public static Connection estabilishConnection(){    
        Connection connection = null;   
        try {       
            Class.forName("org.postgresql.Driver");
            String bancoURL = "jdbc:postgresql://localhost:5435/jogo_da_velha"; 
            connection =(Connection)DriverManager.getConnection(bancoURL,"postgres","");
            //endereço do servidor,porta,nome do banco,usuario,senha.
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Falha na conexão com o banco", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!!", "Erro!", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro desconhecido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
       return connection;
    }
}
