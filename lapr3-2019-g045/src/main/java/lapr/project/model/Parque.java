/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.data.ParqueBD;

/**
 *
 * @author Thais Farias
 */
public class Parque implements Local {

    // Declaração de variáveis de instância
    private String idParque;
    private Double latitude;
    private Double longitude;
    private Integer elevacao;
    private String descricao;
    private Integer lotBike;
    private Integer lotScooter;
    private Integer voltagem;
    private Integer corrente;
    private Integer ativo;
    private ParqueBD bd = new ParqueBD();

    /*
    Construtor da Classe Parque sem parâmetros
     */
    public Parque() {

    }

    /**
     * Construtor Classe Parque com parâmetros
     *
     * @param idParque
     * @param latitude
     * @param longitude
     * @param elevacao
     * @param descricao
     * @param lot_bike
     * @param lot_scooter
     * @param voltagem
     * @param corrente
     */
    public Parque(String idParque, Double latitude, Double longitude, Integer elevacao, String descricao, Integer lot_bike, Integer lot_scooter, Integer voltagem, Integer corrente) {
        this.idParque = idParque;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevacao = elevacao;
        this.descricao = descricao;
        this.lotBike = lot_bike;
        this.lotScooter = lot_scooter;
        this.voltagem = voltagem;
        this.corrente = corrente;
        this.ativo = 1;
    }

    /**
     * Método retorna o Id do parque
     *
     * @return
     */
    public String getIdParque() {
        return idParque;
    }

    /**
     * Método faz set ao Id do parque
     *
     * @param idParque
     */
    public void setIdParque(String idParque) {
        this.idParque = idParque;
    }

    /**
     * Método retorna a Latitude
     *
     * @return
     */
    @Override
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Método faz set a Latitude do Parque
     *
     * @param latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Método retorna a Longitude do Parque
     *
     * @return
     */
    @Override
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Método set Longitude do Parque
     *
     * @param longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Método get Elevação do Parque
     *
     * @return elevacao
     */
    public Integer getElevacao() {
        return elevacao;
    }

    /**
     * *
     * Método set Elevação do Parque
     *
     * @param elevacao
     */
    public void setElevacao(Integer elevacao) {
        this.elevacao = elevacao;
    }

    /**
     * Método get Descrição do Parque
     *
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método set Descrição do Parque
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Método get Lotação de Bicicletas do Parque
     *
     * @return
     */
    public Integer getLotBike() {
        return lotBike;
    }

    /**
     * Método set Lotação Bicicletas do Parque
     *
     * @param lotBike
     */
    public void setLotBike(Integer lotBike) {
        this.lotBike = lotBike;
    }

    /**
     * Método get Lotação scooters do Parque
     *
     * @return
     */
    public Integer getLotScooter() {
        return lotScooter;
    }

    /**
     * Método set Lotação Scooters do Parque
     *
     * @param lotScooter
     */
    public void setLotScooter(Integer lotScooter) {
        this.lotScooter = lotScooter;
    }

    /**
     * Método get Voltagem do Parque
     *
     * @return
     */
    public Integer getVoltagem() {
        return voltagem;
    }

    /**
     * Método set Voltagem do Parque
     *
     * @param voltagem
     */
    public void setVoltagem(Integer voltagem) {
        this.voltagem = voltagem;
    }

    /**
     * Método retorna a corrente de um parque
     *
     * @return
     */
    public Integer getCorrente() {
        return corrente;
    }

    /**
     * Método faz set a corrente do parque
     *
     * @param corrente
     */
    public void setCorrente(Integer corrente) {
        this.corrente = corrente;
    }

    /**
     * Método Get ativo Parque
     *
     * @return
     */
    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idParque);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            return this.hashCode() == obj.hashCode();
        }
    }

    public boolean save() {
        try {
            bd.getParque(this.idParque);
        } catch (IllegalArgumentException e) {
            return bd.addParque(this);
        }
        return bd.atualizaParque(this);
    }

    public void removerParque() {
        this.ativo = 0;
    }

    public void setBd(ParqueBD bd) {
        this.bd = bd;
    }

    public String toString() {
        return descricao;
    }
    
    public Double getPotenciaParque(){
        Double volt = this.getVoltagem().doubleValue();
        Double intens = this.getCorrente().doubleValue();
        return volt * intens;
    }

}
