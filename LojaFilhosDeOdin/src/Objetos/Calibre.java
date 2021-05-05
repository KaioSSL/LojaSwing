/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import ConexãoSQL.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author SynMcall
 */
public class Calibre {
    private int idCalibre;
    private String dscCalibre;

    public int getIdCalibre() {
        return idCalibre;
    }

    public void setIdCalibre(int idCalibre) {
        this.idCalibre = idCalibre;
    }

    public String getDscCalibre() {
        return dscCalibre;
    }

    public void setDscCalibre(String dscCalibre) {
        this.dscCalibre = dscCalibre;
    }
    
    public static void cadastrarCalibre(Calibre calibre){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Calibres (dscCalibre) VALUES(?)");
            stmt.setString(1,calibre.getDscCalibre());
                       
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com Sucesso");
            
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro Ao Salvar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
    }
    public static List<Calibre> buscarCalibres(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Calibre> calibres = new ArrayList<>();        
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Calibres");
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                Calibre calibre = new Calibre();
                calibre.setIdCalibre(rs.getInt("idCalibre"));
                calibre.setDscCalibre(rs.getString("dscCalibre"));                             
                calibres.add(calibre);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return calibres;
    }
    public String toString(){
        return getDscCalibre();
    }
    public static void deletarCalibre(Calibre calibre){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Calibres WHERE  idCalibre = ?");
            stmt.setInt(1,calibre.getIdCalibre());           
            
            stmt.executeUpdate();            
            JOptionPane.showMessageDialog(null,"Excluído com Sucesso ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     }
    public static void atualizar(Calibre calibre){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Calibres SET  dscCalibre = ? WHERE idCalibre = ?");
            stmt.setString(1,calibre.getDscCalibre());
            stmt.setInt(2,calibre.getIdCalibre());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Alterado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     }

}
