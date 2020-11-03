package lapr.project.model;

public class Bicicleta extends Veiculo {

    private Integer tamanho;

    public Bicicleta(Integer tamanho, String id_parque, String descricao, Integer peso, Double areaFrontal, Double coeficienteAerodinamico) {
        super(id_parque, descricao, peso, areaFrontal, coeficienteAerodinamico);
        this.tamanho = tamanho;
    }

    public Bicicleta() {
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }
}
