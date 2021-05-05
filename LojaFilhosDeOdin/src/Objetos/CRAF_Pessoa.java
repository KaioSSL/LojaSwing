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
public class CRAF_Pessoa {
    private String registroCRAF;
    private String dataValidade;
    private String dataExpedição;
    private String orgãoExpedidor;

    public String getRegistroCRAF() {
        return registroCRAF;
    }

    public void setRegistroCRAF(String registroCRAF) {
        this.registroCRAF = registroCRAF;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDataExpedição() {
        return dataExpedição;
    }

    public void setDataExpedição(String dataExpedição) {
        this.dataExpedição = dataExpedição;
    }

    public String getOrgãoExpedidor() {
        return orgãoExpedidor;
    }

    public void setOrgãoExpedidor(String orgãoExpedidor) {
        this.orgãoExpedidor = orgãoExpedidor;
    }
    
    public static void cadastrarCRAF_Pessoa(CRAF_Pessoa craf){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO CRAF_Pessoa(registroCRAF,dataValidade,dataExpedição,orgãoExpedidor) VALUES(?,?,?,?)");
            stmt.setString(1,craf.getRegistroCRAF());
            stmt.setString(2,craf.getDataValidade());
            stmt.setString(3,craf.getDataExpedição());
            stmt.setString(4,craf.getOrgãoExpedidor());           
            
            stmt.executeUpdate();    
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Dados já Existentes! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
    }
    public static List<CRAF_Pessoa> buscarCRAF(){
        Connection con = ConnectionFactory.getConnection();
        com.mysql.jdbc.PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<CRAF_Pessoa> crafs = new ArrayList<>();        
        try {
            stmt = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("SELECT * FROM CRAF_Pessoa");
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                CRAF_Pessoa craf = new CRAF_Pessoa();
                craf.setRegistroCRAF(rs.getString("registroCRAF"));
                craf.setDataExpedição(rs.getString("dataExpedição"));
                craf.setDataValidade(rs.getString("dataValidade"));
                craf.setOrgãoExpedidor(rs.getString("orgãoExpedidor"));
                crafs.add(craf);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar! "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return crafs;
    }
}
