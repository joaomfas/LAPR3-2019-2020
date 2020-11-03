package lapr.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import lapr.project.model.*;
import static lapr.project.utils.Parsers.parseDouble;
import static lapr.project.utils.Parsers.parseInt;

public class RotasController {

    private static final int NUM_PATHS_DEFAULT = 100;
    Pesquisa p = new Pesquisa();
    Rotas r = new Rotas();

    /**
     * Retorna distância da rota mais curta entre dois parques
     *
     * @param latA
     * @param lonA
     * @param latB
     * @param lonB
     * @return
     */
    public LinkedList<Local> rotaMaisCurta(double latA, double lonA, double latB, double lonB) {
        Parque pOrig = p.getParque(latA, lonA);
        Parque pDest = p.getParque(latB, lonB);
        if (pOrig == null || pDest == null) {
            return new LinkedList<>();
        }
        LinkedList<Local> rotaMaisCurta = r.rotaMaisCurta(pOrig, pDest);
        return rotaMaisCurta;
    }

    public LinkedList<Local> rotaMaisCurta(String oParkId, String dParkId) {
        Parque pOrig = p.getParque(oParkId);
        Parque pDest = p.getParque(dParkId);
        if (pOrig == null || pDest == null) {
            return new LinkedList<>();
        }
        LinkedList<Local> rotaMaisCurta = r.rotaMaisCurta(pOrig, pDest);
        return rotaMaisCurta;
    }

    /**
     * Retorna a rota mais eficiente energeticamente entre dois parques specs:
     * weight;aerodynamic coefficient;frontal area
     *
     * @param orig
     * @param dest
     * @param typeOfVehicle
     * @param vehicleSpecs
     * @param username
     * @param rota
     * @return
     */
    public LinkedList<Local> rotaMaisEficiente(String orig,
            String dest,
            String typeOfVehicle,
            String vehicleSpecs,
            String username) {
        Utilizador u = p.getUtilizadorByUserName(username);
        Veiculo v = p.getVeiculoComTipoESpecs(typeOfVehicle, vehicleSpecs);
        Parque pOrig = p.getParque(orig);
        Parque pDest = p.getParque(dest);
        if (pOrig == null || pDest == null) {
            return new LinkedList<>();
        }
        LinkedList<Local> rotaMaisEficiente = r.rotaMaisEficiente(pOrig, pDest, v, u);
        return rotaMaisEficiente;
    }

    /**
     * Retorna a distância de uma rota
     *
     * @param rota
     * @return
     */
    public long distanciaRota(List<Local> rota) {
        long dist = (long) r.distanciaParaUmaRota(rota);
        return dist;
    }

    /**
     * Retorna a distância de uma rota
     *
     * @param rota
     * @return
     */
    public double energiaRota(List<Local> rota) {
        return r.energiaParaUmaRota(rota);
    }

    /**
     * Converte uma lista de locais em uma string para escrita em ficheiro
     *
     * @param rota
     * @return
     */
    public String rotaToString(List<Local> rota) {
        String sRota = "";
        for (Local l : rota) {
            sRota = sRota
                    + String.format(Locale.US, "%.6f", l.getLatitude())
                    + ";"
                    + String.format(Locale.US, "%.6f", l.getLongitude())
                    + "\n";
        }
        return sRota;
    }

    /**
     * *
     * Retorna uma string contendo o conteúdo de paths a escrever em ficheiro
     *
     * @param rotas
     * @return
     */
    public String rotaToFileString(List<LinkedList<Local>> rotas) {
        String content = "";
        int pathNum = 1;
        for (LinkedList<Local> rota : rotas) {
            if (!rota.isEmpty()) {
                int elev = rota.get(0).getElevacao() - rota.get(rota.size() - 1).getElevacao();
                double dist = this.distanciaRota(rota);
                String sRota = this.rotaToString(rota);
                double energy = this.energiaRota(rota);
                content = content
                        + "Path " + String.format(Locale.US, "%03d", pathNum)
                        + "\ntotal_distance:" + String.format(Locale.US, "%.0f", dist)
                        + "\ntotal_energy:" + String.format(Locale.US, "%.2f", energy)
                        + "\nelevation:" + elev
                        + "\n" + sRota;
                pathNum++;
            }
        }
        return content;
    }

    /**
     * Converte uma lista de listas de locais para uma lista de string para
     * escrita em ficheiro
     *
     * @param rotas
     * @return
     */
    public List<String> rotasToString(List<List<Local>> rotas) {
        List<String> sRotas = new ArrayList<>();
        rotas.forEach(r -> {
            sRotas.add(rotaToString(r));
        });
        return sRotas;
    }

