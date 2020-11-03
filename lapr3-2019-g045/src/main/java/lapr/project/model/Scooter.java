package lapr.project.model;

public class Scooter extends Veiculo {

    private Double capacidadeMax;
    private Integer cargaAtual;
    private String tipoScooter;
    private Integer potMotor;

    public Scooter(Double capacidadeMax, Integer cargaAtual, String tipoScooter, Integer potMotor, String idParque, String descricao, Integer peso, Double areaFrontal, Double coeficienteAerodinamico) {
        super(idParque, descricao, peso, areaFrontal, coeficienteAerodinamico);
        this.capacidadeMax = capacidadeMax;
        this.cargaAtual = cargaAtual;
        this.tipoScooter = tipoScooter;
        this.potMotor = potMotor;
    }

    public Scooter() {
    }

    public Double getCapacidadeMax() {
        return capacidadeMax;
    }

    public void setCapacidadeMax(Double capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }

    public Integer getCargaAtual() {
        return cargaAtual;
    }

    public void setCargaAtual(Integer cargaAtual) {
        this.cargaAtual = cargaAtual;
    }

    public String getTipoScooter() {
        return tipoScooter;
    }

    public void setTipoScooter(String tipoScooter) {
        this.tipoScooter = tipoScooter;
    }

    public Integer getPotMotor() {
        return potMotor;
    }

    public void setPotMotor(Integer potMotor) {
        this.potMotor = potMotor;
    }

    public Double getCargaRestante() {
        Double x = this.capacidadeMax.doubleValue() * 1000; // 100.000
        Double y = (this.cargaAtual.doubleValue() / 100); // 0,2
        Double cargaAtualEmW = x * y; // 20.000
        return (x - cargaAtualEmW); // 80.000
    }
}
