package lapr.project.assessment;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lapr.project.controller.*;
import lapr.project.data.BDUtils;
import lapr.project.data.FileUtils;
import lapr.project.model.*;
import lapr.project.utils.Calculos;

public class Facade implements Serviceable {

    BDUtils bdutil = new BDUtils();

    public Facade() {
        bdutil.startBDFacade();
    }

    VeiculoController veiculoController = new VeiculoController();
    ParqueController parqueController = new ParqueController();
    UtilizadorController utilizadorController = new UtilizadorController();
    POIController poiController = new POIController();
    ViagemController viagemController = new ViagemController();
    PesquisaController pesquisaController = new PesquisaController();
    Pesquisa p = new Pesquisa();

    @Override
    public int addBicycles(String s) {
        ArrayList<String[]> strings = (ArrayList<String[]>) FileUtils.read(s, ";");
        return veiculoController.carregarBicicletas(strings);
    }

    @Override
    public int addEscooters(String s) {
        ArrayList<String[]> strings = (ArrayList<String[]>) FileUtils.read(s, ";");
        return veiculoController.carregarScooters(strings);
    }

    @Override
    public int addParks(String s) {
        List<String[]> dados = (ArrayList<String[]>) FileUtils.read(s, ";");
        return parqueController.addParques(dados);
    }

    @Override
    public int addPOIs(String s) {
        ArrayList<String[]> dados = (ArrayList<String[]>) FileUtils.read(s, ";");
        return poiController.carregarPOIs(dados);
    }

    @Override
    public int addUsers(String s) {
        ArrayList<String[]> strings = (ArrayList<String[]>) FileUtils.read(s, ";");
        return utilizadorController.addUsers(strings);
    }

    @Override
    public int addPaths(String s) {
        List<String[]> paths = FileUtils.read(s, ";");
        CaminhosController controller = new CaminhosController();
        return controller.adicionarCaminhos(paths);
    }

    @Override
    public int getNumberOfBicyclesAtPark(double v, double v1, String s) {
        List<String> bicicletas = new PesquisaController().bicicletasNumParque(v, v1);
        String header = "bicycle description;wheel size";
        FileUtils.write(bicicletas, s, header);
        return bicicletas.size();
    }

    /**
     * Distance is returns in meters, rounded to the unit e.g. (281,58 rounds to
     * 282);
     *
     * @param v Latitude in degrees.
     * @param v1 Longitude in degrees.
     * @param s Filename for output.
     */
    @Override
    public void getNearestParks(double v, double v1, String s) {
        List<String> parques = new PesquisaController().parquesMaisProximos(v, v1);
        String header = "latitude;longitude;distance in meters";
        FileUtils.write(parques, s, header);
    }

    @Override
    public int getNumberOfBicyclesAtPark(String string, String string1) {
        List<String> bicicletas = new PesquisaController().bicicletasNumParque(string);
        String header = "bicycle description;wheel size";
        FileUtils.write(bicicletas, string1, header);
        return bicicletas.size();
    }

    /**
     *
     * @param string Park to be removed from the system.
     * @return
     */
    @Override
    public int removePark(String string) {
        boolean numRemovido = parqueController.removeParque(string);
        return numRemovido ? 1 : 0;
    }

    @Override
    public void getNearestParks(double d, double d1, String string, int i) {
        List<String> parques = new PesquisaController().parquesMaisProximos(d, d1, i);
        String header = "latitude;longitude;distance in meters";
        FileUtils.write(parques, string, header);
    }

    @Override
    public int getFreeBicycleSlotsAtPark(String string, String string1) {
        String tipoVeiculo = veiculoController.getTipoVeiculoPorUsername(string1);
        if (tipoVeiculo != null && tipoVeiculo.equalsIgnoreCase("Bicicleta")) {
            return parqueController.verificaLugaresDisponiveisParque(string, string1);
        } else {
            return 0;
        }
    }

    @Override
    public int getFreeEscooterSlotsAtPark(String string, String string1) {
        String tipoVeiculo = veiculoController.getTipoVeiculoPorUsername(string1);
        if (tipoVeiculo != null && tipoVeiculo.equalsIgnoreCase("Scooter")) {
            return parqueController.verificaLugaresDisponiveisParque(string, string1);
        } else {
            return 0;
        }
    }

