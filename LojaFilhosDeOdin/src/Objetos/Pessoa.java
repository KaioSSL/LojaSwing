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
public class Pessoa {
    
private    String cpf;
private    String nome;
private    String dataNascimento;
private    String endereco;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
private    String telefone;
private    String dataCadastro;

   public void cadastrarPessoa(Pessoa pessoa){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Pessoa (cpfPessoa,nomePessoa,enderecoPessoa,telefonePessoa,dataNascimento,dataCadastro) VALUES(?,?,?,?,?,curdate())");
            stmt.setString(1,pessoa.getCpf());
            stmt.setString(2,pessoa.getNome());
            stmt.setString(3,pessoa.getEndereco());
            stmt.setString(4,pessoa.getTelefone());
            stmt.setString(5,pessoa.getDataNascimento());            
            
            stmt.executeUpdate();
            
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Eerro Ao Salvar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
    }

   public List<Pessoa> buscarPessoa(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Pessoa> pessoas = new ArrayList<>();
        
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Cliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setCpf(rs.getString("cpfPessoa"));
                pessoa.setNome(rs.getString("nomePessoa"));
                pessoa.setEndereco(rs.getString("enderecoPessoa"));
                pessoa.setTelefone(rs.getString("telefonePessoa"));
                pessoa.setDataCadastro(rs.getString("dataCadastro"));
                pessoa.setDataNascimento(rs.getString("dataNascimento"));
                pessoas.add(pessoa);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return pessoas;
    }

   
   
   public static void deletarPessoa(Pessoa pessoa){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Pessoa WHERE  cpfPessoa = ?");
            stmt.setString(1,pessoa.getCpf());           
            
            stmt.executeUpdate();            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     }
   
   public static void deletar(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Cliente WHERE  Pessoa_cpfPessoa = ?");
            stmt.setString(1,cliente.getCpf());           
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Excluido Com Sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }deletarPessoa(cliente);
    
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
   public static void deletar(Fornecedor fornecedor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Fornecedor WHERE Pessoa_cpfPessoa = ? ");
            stmt.setString(1,fornecedor.getCpf());           
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Excluido Com Sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }deletarPessoa(fornecedor);
    
     }
   
   public static void atualizarPessoa(Pessoa pessoa){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Pessoa SET  nomePessoa = ?, enderecoPessoa = ?, telefonePessoa = ? , dataNascimento = ? WHERE cpfPessoa = ?");
            stmt.setString(1,pessoa.getNome());
            stmt.setString(2,pessoa.getEndereco());
            stmt.setString(3,pessoa.getTelefone());
            stmt.setString(4,pessoa.getDataNascimento());
            stmt.setString(5,pessoa.getCpf());
            
            stmt.executeUpdate();          

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     }
public static void atualizar(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Pessoa SET  nomePessoa = ?, enderecoPessoa = ?, telefonePessoa = ? ,  dataNascimento = ? WHERE cpfPessoa = ?");
            stmt.setString(1,cliente.getNome());
            stmt.setString(2,cliente.getEndereco());
            stmt.setString(3,cliente.getTelefone());
            stmt.setString(4,cliente.getDataNascimento());
            stmt.setString(5,cliente.getCpf());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Alterado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     } 
public static void atualizar(Funcionario funcionario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Funcionario SET  salarioFuncionario = ?, Cargo_idCargo = ? WHERE Pessoa_cpfPessoa = ?");
            stmt.setLong(1,funcionario.getSalarioFuncionario());
            stmt.setObject(2,funcionario.getIdCargo());
            stmt.setString(3,funcionario.getCpf());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Alterado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }atualizarPessoa(funcionario);
    
     } 
public static void atualizar(Fornecedor fornecedor){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Pessoa SET  nomePessoa = ?, enderecoPessoa = ?, telefonePessoa = ?, dataNascimento = ? WHERE cpfPessoa = ?");
            stmt.setString(1,fornecedor.getNome());
            stmt.setString(2,fornecedor.getEndereco());
            stmt.setString(3,fornecedor.getTelefone());
            stmt.setString(4,fornecedor.getDataNascimento());
            stmt.setString(5,fornecedor.getCpf());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Alterado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     } 
}
