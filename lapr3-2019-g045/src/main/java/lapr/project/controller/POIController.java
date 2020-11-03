package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.*;
import static lapr.project.utils.Parsers.*;

public class POIController {

    Pesquisa pb  = new Pesquisa();

    Transacao t = new Transacao();

    public int carregarPOIs(List<String[]> strings) {
        ArrayList<POI> pois = new ArrayList<>();

        for (String[] str : strings) {
            Double latitude = parseDouble(str[0]);
            Double longitude = parseDouble(str[1]);
            Integer elevacao = parseInt(str[2]);
            String descricao = str[3];

            POI poi = new POI(latitude, longitude, elevacao, descricao);

            pois.add(poi);
        }
        boolean success = t.addAllPOI(pois);
        if (success) {
            return strings.size();
        } else {
            return 0;
        }
    }

    public boolean salvarPOI(POI poi) {
        return poi.save();
    }

    public boolean removerPOI(Double lat, Double lon) {
        try {
            POI poi = pb.getPOI(lat, lon);
            return removerPOI(poi);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    public boolean removerPOI(POI poi) {
        return poi.remove();
    }

    public void setPesquisa(Pesquisa p) {
        this.pb = p;
    }

    public void setTransacao(Transacao t) {
        this.t = t;

    }
}
