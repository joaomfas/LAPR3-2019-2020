package lapr.project.model;

import java.util.Objects;
import lapr.project.data.*;

public class POI implements Local {

    private Double latitude;
    private Double longitude;
    private Integer elevacao;
    private String descricao;
    private POIBD bd = new POIBD();

    public POI(Double latitude, Double longitude, Integer elevacao, String descricao) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevacao = elevacao;
        this.descricao = descricao;
    }

    public POI() {
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getElevacao() {
        return elevacao;
    }

    public void setElevacao(Integer elevacao) {
        this.elevacao = elevacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.descricao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    public boolean save() {
        try {
            bd.getPOI(this.latitude, this.longitude);
        } catch (IllegalArgumentException ex) {
            return bd.addPOI(this);
        }
        return false;
    }

    public boolean remove() {
        try {
            return bd.removePOI(this);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public void setBd(POIBD bd) {
        this.bd = bd;
    }

    @Override
    public String toString() {
        if (descricao != null) {
            return String.valueOf(this.descricao);
        }
        return "";
    }

}