    @Override
    public int linearDistanceTo(double d, double d1, double d2, double d3) {
        return (int) Calculos.distEntreDoisLocais(d, d1, d2, d3);
    }

    @Override
    public int pathDistanceTo(double d, double d1, double d2, double d3) {
        RotasController rotasController = new RotasController();
        List<Local> path = rotasController.rotaMaisCurta(d, d1, d2, d3);
        int dist = (int) rotasController.distanciaRota(path);
        return dist;
    }

    @Override
    public long unlockBicycle(String string, String string1) {
        return viagemController.unlockVeiculo(string, string1);
    }

    @Override
    public long unlockEscooter(String string, String string1) {
        return viagemController.unlockVeiculo(string, string1);
    }

    @Override
    public long lockBicycle(String string, double d, double d1, String string1) {
        return viagemController.lockVeiculo(string, d, d1, string1);
    }

    @Override
    public long lockBicycle(String string, String string1, String string2) {
        return viagemController.lockVeiculo(string, string1, string2);
    }

    @Override
    public long lockEscooter(String string, double d, double d1, String string1) {
        return viagemController.lockVeiculo(string, d, d1, string1);
    }

    @Override
    public long lockEscooter(String string, String string1, String string2) {
        return viagemController.lockVeiculo(string, string1, string2);
    }

    @Override
    public int suggestEscootersToGoFromOneParkToAnother(String string, String string1, double d, double d1, String string2) {
        List<Scooter> scSugeridas = viagemController.sugerirScooter(string, string1, d, d1);
        List<String> dados = new ArrayList<>();
        String header = "escooter description;type;actual battery capacity";
        if (scSugeridas == null) {
            FileUtils.write(dados, string2, header);
            return 0;
        }
        for (Scooter sc : scSugeridas) {
            Integer cargaAtual = sc.getCargaAtual();
            String texto = sc.getDescricao() + ";" + sc.getTipoScooter() + ";" + String.valueOf(cargaAtual);
            dados.add(texto);
        }
        FileUtils.write(dados, string2, header);
        return scSugeridas.size();
    }

    @Override
    public long mostEnergyEfficientRouteBetweenTwoParks(String originParkIdentification,
            String destinationParkIdentification,
            String typeOfVehicle,
            String vehicleSpecs,
            String username,
            String outputFileName) {
        RotasController rotasController = new RotasController();
        LinkedList<Local> rota = rotasController.rotaMaisEficiente(originParkIdentification,
                destinationParkIdentification,
                typeOfVehicle,
                vehicleSpecs,
                username);
        List<LinkedList<Local>> rotas = new ArrayList<>();
        rotas.add(rota);
        String content = rotasController.rotaToFileString(rotas);
        String header = "";
        FileUtils.write(content, outputFileName, header);

        return (rotas.isEmpty()) ? 0 : (long) rotasController.energiaRota(rotas.get(0));
    }

    @Override
    public int getNumberOfEscootersAtPark(double d, double d1, String string) {
        List<String> scooters = new PesquisaController().scootersNumParque(d, d1);
        String header = "escooter description;type;actual battery capacity";
        FileUtils.write(scooters, string, header);
        return scooters.size();
    }

    @Override
    public int getNumberOfEScootersAtPark(String string, String string1) {
        List<String> bicicletas = new PesquisaController().scootersNumParque(string);
        String header = "bicycle description;wheel size";
        FileUtils.write(bicicletas, string1, header);
        return bicicletas.size();
    }

    @Override
    public int getFreeSlotsAtParkForMyLoanedVehicle(String string, String string1) {
        return parqueController.verificaLugaresDisponiveisParque(string1, string);
    }

    @Override
    public long unlockAnyEscooterAtPark(String string, String string1, String string2) {
        List<Scooter> scooters = new PesquisaController().scootersParqueOrdenadosPorMaiorCarga(string);
        String header = "escooter description;type;actual battery capacity";
        String content = "";
        long resultado = 0;

        if (scooters.iterator().hasNext()) {
            Scooter s = scooters.iterator().next();
            resultado = new ViagemController().unlockVeiculo(s.getDescricao(), string, string1);
            Integer cargaAtual = s.getCargaAtual();
            content = s.getDescricao() + ";" + s.getTipoScooter() + ";" + String.valueOf(cargaAtual);
            FileUtils.write(content, string2, header);
        }
        FileUtils.write(content, string2, header);
        return resultado;
    }

