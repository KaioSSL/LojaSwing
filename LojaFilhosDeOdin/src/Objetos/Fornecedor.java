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
public class Fornecedor extends Pessoa {
    private int idFornecedor;
    
    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
    
    public static void cadastrarPessoa(Fornecedor fornecedor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Pessoa (cpfPessoa,nomePessoa,enderecoPessoa,telefonePessoa,dataNascimento,dataCadastro) VALUES(?,?,?,?,?,curdate())");
            stmt.setString(1,fornecedor.getCpf());
            stmt.setString(2,fornecedor.getNome());
            stmt.setString(3,fornecedor.getEndereco());
            stmt.setString(4,fornecedor.getTelefone());
            stmt.setString(5,fornecedor.getDataNascimento());            
            
            stmt.executeUpdate();            
            
            stmt = con.prepareStatement("INSERT INTO Fornecedor(Pessoa_cpfPessoa) VALUES(?)");            
            stmt.setString(1,fornecedor.getCpf());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo Com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Dados já Existentes! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    }
    @Override
    public  List<Pessoa> buscarPessoa(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Pessoa> fornecedores = new ArrayList<>();
        
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Fornecedor");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCpf(rs.getString("Pessoa_cpfPessoa"));
                fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                fornecedores.add(fornecedor);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return fornecedores;
    }
    @Override
    public String toString(){
        return getCpf();
    }
    public List<Fornecedor> tabelaFornecedor(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Fornecedor> fornecedores = new ArrayList<>();
        
        try{
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Fornecedor,Pessoa WHERE Pessoa.cpfPessoa = Fornecedor.Pessoa_cpfPessoa order by Pessoa.nomePessoa");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                fornecedor.setCpf(rs.getString("cpfPessoa"));
                fornecedor.setNome(rs.getString("nomePessoa"));
                fornecedor.setEndereco(rs.getString("enderecoPessoa"));
                fornecedor.setTelefone(rs.getString("telefonePessoa"));
                fornecedor.setDataCadastro(rs.getString("dataCadastro"));
                fornecedor.setDataNascimento(rs.getString("dataNascimento"));
                
                fornecedores.add(fornecedor);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return fornecedores;
    
    }
}
