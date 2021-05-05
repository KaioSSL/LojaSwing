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
public class Cliente extends Pessoa{
    private int idCliente;
    private int qtdProdutosAdquiridos;
    private Double valorConsumido;
    private String registroCRAF; 



    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getQtdProdutosAdquiridos() {
        return qtdProdutosAdquiridos;
    }

    public void setQtdProdutosAdquiridos(int qtdProdutosAdquiridos) {
        this.qtdProdutosAdquiridos = qtdProdutosAdquiridos;
    }

    public Double getValorConsumido() {
        return valorConsumido;
    }

    public void setValorConsumido(Double valorConsumido) {
        this.valorConsumido = valorConsumido;
    }

    public String getRegistroCRAF() {
        return registroCRAF;
    }

    public void setRegistroCRAF(String registroCRAF) {
        this.registroCRAF = registroCRAF;
    }
    
    /**
     *
     * @param cliente
     */
    public static void cadastrarPessoa(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Pessoa (cpfPessoa,nomePessoa,enderecoPessoa,telefonePessoa,dataNascimento,dataCadastro) VALUES(?,?,?,?,?,curdate())");
            stmt.setString(1,cliente.getCpf());
            stmt.setString(2,cliente.getNome());
            stmt.setString(3,cliente.getEndereco());
            stmt.setString(4,cliente.getTelefone());
            stmt.setString(5,cliente.getDataNascimento());            
            
            stmt.executeUpdate();            
            
            stmt = con.prepareStatement("INSERT INTO Cliente(Pessoa_cpfPessoa,CRAF_Pessoa_registroCRAF) VALUES(?,?)");            
            stmt.setString(1,cliente.getCpf());
            stmt.setString(2,cliente.getRegistroCRAF());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo Com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Dados já Existentes! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    }
    

    
    @Override
    public List<Pessoa> buscarPessoa(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Pessoa> clientes = new ArrayList<>();
        
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Cliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("Pessoa_cpfPessoa"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setQtdProdutosAdquiridos(rs.getInt("qtdProdutosAdquiridos"));
                cliente.setValorConsumido(rs.getDouble("valorConsumido"));
                cliente.setRegistroCRAF(rs.getString("CRAF_Pessoa_registroCRAF"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
    
    private static int ultimoId(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        int registros = 0;
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT count(*) FROM Cliente)");
            rs = stmt.executeQuery();
            registros = rs.getInt(1);
        } catch (SQLException ex) {

        }finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
            
        }
        return registros;
    }
    @Override
    public String toString(){
        return getCpf();
    }
    
    
    public List<Cliente> tabelaCliente(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
        try{
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Cliente,Pessoa WHERE Pessoa.cpfPessoa = Cliente.Pessoa_cpfPessoa order by Pessoa.nomePessoa");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpfPessoa"));
                cliente.setNome(rs.getString("nomePessoa"));
                cliente.setEndereco(rs.getString("enderecoPessoa"));
                cliente.setTelefone(rs.getString("telefonePessoa"));
                cliente.setDataCadastro(rs.getString("dataCadastro"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setRegistroCRAF(rs.getString("CRAF_Pessoa_registroCRAF"));
                
                
                clientes.add(cliente);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
}