    public List<LinkedList<Local>> rotasMaisCurtasComPOIS(double latA, double lonA,
            double latB, double lonB,
            List<String[]> sPois) {
        List<Local> pois = parsePOI(sPois);
        Local orig = p.getParque(latA, lonA);
        Local dest = p.getParque(latB, lonB);
        if (orig == null || dest == null) {
            return new LinkedList<>();
        }
        List<LinkedList<Local>> rota = r.rotasMaisCurtasComPOIS(orig, dest, pois, NUM_PATHS_DEFAULT);
        return rota;

    }

    public List<LinkedList<Local>> rotasMaisCurtasComPOIS(String origParqueId,
            String destParqueId,
            List<String[]> sPois) {
        List<Local> pois = parsePOI(sPois);
        Local orig = p.getParque(origParqueId);
        Local dest = p.getParque(destParqueId);
        if (orig == null || dest == null) {
            return new LinkedList<>();
        }
        List<LinkedList<Local>> rota = r.rotasMaisCurtasComPOIS(orig, dest, pois, NUM_PATHS_DEFAULT);
        return rota;
    }

    public List<Local> parsePOI(List<String[]> sPois) {
        List<Local> pois = new ArrayList<>();
        for (String[] str : sPois) {
            try {
                Double latitude = parseDouble(str[0]);
                Double longitude = parseDouble(str[1]);
                Integer elevacao = parseInt(str[2]);
                String descricao = str[3];
                POI poi = new POI(latitude, longitude, elevacao, descricao);
                pois.add(poi);
            } catch (IndexOutOfBoundsException ex) {

            }
        }
        return pois;
    }

    /**
     * Calculate the most energetically efficient route from one park to another
     * with sorting options.
     *
     * @param originParkIdentification - Origin Park Identification.
     * @param destinationParkIdentification - Destination Park Identification.
     * @param typeOfVehicle - The type of vehicle required e.g. "bicycle" or
     * "escooter".
     * @param vehicleSpecs - The specs for the vehicle e.g. "16", "19", "27" or
     * any other number for bicyles and "city" or "off-road" for any escooter.
     * @param username - The user that asked for the routes.
     * @param maxNumberOfSuggestions - The maximum number of suggestions to
     * provide.
     * @param ascendingOrder - If routes should be ordered by ascending or
     * descending order
     * @param sortingCriteria - The criteria to use for ordering "energy",
     * "shortest_distance", "number_of_points".
     * @param inputPOIs - Path to file that contains the POIs that the route
     * must go through, according to file input/pois.csv. By default, the file
     * is empty.
     * @param outputFileName - Write to the file the Route between two parks
     * according to file output/paths.csv. More than one path may exist.
     * @return The number of suggestions
     *
     */
    public List<LinkedList<Local>> routesBetweenTwoLocations(String originParkIdentification,
            String destinationParkIdentification,
            String typeOfVehicle,
            String vehicleSpecs,
            String username,
            int maxNumberOfSuggestions,
            boolean ascendingOrder,
            String sortingCriteria,
            List<String[]> inputPOIs,
            String outputFileName) {
        List<Local> pois = parsePOI(inputPOIs);

        Local orig = p.getParque(originParkIdentification);
        Local dest = p.getParque(destinationParkIdentification);
        Veiculo v = p.getVeiculoComTipoESpecs(typeOfVehicle, vehicleSpecs);
        Utilizador u = p.getUtilizadorByUserName(username);
        if (orig == null || dest == null) {
            return new ArrayList<>();
        }
        ArrayList<LinkedList<Local>> result;
        switch (sortingCriteria) {
            case "energy":
                result = new ArrayList<>(r.rotasMaisEficientesComPOIS(orig, dest, v, u, pois, maxNumberOfSuggestions));
                break;
            case "shortest_distance":
                result = new ArrayList<>(r.rotasMaisCurtasComPOIS(orig, dest, pois, maxNumberOfSuggestions));
                break;
            case "number_of_points":
                result = new ArrayList<>(r.rotasComMaisPOIS(orig, dest, pois, maxNumberOfSuggestions));
                break;
            default:
                return new ArrayList<>();
        }

        if (ascendingOrder) {
            LinkedList<LinkedList<Local>> ascendingOrderList = new LinkedList<>();
            result.forEach(l -> {
                ascendingOrderList.addFirst(l);
            });
            return ascendingOrderList;
        }
        return result;
    }

    public void setP(Pesquisa p) {
        this.p = p;
    }

    public void setR(Rotas r) {
        this.r = r;
    }

}
