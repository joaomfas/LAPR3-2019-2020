package lapr.project.controller;

import java.util.ArrayList;
import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.util.Pair;
import lapr.project.model.*;
import static lapr.project.utils.Parsers.*;

public class VeiculoController {

    Pesquisa p = new Pesquisa();
    Transacao t = new Transacao();

    public int carregarScooters(List<String[]> strings) {
        ArrayList<Veiculo> scooters = new ArrayList<>();

        for (String[] str : strings) {
            String desc = str[0];
            Integer peso = parseInt(str[1]);
            String tipo_leitura = str[2];
            String tipo;

            if (tipo_leitura.equalsIgnoreCase("city")) {
                tipo = "CITY";
            } else if (tipo_leitura.equalsIgnoreCase("off-road")) {
                tipo = "OFF-ROAD";
            } else {
                tipo = "CITY";
            }

            Double latitude = parseDouble(str[3]);
            Double longitude = parseDouble(str[4]);
            Double capMax = parseDouble(str[5]);
            Integer capAtual = parseInt(str[6]);
            Double coefAerodinamico = parseDouble(str[7]);
            Double areaFrontal = parseDouble(str[8]);
            Integer potMotor = parseInt(str[9]);

            String id_parque;
            if (latitude != null || longitude != null) {
                id_parque = p.getParque(latitude, longitude).getIdParque();
            } else {
                id_parque = null;
            }

            Scooter veic = new Scooter(capMax, capAtual, tipo, potMotor, id_parque, desc, peso, areaFrontal, coefAerodinamico);
            scooters.add(veic);
        }
        boolean success = t.addAllVeiculos(scooters);
        if (success) {
            return strings.size();
        } else {
            return 0;
        }
    }

    public int carregarBicicletas(List<String[]> strings) {
        ArrayList<Veiculo> bcs = new ArrayList<>();

        for (String[] str : strings) {
            String desc = str[0];
            Integer peso = parseInt(str[1]);
            Double latitude = parseDouble(str[2]);
            Double longitude = parseDouble(str[3]);
            Double coefAero = parseDouble(str[4]);
            Double areaFrontal = parseDouble(str[5]);
            Integer tamanho = parseInt(str[6]);

            String id_parque;
            if (latitude != null || longitude != null) {
                Parque pk = p.getParque(latitude, longitude);
                id_parque = pk.getIdParque();
            } else {
                id_parque = null;
            }
            Bicicleta veic = new Bicicleta(tamanho, id_parque, desc, peso, areaFrontal, coefAero);
            bcs.add(veic);
        }
        boolean success = t.addAllVeiculos(bcs);
        if (success) {
            return strings.size();
        } else {
            return 0;
        }
    }

    /**
     *
     * @param descVeiculo
     * @return
     */
 public boolean removerVeiculo(String descVeiculo) {
		Veiculo veiculo = p.getVeiculo(descVeiculo);
		if (veiculo == null) {
			return false;
		}
		veiculo.setIdParque(null);
		veiculo.setRemovido(true);
		return salvarVeiculo(veiculo);
    }

	public boolean desassociarVeiculo(String descVeiculo) {
		Veiculo v = p.getVeiculo(descVeiculo);
		if (v == null) {
			return false;
		}
		v.setIdParque(null);
		return salvarVeiculo(v);
    }

  public boolean associarVeiculo(String descVeiculo, String idParque) {
		Veiculo veiculo = p.getVeiculo(descVeiculo);
		if (veiculo == null) {
			return false;
		}
		p.getParque(idParque);
		veiculo.setIdParque(idParque);
		return salvarVeiculo(veiculo);
	}

    public boolean salvarVeiculo(Veiculo v) {
        return v.save();
    }

    public void setPesquisa(Pesquisa p) {
        this.p = p;
    }

    public void setTransacao(Transacao t) {
        this.t = t;
    }

    /**
     * Veículos em Utilização
     *
     * @return
     */
    public List<String> veiculosEmUtilizacaoRelatorio() {
        try {
            return p.veiculosEmUtilizacaoRelatorio();
        } catch (IllegalArgumentException ex) {
            return null;
        }

    }

