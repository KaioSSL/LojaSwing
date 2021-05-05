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
public class Cargo {
    private int idCargo;
    private String dscCargo;
    private String dscFuncoes;

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getDscCargo() {
        return dscCargo;
    }

    public void setDscCargo(String dscCargo) {
        this.dscCargo = dscCargo;
    }

    public String getDscFuncoes() {
        return dscFuncoes;
    }

    public void setDscFuncoes(String dscFuncoes) {
        this.dscFuncoes = dscFuncoes;
    }
    public static void cadastrarCargo(Cargo cargo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Cargo (dscCargo,dscFuncoes) VALUES(?,?)");            
            stmt.setString(1,cargo.getDscCargo());
            stmt.setString(2,cargo.getDscFuncoes());

            stmt.executeUpdate();

            
            JOptionPane.showMessageDialog(null,"Salvo Com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Dados já Existentes! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    }
  
    
    
    public static List<Cargo> buscarCargos(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cargo> cargos = new ArrayList<>();        
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Cargo");
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setDscCargo(rs.getString("dscCargo"));
                cargo.setDscFuncoes(rs.getString("dscFuncoes"));                
                cargos.add(cargo);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cargos;
    }
    @Override
    public String toString(){
            return getDscCargo();
    }
    public static void deletarCargo(Cargo cargo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Cargo WHERE  idCargo = ?");
            stmt.setInt(1,cargo.getIdCargo());           
            
            stmt.executeUpdate();      
        JOptionPane.showMessageDialog(null,"Excluído com Sucesso ");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     }
    public static void atualizar(Cargo cargo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Cargo SET  dscCargo = ?, dscFuncoes = ? WHERE idCargo = ?");
            stmt.setString(1,cargo.getDscCargo());
            stmt.setString(2,cargo.getDscFuncoes());
            stmt.setInt(3,cargo.getIdCargo());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Alterado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     }
}