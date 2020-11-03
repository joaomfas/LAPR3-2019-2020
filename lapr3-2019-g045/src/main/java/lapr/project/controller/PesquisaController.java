package lapr.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;
import lapr.project.model.*;
import lapr.project.utils.Calculos;

public class PesquisaController {

    Pesquisa p = new Pesquisa();
    public static final int DEFAULT_RADIUS = 1; // 1 km é o default radius

    public List<String> parquesMaisProximos(double lat, double lon, int radius) {

        // lista de parques da BD
        List<Parque> parques = p.getParques();

        // adição e ordenação dos parques usando um sortedSet
        SortedSet<Parque> parquesOrd = new TreeSet<Parque>(new Comparator<Parque>() {
            @Override
            public int compare(Parque p1, Parque p2) {
                double dist1 = Calculos.distEntreDoisLocais(p1.getLatitude(), p1.getLongitude(), lat, lon);
                double dist2 = Calculos.distEntreDoisLocais(p2.getLatitude(), p2.getLongitude(), lat, lon);
                return Double.compare(dist1, dist2);
            }
        });

        parques.forEach(p -> {
            parquesOrd.add(p);
        });

        // formatação dos parques para escrita em ficheiro.
        List<String> sParques = new ArrayList<>();
        for (Parque p : parquesOrd) {
            double distance = Calculos.distEntreDoisLocais(p.getLatitude(), p.getLongitude(), lat, lon);
            double maxDist = radius * 1000;
            if (maxDist <= distance) {
                break;
            }
            String sLatitude = String.format(Locale.US, "%.5f", p.getLatitude());
            String sLongitude = String.format(Locale.US, "%.5f", p.getLongitude());
            String sDistance = String.format("%.0f", distance); // distance is in km. write to m
            String content = "";
            content = content + sLatitude + ";";
            content = content + sLongitude + ";";
            content = content + sDistance;
            sParques.add(content);
        }

        return sParques;
    }

    /**
     * If no radius is given, use one kilometer
     *
     * @param lat
     * @param lon
     * @return
     */
    public List<String> parquesMaisProximos(double lat, double lon) {
        return parquesMaisProximos(lat, lon, DEFAULT_RADIUS);
    }

    /**
     * *
     * Método retorna todas as bicicletas de um parque, dado o id
     *
     * @param id
     * @return
     */
    public List<String> bicicletasNumParque(String id) {
        List<String> sBicicletas = new ArrayList<>();
        List<Bicicleta> bicicletas = p.getBicicletasNumParque(id);
        for (Bicicleta b : bicicletas) {
            sBicicletas.add(b.getDescricao() + ";" + b.getTamanho());
        }
        return sBicicletas;
    }

    /**
     * *
     * Overload do método bicicletasNumParque(string id)
     *
     * @param lat
     * @param lon
     * @return
     */
    public List<String> bicicletasNumParque(double lat, double lon) {
        // procura pelo parque, se não encontrar retorna uma lista vazia
        Parque parque = p.getParque(lat, lon);
        if (parque == null) {
            return new ArrayList<>();
        }
        // encontrando o parque, chama a função principal
        String parqueId = parque.getIdParque();
        ArrayList<String> lista = new ArrayList<>(bicicletasNumParque(String.valueOf(parqueId)));
        Collections.sort(lista);
        return lista;
    }

    public List<Scooter> scootersParqueOrdenadosPorMaiorCarga(String id) {
        List<Scooter> scooters = p.getScootersNumParque(id);
        if (scooters == null) {
            return new ArrayList<>();
        }
        scooters.sort(new Comparator<Scooter>() {
            @Override
            public int compare(Scooter p1, Scooter p2) {
                double c1 = Double.valueOf(p1.getCargaAtual()) * Double.valueOf(p1.getCargaAtual());
                double c2 = Double.valueOf(p2.getCargaAtual()) * Double.valueOf(p2.getCargaAtual());
                return Double.compare(c2, c1);
            }
        });
        return scooters;
    }

    public double getDistanciaUtilizadorParque(double lat, double lon, String idParque) {

        Parque park = p.getParque(idParque);
        if (park == null) {
            return 0;
        }
        double plat = park.getLatitude();
        double plon = park.getLatitude();
        return Calculos.distEntreDoisLocais(plat, plon, lat, lon);
    }

    public Utilizador getUtilizadorByUsername(String username) {
        return p.getUtilizadorByUserName(username);
    }

    public Local getLocalComCoordenadas(Double lat, Double lon) {
        Parque pk = p.getParque(lat, lon);
        if (pk == null) {
            return p.getPOI(lat, lon);
        }
        return pk;
    }

    public void setP(Pesquisa p) {
        this.p = p;
    }

    public List<String> scootersNumParque(double d, double d1) {
        Parque parque = p.getParque(d, d1);
        if (parque == null) {
            return new ArrayList<>();
        }
        // encontrando o parque, chama a função principal
        String parqueId = parque.getIdParque();
        return scootersNumParque(String.valueOf(parqueId));
    }

    public List<String> scootersNumParque(String idParque) {
        List<String> sScooters = new ArrayList<>();
        List<Scooter> scooters = p.getScootersNumParque(idParque);
        for (Scooter b : scooters) {
            sScooters.add(b.getDescricao() + ";" + b.getTipoScooter() + ";" + b.getCargaAtual());
        }
        Collections.sort(sScooters);
        return sScooters;
    }
}
