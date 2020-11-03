package lapr.project.model;

import java.sql.Date;
import java.util.Objects;
import lapr.project.data.FaturaBD;


public class Fatura {
    
    private int idFatura;
    
    private String username;
    //1=janeiro
    private int mes;
    
    private int pontosAnteriores;
    
    private int pontosGanhos;
    
    private int pontosDescontados;
    
    private int pontosAtuais;
    
    private double valorCobrado;

    private double valorPago;
    
    


    private FaturaBD bd = new FaturaBD();
    
    public Fatura() {
    }

    public Fatura(String username, int mes) {
        this.username = username;
        this.mes = mes;
    }
    
    public FaturaBD getBd() {
        return bd;
    }

    public void setBd(FaturaBD bd) {
        this.bd = bd;
    }

    public int getIdFatura() {
        return idFatura;
    }

    public void setIdFatura(int idFatura) {
        this.idFatura = idFatura;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getPontosAnteriores() {
        return pontosAnteriores;
    }

    public void setPontosAnteriores(int pontosAnteriores) {
        this.pontosAnteriores = pontosAnteriores;
    }

    public int getPontosGanhos() {
        return pontosGanhos;
    }

    public void setPontosGanhos(int pontosGanhos) {
        this.pontosGanhos = pontosGanhos;
    }

    public int getPontosDescontados() {
        return pontosDescontados;
    }

    public void setPontosDescontados(int pontosDescontados) {
        this.pontosDescontados = pontosDescontados;
    }

    public int getPontosAtuais() {
        return pontosAtuais;
    }

    public void setPontosAtuais(int pontosAtuais) {
        this.pontosAtuais = pontosAtuais;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public boolean usaPontos(int pontos) {
        if(pontos > this.pontosAtuais)
            return false;
        if(pontos < 10)
            return false;
        
        while (pontos >= 10 && this.getValorCobrado() >= 1) {
            pontos -= 10;
            this.pontosDescontados += 10;
            this.pontosAtuais -= 10;
            this.valorCobrado -= 1;
        }
        return this.save();
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.username);
        hash = 73 * hash + Objects.hashCode(this.mes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }
        
    public boolean save() {
        try {
            bd.getFatura(this.getUsername(), this.getMes());
        } catch (IllegalArgumentException e) {
            return bd.addFatura(this);
        }
        return bd.atualizaFatura(this);
    }
    
    
}
