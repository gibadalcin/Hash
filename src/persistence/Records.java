/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import dConnection.DbConnection;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author giba01
 */
public class Records {  
    
    private int n;
    public static boolean r;
    
    public static void insert(int into) {
        try {
            String sql = "UPDATE dados_jogo SET recorde = ? WHERE id_recorde > 0";
            Connection conn = DbConnection.estabilishConnection();
            PreparedStatement register = (PreparedStatement) conn.prepareStatement(sql);
            register.setInt(1, into);
            register.executeUpdate();
            register.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Novo Recorde de Vitórias em uma Campanha!!", "Novo Recorde!!!", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!!", "Erro!", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERRO!!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteRecord() {
        try {
            Object[] options = {"Sim", "Não"};
            int answer = JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro de recordes?", "Zerar Recorde",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (answer == 0) {
                String sql="UPDATE dados_jogo SET recorde = 0 WHERE id_recorde > 0";
                Connection conn = DbConnection.estabilishConnection();
                PreparedStatement register = (PreparedStatement) conn.prepareStatement(sql);
                register.executeUpdate();
                register.close();
                conn.close();
                JOptionPane.showMessageDialog(null, "O número de registros de recorde foi zerado!!", "Zerar Recorde!!!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!!", "Erro!", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERRO!!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
  public boolean searchRecord(int record){
      String sql = "SELECT*FROM dados_jogo WHERE recorde >= ?";
      r = true;
      try{
          Connection conn = DbConnection.estabilishConnection();
          PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(sql);
          prepStmt.setInt(1, record);
          ResultSet rs = prepStmt.executeQuery();
          while(rs.next()){
             n = rs.getInt("recorde");
             if(n < record){
             JOptionPane.showMessageDialog(null,"Nenhum novo recorde foi registrado, permanece em"+
               n + "vitórias seguidas");
             r = false;
             }
          }
          rs.close();
          conn.close();
          prepStmt.close();          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL!!", "Erro!", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERRO!!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
        return false;   
  }           
}
