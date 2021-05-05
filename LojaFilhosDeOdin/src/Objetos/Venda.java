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
public class Venda {
    private int idVenda;
    private Double valorVenda;
    private int idCliente;
    private int idFuncionario;
    private int idArma;
    private String dataVenda;
    private String horaVenda;
    private String dscArma;
    private String cpfFuncionario;
    private String cpfCliente;

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getHoraVenda() {
        return horaVenda;
    }

    public void setHoraVenda(String horaVenda) {
        this.horaVenda = horaVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getDscArma() {
        return dscArma;
    }

    public void setDscArma(String dscArma) {
        this.dscArma = dscArma;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdArma() {
        return idArma;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public void setIdArma(int idArma) {
        this.idArma = idArma;
    }


    public void cadastrarVenda(Venda venda){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO VENDA(Funcionario_idFuncionario,Cliente_idCliente,valorVenda,Arma_idArma,dataVenda,horaVenda) VALUES(?,?,?,?,curdate(),time(sysdate()))");
            stmt.setInt(1,venda.getIdFuncionario());
            stmt.setInt(2,venda.getIdCliente());
            stmt.setDouble(3,venda.getValorVenda());
            stmt.setInt(4,venda.getIdArma());             
            
            stmt.executeUpdate();          
           
            
            JOptionPane.showMessageDialog(null,"Salvo Com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    }
    public List<Venda> tabelaVendas(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Venda> vendas = new ArrayList<>();
        
        try{
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM Venda,Arma,Funcionario,Cliente where Venda.Arma_idArma = Arma.idArma and Venda.Funcionario_idFuncionario = Funcionario.idFuncionario and Venda.Cliente_idCliente = Cliente.idCliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Venda venda = new Venda();
                venda.setIdVenda(rs.getInt("idVenda"));
                venda.setIdCliente(rs.getInt("Cliente_idCliente"));
                venda.setIdFuncionario(rs.getInt("Funcionario_idFuncionario"));
                venda.setDscArma(rs.getString("Arma.dscArma"));
                venda.setCpfFuncionario(rs.getString("Funcionario.Pessoa_cpfPessoa"));
                venda.setCpfCliente(rs.getString("Cliente.Pessoa_cpfPessoa"));
                venda.setValorVenda(rs.getDouble("valorVenda"));
                venda.setHoraVenda(rs.getString("horaVenda"));
                venda.setDataVenda(rs.getString("dataVenda"));                
                vendas.add(venda);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return vendas;
    }
    public static void deletarVenda(Venda venda){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Venda WHERE  idVenda = ?");
            stmt.setInt(1,venda.getIdVenda());           
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null,"Excluído com Sucesso ");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
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
    public static void atualizar(Venda venda){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Venda SET  Cliente_idCliente = ?, Funcionario_idFuncionario = ?, Arma_idArma = ?, valorVenda = ? WHERE idVenda = ?");
            stmt.setInt(1,venda.getIdCliente());
            stmt.setInt(2,venda.getIdFuncionario());
            stmt.setInt(3,venda.getIdArma());
            stmt.setDouble(4,venda.getValorVenda());
            stmt.setInt(5,venda.getIdVenda());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Alterado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Alterar "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    
     }
}
