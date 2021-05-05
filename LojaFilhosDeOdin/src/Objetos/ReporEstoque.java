/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author SynMcall
 */
public class ReporEstoque {
    private int idCompra;
    private int peçasDisponiveis;
    private Date dataCompra;

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getPeçasDisponiveis() {
        return peçasDisponiveis;
    }

    public void setPeçasDisponiveis(int peçasDisponiveis) {
        this.peçasDisponiveis = peçasDisponiveis;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Time getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(Time horaCompra) {
        this.horaCompra = horaCompra;
    }
    private Time horaCompra;
    
    
}
