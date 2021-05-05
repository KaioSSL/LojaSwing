/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import ConexãoSQL.ConnectionFactory;
import static Objetos.Pessoa.atualizarPessoa;
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
public class Arma {
    private int idArma;
    private String dscArma;
    private Double valorArma;
    private int idFornecedor;
    private int idCalibre;
    private String cpfFornecedor;
    private String DscCalibre;

    public int getIdArma() {
        return idArma;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }

    public String getDscArma() {
        return dscArma;
    }

    public void setDscArma(String dscArma) {
        this.dscArma = dscArma;
    }

    public Double getValorArma() {
        return valorArma;
    }

    public void setValorArma(Double valorArma) {
        this.valorArma = valorArma;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdCalibre() {
        return idCalibre;
    }

    public void setIdCalibre(int idCalibre) {
        this.idCalibre = idCalibre;
    }

    public String getCpfFornecedor() {
        return cpfFornecedor;
    }

    public void setCpfFornecedor(String cpfFornecedor) {
        this.cpfFornecedor = cpfFornecedor;
    }

    public String getDscCalibre() {
        return DscCalibre;
    }

    public void setDscCalibre(String DscCalibre) {
        this.DscCalibre = DscCalibre;
    }

    public static void cadastrarArma(Arma arma){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Arma (idArma,dscArma,Fornecedor_idFornecedor,Calibres_idCalibre,valorArma) VALUES(?,?,?,?,?)");
            stmt.setInt(1,arma.getIdArma());
            stmt.setString(2,arma.getDscArma());
            stmt.setInt(3,arma.getIdFornecedor());
            stmt.setInt(4,arma.getIdCalibre()); 
            stmt.setDouble(5, arma.getValorArma());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Salvo com Sucesso ");
            
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Eerro Ao Salvar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
    }
    public List<Arma> buscarArma(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Arma> armas = new ArrayList<>();        
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Arma");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Arma arma = new Arma();
                arma.setIdArma(rs.getInt("idArma"));
                arma.setDscArma(rs.getString("dscArma"));
                arma.setIdFornecedor(rs.getInt("Fornecedor_idFornecedor")); 
                arma.setIdCalibre(rs.getInt("Calibres_idCalibre"));
                arma.setValorArma(rs.getDouble("valorArma"));
                armas.add(arma);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return armas;
    }
    public String toString(){
        return getDscArma();
    }
    
    public List<Arma> tabelaArmas(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Arma> armas = new ArrayList<>();        
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Arma,Fornecedor,Calibres where Arma.Fornecedor_idFornecedor = Fornecedor.idFornecedor and Arma.Calibres_idCalibre = Calibres.idCalibre");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Arma arma = new Arma();
                arma.setIdArma(rs.getInt("idArma"));
                arma.setDscArma(rs.getString("dscArma"));
                arma.setCpfFornecedor(rs.getString("Fornecedor.Pessoa_cpfPessoa")); 
                arma.setDscCalibre(rs.getString("Calibres.dscCalibre"));
                arma.setValorArma(rs.getDouble("valorArma"));
                armas.add(arma);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return armas;
    }
    public static void deletarArma(Arma arma){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Arma WHERE  idArma = ?");
            stmt.setInt(1,arma.getIdArma());           
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null,"Excluído com Sucesso ");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     }
    
    public static void atualizar(Arma arma){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Arma SET  dscArma = ?, valorArma = ?, Fornecedor_idFornecedor = ? , Calibres_idCalibre = ? WHERE idArma = ?");
            stmt.setString(1,arma.getDscArma());
            stmt.setDouble(2,arma.getValorArma());
            stmt.setInt(3,arma.getIdFornecedor());
            stmt.setInt(4,arma.getIdCalibre());
            stmt.setInt(5, arma.getIdArma());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Alterado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     }
    
    
}
