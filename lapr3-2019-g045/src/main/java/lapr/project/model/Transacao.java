package lapr.project.model;

import java.util.ArrayList;
import lapr.project.data.CaminhoBD;
import lapr.project.data.POIBD;
import lapr.project.data.ParqueBD;
import lapr.project.data.UtilizadorBD;
import lapr.project.data.VeiculoBD;

public class Transacao {

    ParqueBD pqbd = new ParqueBD();
    VeiculoBD vbd = new VeiculoBD();
    CaminhoBD cbd = new CaminhoBD();
    POIBD poibd = new POIBD();
    UtilizadorBD ubd = new UtilizadorBD();

    public boolean addAllParques(ArrayList<Parque> pqs) {
        return pqbd.addAll(pqs);
    }

    public boolean addAllVeiculos(ArrayList<Veiculo> vcs) {
        return vbd.addAll(vcs);
    }
    
    public boolean addAllCaminhos(ArrayList<Caminho> cms) {
        return cbd.addAll(cms);
    }
    
    public boolean addAllPOI(ArrayList<POI> pois){
        return poibd.addAll(pois);
    }
    
    public boolean addAllUsers(ArrayList<Utilizador> users){
        return ubd.addAll(users);
    }

    public void setPqbd(ParqueBD pqbd) {
        this.pqbd = pqbd;
    }

    public void setVbd(VeiculoBD vbd) {
        this.vbd = vbd;
    }

    public void setCbd(CaminhoBD cbd) {
        this.cbd = cbd;
    }

    public void setPoibd(POIBD poibd) {
        this.poibd = poibd;
    }

    public void setUbd(UtilizadorBD ubd) {
        this.ubd = ubd;
    }
    
    
}
