package lapr.project.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import lapr.project.utils.*;

/**
 * Esta classe gera uma estrutura de grafos que permite gerar rotas entre
 * objetos que implementem Local.
 *
 * @author breno
 */
public class Rotas {

    private enum tipoPesquisa {
        DISTANCIA,
        ENERGIA;
    }

    boolean isLoaded = false;
    Pesquisa p = new Pesquisa();
    Graph<Local, Caminho> g;
    List<Caminho> caminho;
    List<Parque> parques;
    List<POI> pois;
    Utilizador u = null;
    Veiculo v = null;

    // mapa dos locais utilizando como chave um hash
    Map<BigDecimal, Local> mapLocais;

    public void loadGraph() {
        // gera o grafo orientado
        g = new Graph<>(true);

        // puxa os parques, pois e caminhos
        caminho = p.getCaminhos();
        parques = p.getTodosParques(); // mesmo os inativos
        pois = p.getPOIs();

        // adiciona parques e pois ao set de locais
        Set<Local> locais = new HashSet<Local>();
        locais.addAll(parques);
        locais.addAll(pois);

        // adiciona os vertex do grafo
        locais.forEach(l -> {
            g.insertVertex(l);
        });

        // cria um map utilizando a latitude e longitude do local como chave
        mapLocais = new HashMap<>();
        locais.forEach(l -> {
            BigDecimal key = hashLocal(l.getLatitude(), l.getLongitude());
            mapLocais.put(key, l);
        });

        // adiciona os caminhos (edges) sem peso.
        caminho.forEach(c -> {
            double latA = c.getLatA();
            double latB = c.getLatB();
            double lonA = c.getLonA();
            double lonB = c.getLonB();
            Local vOrig = mapLocais.get(hashLocal(latA, lonA));
            Local vDest = mapLocais.get(hashLocal(latB, lonB));
            Double peso = 0.0;
            g.insertEdge(vOrig, vDest, c, peso);
        });
		
		this.isLoaded = true;
    }

    /**
     * Calcula a distância total de uma rota
     *
     * @param rota
     * @return
     */
    public double distanciaParaUmaRota(List<Local> rota) {
		if(!isLoaded) {
			loadGraph();
		}
        updatePesos(tipoPesquisa.DISTANCIA);
        return GraphAlgorithms.pathWeigth(rota, g);
    }

    /**
     * Calcula a energia total de uma rota
     *
     * @param rota
     * @param u
     * @param v
     * @return
     */
    public double energiaParaUmaRota(List<Local> rota, Utilizador u, Veiculo v) {
		if(!isLoaded) {
			loadGraph();
		}
        this.u = u;
        this.v = v;
        updatePesos(tipoPesquisa.ENERGIA);
        return GraphAlgorithms.pathWeigth(rota, g);
    }
	

    /**
     * Calcula a energia total de uma rota
     *
     * @param rota
     * @param u
     * @param v
     * @return
     */
    public double energiaParaUmaRota(List<Local> rota) {
		if(!isLoaded) {
			loadGraph();
		}

        updatePesos(tipoPesquisa.ENERGIA);
        return GraphAlgorithms.pathWeigth(rota, g);
    }


    /**
     * Retorna a rota mais curta entre dois parques
     *
     * @return
     */
    public LinkedList<Local> rotaMaisCurta(Local orig, Local dest) {
		if(!isLoaded) {
			loadGraph();
		}
        updatePesos(tipoPesquisa.DISTANCIA);
        LinkedList<Local> path = new LinkedList<>();
        GraphAlgorithms.shortestPath(g, orig, dest, path);
        return path;
    }

    /**
     * Retorna a rota mais eficiente energeticamente entre dois parques
     *
     * @return
     */
    public LinkedList<Local> rotaMaisEficiente(Local orig, Local dest, Veiculo v, Utilizador u) {
		if(!isLoaded) {
			loadGraph();
		}
        this.v = v;
        this.u = u;
        updatePesos(tipoPesquisa.ENERGIA);
        LinkedList<Local> path = new LinkedList<>();
        GraphAlgorithms.shortestPath(g, orig, dest, path);
		
        return path;
    }

    /**
     * Retorna uma lista de trajetos (vertex podem ser repetidos) que tem como
     * origem um Local origem e um Local destino. Os trajetos passam
     * obrigatoriamente por uma de Locais "between". É retornada uma rota para
     * cada combinação possível de Locais between. A lista de rotas está
     * ordenada em ordem crescente da distância total do trajeto. As rotas são
     * fornecidas como LinkedLists de Locais. Se for de interesse saber a
     * distância total de cada rota, chamar o método distanciaParaUmaRota
     *
     * @param orig
     * @param dest
     * @param between
     * @param n
     * @return
     */
    public List<LinkedList<Local>> rotasMaisCurtasComPOIS(Local orig, Local dest, List<Local> between, int n) {
		if(!isLoaded) {
			loadGraph();
		}
        updatePesos(tipoPesquisa.DISTANCIA);
        List<LinkedList<Local>> paths = GraphAlgorithms.pathsWithConstraints(g, orig, dest, between, n);
        return paths;
    }

