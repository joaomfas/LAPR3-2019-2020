/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Parque;
import lapr.project.model.Pesquisa;
import lapr.project.model.Transacao;
import static lapr.project.utils.Parsers.*;

/**
 *
 * @author Thais Farias
 */
public class ParqueController {

    Pesquisa p = new Pesquisa();
    Transacao t = new Transacao();

    /**
     * Cria um parque e tenta salvar
     *
     * @param dados
     * @return
     */
    public int addParques(List<String[]> dados) {
        ArrayList<Parque> pqs = new ArrayList<>();
        for (String[] dado : dados) {
            String id = dado[0];
            Double latitude = parseDouble(dado[1]);
            Double longitude = parseDouble(dado[2]);
            Integer elevacao = parseInt(dado[3]);
            String descricao = dado[4];
            Integer lot_bike = parseInt(dado[5]);
            Integer lot_scooter = parseInt(dado[6]);
            Integer voltagem = parseInt(dado[7]);
            Integer corrente = parseInt(dado[8]);
            Parque parque = new Parque(id, latitude, longitude, elevacao, descricao, lot_bike, lot_scooter, voltagem, corrente);
            pqs.add(parque);
        }
        boolean success = t.addAllParques(pqs);
        if (success) {
            return dados.size();
        } else {
            return 0;
        }
    }

    /**
     *
     * @param dados
     * @return
     */
    public boolean atualizaParque(String[] dados) {

        String id = dados[0];
        Parque parque = getParque(id);
        if (parque == null) {
            return false;
        }
        parque.setLatitude(parseDouble(dados[1]));
        parque.setLongitude(parseDouble(dados[2]));
        parque.setElevacao(parseInt(dados[3]));
        parque.setDescricao(dados[4]);
        parque.setLotBike(parseInt(dados[5]));
        parque.setLotScooter(parseInt(dados[6]));
        parque.setVoltagem(parseInt(dados[7]));
        parque.setCorrente(parseInt(dados[8]));

        return salvarParque(parque);

    }

    /**
     *
     * @param id - Id do parque
     * @return 0 ou 1
     */
    public boolean removeParque(String id) {
        try {
            Parque parque = getParque(id);
            parque.removerParque();
            return salvarParque(parque);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * *
     * Tenta salvar o objeto Parque criado
     *
     * @param p
     * @return
     */
    public boolean salvarParque(Parque p) {
        return p.save();
    }

    public Parque getParque(String id) {
        return p.getParque(id);
    }

    public List<Parque> getParques() {
        return p.getParques();
    }

    public void setTransacao(Transacao t) {
        this.t = t;
    }

    public void setPesquisa(Pesquisa p) {
        this.p = p;
    }

    /**
     * *
     * Verifica os lugares disponíveis
     *
     * @param id
     * @param username
     * @return
     */
    public int verificaLugaresDisponiveisParque(String id, String username) {
        Parque parque = p.getParque(id);
        if (parque != null) {
            return p.verificaLugaresDisponiveis(id, username);
        } else {
            return 0;
        }
    }
}
