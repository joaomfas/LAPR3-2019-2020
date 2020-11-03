package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.*;
import lapr.project.utils.Parsers;

public class CaminhosController {

    Transacao t = new Transacao();

    Pesquisa p = new Pesquisa();

    /**
     * Adiciona uma lista de caminhos ao sistema, retornando o número de
     * caminhos adicionados com sucesso. Os argumentos de um caminho passados
     * como parâmetro devem ser convertíveis em tipo double.
     *
     * @param paths
     * @return
     */
    public int adicionarCaminhos(List<String[]> paths) {
        ArrayList<Caminho> cms = new ArrayList<>();
//		try {
		    for (String[] path : paths) {
			    Double latA = Parsers.parseDouble(path[0]);
				Double lonA = Parsers.parseDouble(path[1]);
	            Double latB = Parsers.parseDouble(path[2]);
		        Double lonB = Parsers.parseDouble(path[3]);
			    Double coefCinetico = Parsers.parseDouble(path[4]);
				Double dirVento = Parsers.parseDouble(path[5]);
	            Double velVento = Parsers.parseDouble(path[6]);
		        Caminho c = new Caminho(latA, lonA, latB, lonB,
			            coefCinetico, dirVento, velVento);
				cms.add(c);
			}
//		} catch (Exception ex) {
//            Logger.getLogger(CaminhosController.class.getName()).log(Level.SEVERE, null, ex);
//			return 0;
//		}
        boolean success = t.addAllCaminhos(cms);
        if (success) {
            return paths.size();
        } else {
            return 0;
        }
    }

    public boolean removerCaminho(double lat1, double lon1, double lat2, double lon2) {
        try {
            Caminho c = p.getCaminho(lat1, lon1, lat2, lon2);
            return removerCaminho(c);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removerCaminho(Caminho c) {
        return c.delete();
    }

    /**
     * *
     * Tenta salvar o objeto Caminho criado
     *
     * @param c
     * @return
     */
    public boolean salvarCaminho(Caminho c) {
        return c.save();
    }
    
    public boolean atualizarInfoVento(double lat1, double lon1, double lat2, double lon2, double velVento, double dirVento){
            Caminho c = p.getCaminho(lat1, lon1, lat2, lon2);
            if(c == null){
                return false;
            }
            c.setVelVento(velVento);
            c.setDirVento(dirVento);
            return salvarCaminho(c);
    }

    public void setTransacao(Transacao t) {
        this.t = t;
    }
}