    /**
     * Retorna uma lista de trajetos simples que tem como origem um Local origem
     * e um Local destino. Os trajetos passam obrigatoriamente por uma de Locais
     * "between". São consideradas todas as rotas possíveis no grafo. A lista de
     * rotas está ordenada em ordem crescente da distância total do trajeto. As
     * rotas são fornecidas como LinkedLists de Locais. Se for de interesse
     * saber a distância total de cada rota, chamar o método
     * distanciaParaUmaRota
     *
     * @param orig
     * @param dest
     * @param between
     * @param n
     * @return
     */
    public List<LinkedList<Local>> todasRotasMaisCurtasComPOIS(Local orig, Local dest, List<Local> between, int n) {
		if(!isLoaded) {
			loadGraph();
		}
        updatePesos(tipoPesquisa.DISTANCIA);
        List<LinkedList<Local>> paths = GraphAlgorithms.allPathsWithConstraints(g, orig, dest, between, n);
        return paths;
    }

    /**
     * Retorna uma lista de trajetos (vertex podem ser repetidos) que tem como
     * origem um Local origem e um Local destino. Os trajetos passam
     * obrigatoriamente por uma de Locais "between". É retornada uma rota para
     * cada combinação possível de Locais between. A lista de rotas está
     * ordenada em ordem crescente da distância total do trajeto. As rotas são
     * fornecidas como LinkedLists de Locais. Se for de interesse saber a
     * distância total de cada rota, chamar o método energiaParaUmaRota
     *
     * @param orig
     * @param dest
     * @param between
     * @param n
     * @return
     */
    public List<LinkedList<Local>> rotasMaisEficientesComPOIS(Local orig, Local dest, Veiculo v, Utilizador u, List<Local> between, int n) {
		if(!isLoaded) {
			loadGraph();
		}
        updatePesos(tipoPesquisa.ENERGIA);
        List<LinkedList<Local>> paths = GraphAlgorithms.pathsWithConstraints(g, orig, dest, between, n);
        return paths;
    }

    /**
     * Retorna uma lista de trajetos simples que tem um Local origem e um Local
     * destino. Os trajetos passam obrigatoriamente por uma de Locais "between".
     * São consideradas todas os trajetos simples possíveis. A lista de rotas
     * está ordenada em ordem crescente da distância total do trajeto. As rotas
     * são fornecidas como LinkedLists de Locais. Se for de interesse saber a
     * distância total de cada rota, chamar o método distanciaParaUmaRota
     *
     * @param orig
     * @param dest
     * @param between
     * @param n
     * @return
     */
    public List<LinkedList<Local>> todasRotasMaisEficientesComPOIS(Local orig, Local dest, Veiculo v, Utilizador u, List<Local> between, int n) {
		if(!isLoaded) {
			loadGraph();
		}
        updatePesos(tipoPesquisa.ENERGIA);
        List<LinkedList<Local>> paths = GraphAlgorithms.allPathsWithConstraints(g, orig, dest, between, n);
        return paths;
    }

    /**
     * Atualiza o peso das arestas do grafo conforme o tipo de pesquisa
     * realizada
     *
     * @param tipo
     */
    private void updatePesos(tipoPesquisa tipo) {
		if(!isLoaded) {
			loadGraph();
		}
        // p/ cada edge, faz set do peso
        for (Edge<Local, Caminho> e : g.edges()) {
            double peso = 0.0;

            // calcula o peso conforme tipo de pesquisa
            if (tipo == tipoPesquisa.DISTANCIA) {
                double latA = e.getVOrig().getLatitude();
                double lonA = e.getVOrig().getLongitude();
                double latB = e.getVDest().getLatitude();
                double lonB = e.getVDest().getLongitude();
                peso = Calculos.distEntreDoisLocais(latA, lonA, latB, lonB);
            } else if (tipo == tipoPesquisa.ENERGIA) {
                Caminho c = e.getElement();
                Local vOrig = e.getVOrig();
                Local vDest = e.getVDest();
                peso = Calculos.energiaEntreLocais(c, u, v, vOrig, vDest);
            }
            e.setWeight(peso);
        }
    }
	
	/**
	 * Utiliza o allpaths para gerar todas as rotas do grafo com uma origem e um destino
	 * e uma lista pois. O resultado é ordenado pelo número de POIS.
	 * @param orig
	 * @param dest
	 * @param pois
	 * @param maxNumberOfSuggestions
	 * @return 
	 */
	public List<LinkedList<Local>> rotasComMaisPOIS(Local orig, Local dest, List<Local> pois, int maxNumberOfSuggestions) {
		List<LinkedList<Local>> rotas = this.rotasMaisCurtasComPOIS(orig, dest, pois, maxNumberOfSuggestions);
		rotas.sort(new Comparator<LinkedList<Local>>(){
			@Override
			public int compare(LinkedList<Local> o1, LinkedList<Local> o2) {
				return Integer.compare(o1.size(), o2.size());
			}
		});
		return rotas;
	}

    /**
     * Gera uma hash para a latitude e longitude do Local. Utilizada para a
     * construção do grafo
     *
     * @param lat
     * @param lon
     * @return
     */
    private BigDecimal hashLocal(double lat, double lon) {
        BigDecimal lat1 = BigDecimal.valueOf(lat);
        BigDecimal lon1 = BigDecimal.valueOf(lon);
        BigDecimal mult = BigDecimal.valueOf(79);
        BigDecimal hash = lat1.multiply(mult).add(lon1.add(mult));
        return hash;
    }

    public void setPesquisa(Pesquisa p) {
        this.p = p;
    }
}
