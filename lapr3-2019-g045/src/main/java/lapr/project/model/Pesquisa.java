package lapr.project.model;

// classe com métodos de pesquisa
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.*;

public class Pesquisa {

    private ParqueBD pbd = new ParqueBD();
    private POIBD poibd = new POIBD();
    private CaminhoBD cbd = new CaminhoBD();
    private VeiculoBD vbd = new VeiculoBD();
    private ViagemBD vibd = new ViagemBD();
    private UtilizadorBD ubd = new UtilizadorBD();
    private FaturaBD fbd = new FaturaBD();

    public List<Parque> getParques() {
        return pbd.getParques(true);
    }

    public List<Parque> getTodosParques() {
        return pbd.getParques(false);
    }

    public List<Bicicleta> getBicicletas(boolean removido) {
        return vbd.getBicicletas(removido);
    }

    public List<Scooter> getScooters(boolean removido) {
        return vbd.getScooters(removido);
    }

    public List<Caminho> getCaminhos() {
        return cbd.getCaminhos();
    }

    public Caminho getCaminho(double lat1, double lon1, double lat2, double lon2) {
        try {
            return cbd.getCaminho(lat1, lon1, lat2, lon2);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<POI> getPOIs() {
        return poibd.getPOIs();
    }

    public List<Bicicleta> getBicicletasNumParque(String id) {
        try {
            return vbd.getBicicletasNumParque(id);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Scooter> getScootersNumParque(String id) {
        try {
            return vbd.getScootersNumParque(id);
        } catch (Exception ex) {
            return null;
        }
    }

    public Parque getParque(String id) {
        try {
            return pbd.getParque(id);
        } catch (Exception ex) {
            return null;
        }
    }

    public Parque getParque(double lat, double lon) {
        try {
            return pbd.getParque(lat, lon);
        } catch (Exception ex) {
            return null;
        }
    }

    public Veiculo getVeiculo(String descVeiculo) {
        try {
            return vbd.getVeiculo(descVeiculo);
        } catch (Exception ex) {
            return null;
        }
    }

    public POI getPOI(Double lat, Double lon) {
        try {
            return poibd.getPOI(lat, lon);
        } catch (Exception ex) {
            return null;
        }
    }

    public Utilizador getUtilizador(int idUtilizador) {
        try {
            return ubd.getUtilizador(idUtilizador);
        } catch (Exception ex) {
            return null;
        }
    }



    public Utilizador getUtilizadorByUserName(String userName) {
        try {
            return ubd.getUtilizadorByUserName(userName);
        } catch (Exception ex) {
            return null;
        }
    }

    public Viagem getViagemUtilizador(int id_utilizador) {
        try {
            return vibd.getViagemByUtilizador(id_utilizador);
        } catch (Exception ex) {
            return null;
        }
    }

    public Viagem getViagemVeiculo(String descVeiculo) {
        try {
            return vibd.getViagemByVeiculo(descVeiculo);
        } catch (Exception ex) {
            return null;
        }
    }

    public int verificaLugaresDisponiveis(String id, String username) {
        try {
            return pbd.verificaLugaresDisponiveis(id, username);
        } catch (Exception ex) {
            return 0;
        }
    }

    public List<String> veiculosEmUtilizacaoRelatorio() {
        return vbd.veiculosEmUtilizacao();
    }

    public List<Scooter> getScooterComCargaSuficiente(Double carga, Parque parque) {
        try {
            return vbd.scooterComCargaSuficiente(carga, parque);
        } catch (Exception ex) {
            return null;
        }
    }

    public Fatura getFatura(String username, Integer mes) {
        try {
            return fbd.getFatura(username, mes);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<String> getDetalheFatura(Fatura fatura) {
        try {
            return fbd.getDetalheFatura(fatura);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public List<String> getDetalheAtual(String username) {
        try {
            return fbd.getDetalheAtual(username);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public List<String> getDetalhePontos(String username) {
        try {
            return fbd.getDetalhePontos(username);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public List<Fatura> getFaturasUtilizador(String username) {
        try {
            return fbd.getFaturasUtilizador(username);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public String getTipoVeiculoPorUser(String username) {
        try {
            return vbd.getTipoVeiculoPorUser(username);
        } catch (Exception ex) {
            return null;
        }
    }

    public Veiculo getVeiculoComTipoESpecs(String typeOfVehicle, String vehicleSpecs) {
        try {
            return vbd.getVeiculoComTipoESpecs(typeOfVehicle, vehicleSpecs);
        } catch (Exception ex) {
            return null;
        }
    }

    // SETTERS
    public void setPbd(ParqueBD pbd) {
        this.pbd = pbd;
    }

    public void setPoibd(POIBD poibd) {
        this.poibd = poibd;
    }

    public void setCbd(CaminhoBD cbd) {
        this.cbd = cbd;
    }

    public void setVbd(VeiculoBD vbd) {
        this.vbd = vbd;
    }

    public void setVibd(ViagemBD vibd) {
        this.vibd = vibd;
    }

    public void setUbd(UtilizadorBD ubd) {
        this.ubd = ubd;
    }

    public void setFdb(FaturaBD fbd) {
        this.fbd = fbd;
    }

}
