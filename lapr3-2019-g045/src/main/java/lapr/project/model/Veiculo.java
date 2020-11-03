package lapr.project.model;

import java.util.Objects;
import lapr.project.data.VeiculoBD;

public abstract class Veiculo {

    private String idParque;
    private String descricao;
    private Integer peso;
    private Double areaFrontal;
    private Double coeficienteAerodinamico;
    private Boolean removido;
    private VeiculoBD bd = new VeiculoBD();

    public Veiculo(String idParque, String descricao, Integer peso, Double areaFrontal, Double coeficienteAerodinamico) {
        this.idParque = idParque;
        this.descricao = descricao;
        this.peso = peso;
        this.areaFrontal = areaFrontal;
        this.coeficienteAerodinamico = coeficienteAerodinamico;
        this.removido = false;
    }

    public Veiculo() {
    }

    public void setIdParque(String idParque) {
        this.idParque = idParque;
    }

    public String getIdParque() {
        return idParque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Double getAreaFrontal() {
        return areaFrontal;
    }

    public void setAreaFrontal(double areaFrontal) {
        this.areaFrontal = areaFrontal;
    }

    public Double getCoeficienteAerodinamico() {
        return coeficienteAerodinamico;
    }

    public void setCoeficienteAerodinamico(double coeficienteAerodinamico) {
        this.coeficienteAerodinamico = coeficienteAerodinamico;
    }

    public Boolean getRemovido() {
        return removido;
    }

    public void setRemovido(Boolean removido) {
        this.removido = removido;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.descricao);
        return hash;
    }

    public boolean save() {
        try {
            bd.getVeiculo(this.getDescricao());
        } catch (IllegalArgumentException ex) {
            return bd.addVeiculo(this);
        }
        return bd.updateVeiculo(this);
    }

    public void setBd(VeiculoBD bd) {
        this.bd = bd;
    }

    @Override
    public String toString() {
        if(descricao != null){
            return String.valueOf(this.descricao);
        }
        return "";
    }

}
