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
public class Funcionario extends Pessoa{
    private int idFuncionario;
    private Long salarioFuncionario;
    private Object idCargo;
    private String dscCargo;

    public String getDscCargo() {
        return dscCargo;
    }

    public void setDscCargo(String dscCargo) {
        this.dscCargo = dscCargo;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Long getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(Long salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }

    public Object getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Object idCargo) {
        this.idCargo = idCargo;
    }

    /**
     *
     * @param funcionario
     */
    public static void cadastrarPessoa(Funcionario funcionario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Pessoa (cpfPessoa,nomePessoa,enderecoPessoa,telefonePessoa,dataNascimento,dataCadastro) VALUES(?,?,?,?,?,curdate())");
            stmt.setString(1,funcionario.getCpf());
            stmt.setString(2,funcionario.getNome());
            stmt.setString(3,funcionario.getEndereco());
            stmt.setString(4,funcionario.getTelefone());
            stmt.setString(5,funcionario.getDataNascimento());            
            
            stmt.executeUpdate();
            
            
            stmt = con.prepareStatement("INSERT INTO Funcionario(salarioFuncionario, Cargo_idCargo,Pessoa_cpfPessoa) VALUES(?,?,?)");            
            stmt.setDouble(1,funcionario.getSalarioFuncionario());
            stmt.setObject(2, funcionario.getIdCargo());
            stmt.setString(3,funcionario.getCpf());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo Com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Eerro Ao Salvar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    }
    public static void deletarFuncionario(String chaveExcluir ){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Funcionario WHERE cpfFuncionario = ?");
            stmt.setString(1,chaveExcluir);            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Excluido com Sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    }
    
    @Override
    public List<Pessoa> buscarPessoa(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Pessoa> funcionarios = new ArrayList<>();
        
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Funcionario");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("Pessoa_cpfPessoa"));
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionario.setIdCargo(rs.getInt("Cargo_idCargo"));
                funcionario.setSalarioFuncionario(rs.getLong("salarioFuncionario"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }
    @Override
    public String toString(){
        return getCpf();
    }
   public List<Funcionario> tabelaFuncionario(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try{
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Funcionario,Pessoa,Cargo WHERE Pessoa.cpfPessoa = Funcionario.Pessoa_cpfPessoa and Cargo.idCargo = Funcionario.Cargo_idCargo order by Pessoa.nomePessoa");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("cpfPessoa"));
                funcionario.setNome(rs.getString("nomePessoa"));
                funcionario.setEndereco(rs.getString("enderecoPessoa"));
                funcionario.setTelefone(rs.getString("telefonePessoa"));
                funcionario.setDataCadastro(rs.getString("dataCadastro"));
                funcionario.setDataNascimento(rs.getString("dataNascimento"));
                funcionario.setSalarioFuncionario(rs.getLong("salarioFuncionario"));
                funcionario.setDscCargo(rs.getString("Cargo.dscCargo"));
                
                funcionarios.add(funcionario);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    
    }
   public static void deletar(Funcionario funcionario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Funcionario WHERE Pessoa_cpfPessoa = ?");
            stmt.setString(1,funcionario.getCpf());           
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Excluido Com Sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }deletarPessoa(funcionario);
    
     }
}
 
