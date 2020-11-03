package lapr.project.model;

import java.util.Objects;
import lapr.project.data.CaminhoBD;

public class Caminho {

    private Double latA;
    private Double lonA;
    private Double latB;
    private Double lonB;
    private Double coefCinetico;
    private Double dirVento;
    private Double velVento;
    private CaminhoBD bd = new CaminhoBD();

    public Caminho() {
    }

    public Caminho(Double latA, Double lonA, Double latB, Double lonB, Double coefCinetico, Double dirVento, Double velVento) {
        this.latA = latA;
        this.lonA = lonA;
        this.latB = latB;
        this.lonB = lonB;
        this.coefCinetico = coefCinetico;
        this.dirVento = dirVento;
        this.velVento = velVento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.latA);
        hash = 17 * hash + Objects.hashCode(this.lonA);
        hash = 17 * hash + Objects.hashCode(this.latB);
        hash = 17 * hash + Objects.hashCode(this.lonB);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.hashCode() == this.hashCode();
    }


    /**
     * *
     * Salva o objeto na BD. Caso já exista, faz update. Se não existe, é
     * adicionado. Retorna o sucesso da respectiva operação.
     *
     * @return
     */
    public boolean save() {
        try {
            bd.getCaminho(this.latA, this.lonA, this.latB, this.lonB);
        } catch (IllegalArgumentException e) {
            return bd.addCaminho(this);
        }
        return bd.updateCaminho(this);
    }

    public Double getLatA() {
        return latA;
    }

    public void setLatA(Double latA) {
        this.latA = latA;
    }

    public Double getLonA() {
        return lonA;
    }

    public void setLonA(Double lonA) {
        this.lonA = lonA;
    }

    public Double getLatB() {
        return latB;
    }

    public void setLatB(Double latB) {
        this.latB = latB;
    }

    public Double getLonB() {
        return lonB;
    }

    public void setLonB(Double lonB) {
        this.lonB = lonB;
    }

    public Double getCoefCinetico() {
        return coefCinetico;
    }

    public void setCoefCinetico(Double coefCinetico) {
        this.coefCinetico = coefCinetico;
    }

    public Double getDirVento() {
        return dirVento;
    }

    public void setDirVento(Double dirVento) {
        this.dirVento = dirVento;
    }

    public Double getVelVento() {
        return velVento;
    }

    public void setVelVento(Double velVento) {
        this.velVento = velVento;
    }
    

    public void setBd(CaminhoBD bd) {
        this.bd = bd;
    }

    public boolean delete() {
        try {
            return bd.removeCaminho(this);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