    @Override
    public long unlockAnyEscooterAtParkForDestination(String string, String string1, double d, double d1, String string2) {
        List<Scooter> scSugeridas = viagemController.sugerirScooter(string, string1, d, d1);

        String header = "escooter description;type;actual battery capacity";
        String content = "";
        long resultado = 0;
        if (scSugeridas != null) {
            Scooter s = scSugeridas.get(0);
            resultado = new ViagemController().unlockVeiculo(s.getDescricao(), string, string1);
            Integer cargaAtual = s.getCargaAtual();
            content = s.getDescricao() + ";" + s.getTipoScooter() + ";" + String.valueOf(cargaAtual);
        }
        FileUtils.write(content, string2, header);
        return resultado;
    }

    @Override
    public double getUserCurrentDebt(String string, String string1) {
        FaturaController controller = new FaturaController();
        List<String> detalhe = new ArrayList<>();
        double debitoAtual = controller.getDebitoAtual(string, detalhe);
        String header = "vehicle description;vehicle unlock time;vehicle lock time;origin park latitude;origin park longitude;destination park latitude;destination park longitude;total time spent in seconds;charged value";
        FileUtils.write(detalhe, string1, header);
        return debitoAtual;
    }

    @Override
    public double getUserCurrentPoints(String string, String string1) {
        FaturaController controller = new FaturaController();
        List<String> detalhe = new ArrayList<>();
        double pontos = controller.getUserCurrentPoints(string, detalhe);
        String header = "vehicle description;vehicle unlock time;vehicle lock time;origin park latitude;origin park longitude;origin park elevation;destination park latitude;destination park longitude;destination park elevation;elevation difference;points";
        FileUtils.write(detalhe, string1, header);
        return pontos;
    }

    @Override
    public double calculateElectricalEnergyToTravelFromOneLocationToAnother(double d, double d1, double d2, double d3, String string) {
        Utilizador user = pesquisaController.getUtilizadorByUsername(string);
        Local localA = pesquisaController.getLocalComCoordenadas(d, d1);
        Local localB = pesquisaController.getLocalComCoordenadas(d2, d3);

        if (localA == null || localB == null) {
            return 0;
        }
        
        RotasController rotasController = new RotasController();
        LinkedList<Local> rota = rotasController.rotaMaisCurta(localA.getLatitude(), localA.getLongitude(),
                localB.getLatitude(),
                localB.getLongitude());
        
        if(rota.size() == 0){
            return 0;
        }
        
        Double energiaNecessaria = rotasController.energiaRota(rota);
        return (double) Math.round(energiaNecessaria * 100d) / 100d;
    }

    @Override
    public long forHowLongAVehicleIsUnlocked(String string) {
        Viagem v = p.getViagemVeiculo(string);
        if (v == null) {
            return 0;
        }
        Date dataDesbloqueio = v.getDataHoraInicio();
        Date dataAtual = new Date(System.currentTimeMillis());

        return Calculos.diferencaTempoSegundos(dataDesbloqueio, dataAtual);
    }

    @Override
    public long shortestRouteBetweenTwoParksForGivenPOIs(String originParkIdentification,
            String destinationParkIdentification,
            String inputPOIs,
            String outputFileName) {
        RotasController rotasController = new RotasController();
        List<String[]> sPois = FileUtils.read(inputPOIs, ";");
        List<LinkedList<Local>> rotas
                = rotasController.rotasMaisCurtasComPOIS(originParkIdentification,
                        destinationParkIdentification,
                        sPois);

        String content = rotasController.rotaToFileString(rotas);
        String header = "";
        FileUtils.write(content, outputFileName, header);

        return (rotas.isEmpty()) ? 0 : rotasController.distanciaRota(rotas.get(0));
    }

    @Override
    public long shortestRouteBetweenTwoParksForGivenPOIs(double originLatitudeInDegrees,
            double originLongitudeInDegrees,
            double destinationLatitudeInDegrees,
            double destinationLongitudeInDegrees,
            String inputPOIs,
            String outputFileName) {
        RotasController rotasController = new RotasController();
        List<String[]> sPois = FileUtils.read(inputPOIs, ";");
        List<LinkedList<Local>> rotas
                = rotasController.rotasMaisCurtasComPOIS(originLatitudeInDegrees,
                        originLongitudeInDegrees,
                        destinationLatitudeInDegrees,
                        destinationLongitudeInDegrees,
                        sPois);
        String content = rotasController.rotaToFileString(rotas);
        String header = "";
        FileUtils.write(content, outputFileName, header);

        return (rotas.isEmpty()) ? 0 : rotasController.distanciaRota(rotas.get(0));
    }