    /**
     * Retorna um map com os km e um set com as scooters com capacidade para
     * estes kms.
     *
     * @param idParque
     * @return
     */
    public Map<String, Set<String>> capacidadeVeiculoRelatorio(String idParque) {
        List<Scooter> veiculos = p.getScootersNumParque(idParque);
        Map<String, Set<String>> mapCapacidade = new TreeMap<>();
        Double velocidade = 20.0;
        veiculos.forEach((scooter) -> {

            Double capacidade = ((scooter.getCapacidadeMax().doubleValue() * 1000 * (scooter.getCargaAtual().doubleValue() / 100)) / (scooter.getPotMotor().doubleValue() )) * velocidade * 0.7;
            String km = String.format("%.2f km", capacidade);
            if (mapCapacidade.containsKey(km)) {
                mapCapacidade.get(km).add(scooter.getDescricao());
            } else {
                mapCapacidade.put(km, new HashSet<>());
                mapCapacidade.get(km).add(scooter.getDescricao());
            }
        });
        return mapCapacidade;
    }

    /**
     * Retorna um ArrayList de String com as scooters, carga atual e tempo
     * restante para carga.
     *
     * @param idParque
     * @return
     */
    public ArrayList<String> estadoCarregamentoRelatorio(String idParque) {
            ArrayList<String> lista = new ArrayList<>();
            SortedSet<Pair<Scooter, Double>> scootersOrd = getTempoCargaRestanteByParque(idParque);
            for (Pair<Scooter, Double> pair : scootersOrd) {
                String desc = pair.getKey().getDescricao();
                String type = pair.getKey().getTipoScooter();
                String cAtual = String.format("%d", pair.getKey().getCargaAtual());
                String tFalta = String.format("%.0f", pair.getValue());
                lista.add(desc + ";" + type + ";" + cAtual + ";" + tFalta);
            }
            return lista;
    }

 public SortedSet<Pair<Scooter, Double>> getTempoCargaRestanteByParque(String idParque) {

		SortedSet<Pair<Scooter, Double>> scootersOrd = new TreeSet<>((Pair<Scooter, Double> p1, Pair<Scooter, Double> p2) -> {
			double temp1 = p1.getValue();
			double temp2 = p2.getValue();
			if (temp1 < temp2) {
				return 1;
			} else if (temp1 > temp2) {
				return -1;
			} else {
				return p1.getKey().getDescricao().compareTo(p2.getKey().getDescricao());
			}
		});

		List<Scooter> scooters = p.getScootersNumParque(idParque);
		if (scooters == null) {
			return new TreeSet<>();
		}
		Integer qtdPontosCarga = scooters.size();
		Double potenciaParque = p.getParque(idParque).getPotenciaParque();
		Double distPotPorPontoCarga = potenciaParque / qtdPontosCarga;
		scooters.forEach((Scooter scooter) -> {
                    Pair<Scooter, Double> res = getTempoCargaRestante(scooter, distPotPorPontoCarga);
                    if(res != null){
                        scootersOrd.add(res);
                    }		
		});
		return scootersOrd;
	}

	public Pair<Scooter, Double> getTempoCargaRestante(Scooter s, Double distPontoCarga) {
		Double pontoCarga = 0.0;
		Double cargaRestante = s.getCargaRestante();
		if (cargaRestante == 0) {
			return null;
		} else {
			if (distPontoCarga > 3000) {
				pontoCarga = 3000.0;
			} else {
				pontoCarga = distPontoCarga;
			}
			// tempo que falta para carregar scooter em segundos
			Double tCarga = (cargaRestante / pontoCarga) * 3600;
			Pair<Scooter, Double> par = new Pair<>(s, tCarga);
			return par;
		}
	}

    public List<Scooter> getScooters(boolean removido) {
        return p.getScooters(removido);
    }

    public List<Bicicleta> getBicicletas(boolean removido) {
        return p.getBicicletas(removido);
    }

    public String getTipoVeiculoPorUsername(String string) {
        return p.getTipoVeiculoPorUser(string);

    }

}
