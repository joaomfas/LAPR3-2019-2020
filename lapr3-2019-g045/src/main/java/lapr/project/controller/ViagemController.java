package lapr.project.controller;

import java.sql.Date;
import java.util.*;
import lapr.project.model.*;
import lapr.project.utils.Calculos;

public class ViagemController {

    Pesquisa p = new Pesquisa();

    public long unLock(Utilizador utilizador, Veiculo veiculo, Parque parqueOrigem, Date date) {
        if (utilizador == null || veiculo == null || parqueOrigem == null) {
            return 0;
        }
        Viagem viagem = new Viagem(utilizador.getIdUtilizador(), veiculo.getDescricao(), parqueOrigem.getIdParque(), date);
        if (salvarViagem(viagem)) {
            return date.getTime();
        } else {
            return 0;
        }
    }

    public long lock(Utilizador utilizador, Veiculo veiculo, Parque parqueDestino, Date date) {
        if (utilizador == null || veiculo == null || veiculo.getRemovido() || parqueDestino == null) {
            return 0;
        }
        Viagem v = p.getViagemUtilizador(utilizador.getIdUtilizador());
        if (v == null || v.getDescVeiculo() == null || veiculo.getDescricao() == null) {
            return 0;
        }
        if (!v.getDescVeiculo().equalsIgnoreCase(veiculo.getDescricao())) {
            return 0;
        }
        Viagem viagem = new Viagem(v.getIdViagem(),
                v.getIdUtilizador(),
                v.getDescVeiculo(),
                v.getIdParqueInicio(),
                parqueDestino.getIdParque(),
                v.getDataHoraInicio(),
                date);
        if (salvarViagem(viagem)) {
            return date.getTime();
        } else {
            return 0;
        }
    }

    public long unlockVeiculo(String veiculoDescription, String username) {
        Date date = dateInstance();
        Utilizador utilizador = p.getUtilizadorByUserName(username);
        Veiculo veiculo = p.getVeiculo(veiculoDescription);
        if (veiculo == null) {
            return 0;
        }
        Parque parque = p.getParque(veiculo.getIdParque());
        return unLock(utilizador, veiculo, parque, date);
    }

    public long unlockVeiculo(String veicDescription, String parkIdentification, String username) {
        Date date = dateInstance();
        Utilizador utilizador = p.getUtilizadorByUserName(username);
        Veiculo veiculo = p.getVeiculo(veicDescription);
        Parque parque = p.getParque(parkIdentification);
        return unLock(utilizador, veiculo, parque, date);
    }

    public long lockVeiculo(String veicDescription, double parkLatitudeInDegrees, double parkLongitudeInDegrees, String username) {
        Date date = dateInstance();
        Utilizador utilizador = p.getUtilizadorByUserName(username);
        Veiculo veiculo = p.getVeiculo(veicDescription);
        Parque parque = p.getParque(parkLatitudeInDegrees, parkLongitudeInDegrees);
        return lock(utilizador, veiculo, parque, date);
    }

    public long lockVeiculo(String veicDescription, String parkIdentification, String username) {
        Date date = dateInstance();
        Utilizador utilizador = p.getUtilizadorByUserName(username);
        Veiculo veiculo = p.getVeiculo(veicDescription);
        Parque parque = p.getParque(parkIdentification);
        return lock(utilizador, veiculo, parque, date);
    }

    public Date dateInstance() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * *
     * Tenta salvar o objeto Viagem criado
     *
     * @param v
     * @return
     */
    public boolean salvarViagem(Viagem v) {
        return v.save();
    }

    public Veiculo getVeiculo(String desVeiculo) {
        return p.getVeiculo(desVeiculo);
    }

    public List<Scooter> sugerirScooter(String parqueID, String username, Double lat, Double lon) {
        Utilizador user = p.getUtilizadorByUserName(username);
        Parque parqueOrigem = p.getParque(parqueID);
        Parque parqueDestino = p.getParque(lat, lon);

        if (parqueOrigem == null || parqueDestino == null || user == null) {
            return null;
        }

        RotasController rotasController = new RotasController();
        LinkedList<Local> rota = rotasController.rotaMaisCurta(parqueOrigem.getLatitude(), parqueOrigem.getLongitude(),
                parqueDestino.getLatitude(),
                parqueDestino.getLongitude());
        
        if(rota.size() == 0){
            return new ArrayList<>();
        }
        
        Double energiaNecessaria = rotasController.energiaRota(rota) * 1.1;

        List<Scooter> lista = p.getScooterComCargaSuficiente(energiaNecessaria, parqueOrigem);
        lista.sort(new Comparator<Scooter>() {
            @Override
            public int compare(Scooter p1, Scooter p2) {
				double c1 = Double.valueOf(p1.getCargaAtual()) * Double.valueOf(p1.getCargaAtual());
				double c2 = Double.valueOf(p2.getCargaAtual()) * Double.valueOf(p2.getCargaAtual());
				return Double.compare(c2, c1);
            }
        });
        return lista;
    }

    public double caloriasEntreDoisPontos(Local locA, Local locB, Veiculo v, Utilizador u) {
        Double energiaWH = Calculos.energiaEntreLocais(null, u, v, locA, locB);
        return energiaWH * 860420.65; // 1Wh = 860420.65 cal
    }
    
    public double caloriasEntreDoisPontos(double kwh) {
        return kwh * 860420.65; // 1Wh = 860420.65 cal
    }
}