    /**
     *
     * @param string Park Identification
     * @param string1 Path to file where vehicles information should be written,
     * according to file output/chargingReport.csv
     * @return The number of escooters charging at the moment that are not 100%
     * fully charged.
     */
    @Override
    public long getParkChargingReport(String string, String string1) {
        ArrayList<String> lista = veiculoController.estadoCarregamentoRelatorio(string);
        List<String> dados = new ArrayList<>();
        String header = "escooter description;type;actual battery capacity;time to finish charge in seconds";
        if (lista == null) {
            FileUtils.write(dados, string1, header);
            return 0;
        }

        for (String ls : lista) {
            dados.add(ls);
        }

        FileUtils.write(dados, string1, header);
        return lista.size();
    }

    @Override
    public int suggestRoutesBetweenTwoLocations(String originParkIdentification,
            String destinationParkIdentification,
            String typeOfVehicle,
            String vehicleSpecs,
            String username,
            int maxNumberOfSuggestions,
            boolean ascendingOrder,
            String sortingCriteria,
            String inputPOIs,
            String outputFileName) {
        RotasController rotasController = new RotasController();
        List<String[]> sPois = FileUtils.read(inputPOIs, ";");
        List<LinkedList<Local>> rotas
                = rotasController.routesBetweenTwoLocations(originParkIdentification,
                        destinationParkIdentification,
                        typeOfVehicle,
                        vehicleSpecs,
                        username,
                        maxNumberOfSuggestions,
                        ascendingOrder,
                        sortingCriteria,
                        sPois,
                        outputFileName);
        String content = rotasController.rotaToFileString(rotas);
        String header = "";
        FileUtils.write(content, outputFileName, header);
        return rotas.size();
    }

    @Override
    public double getInvoiceForMonth(int i, String string, String string1) {
        FaturaController controller = new FaturaController();
        Fatura f = new Pesquisa().getFatura(string, i);
        //se fatura ainda não existe
        if (f == null) {
            //Emite fatura?
            if (controller.emiteFatura(string, i)) {
                f = new Pesquisa().getFatura(string, i);
                //tenta descontar o maior numero de pontos.
                f.usaPontos(f.getPontosAtuais());
            }
        }

        List<String> header = new ArrayList<>();
        List<String> detalhe = new ArrayList<>();
        double total = controller.getFatura(string, i, header, detalhe);

        String header2 = "";
        for (String h : header) {
            header2 += h + "\n";
        }
        detalhe.add(0, "vehicle description;vehicle unlock time;vehicle lock time;origin park latitude;origin park longitude;destination park latitude;destination park longitude;total time spent in seconds;charged value");
        FileUtils.write(detalhe, string1, header2);

        return total;
    }

    @Override
    public int registerUser(String string, String string1, String string2, String string3, int i, int i1, BigDecimal bd, String string4) {
        return new UtilizadorController().registerUser(string, string1, string2, string3, i, i1, bd, string4);
    }

    @Override
    public long shortestRouteBetweenTwoParks(double d, double d1, double d2, double d3, int i, String string) {
        RotasController rotasController = new RotasController();
        LinkedList<Local> rota = rotasController.rotaMaisCurta(d,
                d1,
                d2,
                d3);
        List<LinkedList<Local>> rotas = new ArrayList<>();
        rotas.add(rota);
        String content = rotasController.rotaToFileString(rotas);
        String header = "";
        FileUtils.write(content, string, header);

        return (rotas.isEmpty()) ? 0 : rotasController.distanciaRota(rotas.get(0));
    }

    @Override
    public long shortestRouteBetweenTwoParks(String string, String string1, int i, String string2) {
        RotasController rotasController = new RotasController();
        LinkedList<Local> rota
                = rotasController.rotaMaisCurta(string, string1);
        List<LinkedList<Local>> rotas = new ArrayList<>();
        rotas.add(rota);
        String content = rotasController.rotaToFileString(rotas);
        String header = "";
        FileUtils.write(content, string2, header);

        return (rotas.isEmpty()) ? 0 : rotasController.distanciaRota(rotas.get(0));
    }
}
