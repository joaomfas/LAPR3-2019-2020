package lapr.project.assessment;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import lapr.project.controller.*;
import lapr.project.model.*;

public class FacadeTests {

    FacadeCopy f;

    String fPois = "src/main/resources/FicheirosTeste/input/pois.csv";
    String fPoisR = "src/main/resources/FicheirosTeste/input/poisR.csv";
    String fInputPois = "src/main/resources/FicheirosTeste/input/inputPois.csv";
    String fParks = "src/main/resources/FicheirosTeste/input/parks.csv";
    String fParksR = "src/main/resources/FicheirosTeste/input/parksR.csv";
    String fBikes = "src/main/resources/FicheirosTeste/input/bicycles.csv";
    String fBikesR = "src/main/resources/FicheirosTeste/input/bicyclesR.csv";
    String fScooters = "src/main/resources/FicheirosTeste/input/scooters.csv";
    String fScootersR = "src/main/resources/FicheirosTeste/input/scootersR.csv";
    String fUsers = "src/main/resources/FicheirosTeste/input/users.csv";
    String fUsersR = "src/main/resources/FicheirosTeste/input/usersR.csv";
    String fPaths = "src/main/resources/FicheirosTeste/input/paths.csv";
    String fPathsR = "src/main/resources/FicheirosTeste/input/pathsR.csv";

    String oPaths = "src/main/resources/FicheirosTeste/output/paths.csv";
    String oNumBikes1 = "src/main/resources/FicheirosTeste/output/numBikes1.csv";
    String oNumBikes2 = "src/main/resources/FicheirosTeste/output/numBikes2.csv";
    String oNumScooters1 = "src/main/resources/FicheirosTeste/output/numScooters1.csv";
    String oNumScooters2 = "src/main/resources/FicheirosTeste/output/numScooters2.csv";
    String oNearParks1 = "src/main/resources/FicheirosTeste/output/nearParks1.csv";
    String oNearParks2 = "src/main/resources/FicheirosTeste/output/nearParks2.csv";
    String oNearParks3 = "src/main/resources/FicheirosTeste/output/nearParks3.csv";
    String oChargingReport = "src/main/resources/FicheirosTeste/output/chargingReport.csv";
    String oChargingReport1 = "src/main/resources/FicheirosTeste/output/chargingReport1.csv";
    String oChargingReport2 = "src/main/resources/FicheirosTeste/output/chargingReport2.csv";
    String oChargingReport3 = "src/main/resources/FicheirosTeste/output/chargingReport3.csv";
    String oUserDebt1 = "src/main/resources/FicheirosTeste/output/userDebt1.csv";
    String oUserDebt2 = "src/main/resources/FicheirosTeste/output/userDebt2.csv";
    String oUserPoints = "src/main/resources/FicheirosTeste/output/userPoints.csv";
    String oEffRouteParks = "src/main/resources/FicheirosTeste/output/effRouteParks.csv";
    String oShortRouteParks1 = "src/main/resources/FicheirosTeste/output/shortRouteParks1.csv";
    String oShortRouteParks2 = "src/main/resources/FicheirosTeste/output/shortRouteParks2.csv";
    String oShortRouteParksPOIS1 = "src/main/resources/FicheirosTeste/output/shortRouteParksPOIS1.csv";
    String oShortRouteParksPOIS2 = "src/main/resources/FicheirosTeste/output/shortRouteParksPOIS2.csv";
    String oSuggestScooter = "src/main/resources/FicheirosTeste/output/suggestScooter.csv";
    String oRouteBetweenLocs1 = "src/main/resources/FicheirosTeste/output/routeBetweenLocs1.csv";
    String oRouteBetweenLocs2 = "src/main/resources/FicheirosTeste/output/routeBetweenLocs2.csv";
    String oRouteBetweenLocs3 = "src/main/resources/FicheirosTeste/output/routeBetweenLocs3.csv";
    String oUnlockAnyScooter = "src/main/resources/FicheirosTeste/output/unlockAnyScooter.csv";
    String oUnlockAnyScooterForDest = "src/main/resources/FicheirosTeste/output/unlockAnyScooterForDest.csv";
    String oInvoice1 = "src/main/resources/FicheirosTeste/output/invoice1.csv";
    String oInvoice2 = "src/main/resources/FicheirosTeste/output/invoice2.csv";
    String oInvoice3 = "src/main/resources/FicheirosTeste/output/invoice3.csv";

    // breno
    int intResult;
    double doubleResult;
    long longResult;
    String stringResult;

    // joao
    Date date = null;
    SimpleDateFormat simpleDateFormat = null;
    String dateS;
    // josue
    // thais
    String descricaoVeiculo = "scooterE";
    String usernamePoints = "thaisf";
    ViagemController vc = new ViagemController();
    PesquisaController pc = new PesquisaController();

    // maria
    public FacadeTests() {
        f = new FacadeCopy();
        inserts();
        f.bdutil.runStript("/src/main/resources/BaseDeDados/viagensTestes.sql");
        unlocks();
        locks();
        numberVehicles();
        freeVehicles();
        distances();
        paths();
        reports();
    }

    public void inserts() {
        // -> addParks(String inputFile)
        intResult = f.addParks(fParks);
        System.out.println("addParks(fParks): " + intResult);
        //f.addParks(fParksR);
        // -> removePark(String parkIdentification)
        //intResult = f.removePark("Dummy");
        //System.out.println("removePark(\"Dummy\"): " + intResult);
        // -> addBicycles(String inputFile)
        intResult = f.addBicycles(fBikes);
        System.out.println("addBicycles(fBikes): " + intResult);
        //f.addBicycles(fBikesR);
        // -> addEscooters(String inputFile)
        intResult = f.addEscooters(fScooters);
        System.out.println("addEscooters(fScooters): " + intResult);
        //f.addEscooters(fScootersR);
        // -> addPOIs(String inputFile)
        intResult = f.addPOIs(fPois);
        System.out.println("addPOIs(fPois): " + intResult);
        //f.addPOIs(fPoisR);
        // -> addPaths(String inputFile)
        intResult = f.addPaths(fPaths);
        System.out.println("addPaths(fPaths): " + intResult);
        //f.addPaths(fPathsR);
        // -> addUsers(String inputFile)
        intResult = f.addUsers(fUsers);
        System.out.println("addUsers(fUsers): " + intResult);
        //f.addUsers(fUsersR);
        int numReg = f.registerUser("username", "email@email.com", "123abc", "2746274627382182", 160, 60, new BigDecimal(5.55), "m");
        System.out.println("Sucesso do registo de um user: " + numReg);
        // josué
    }

    // john
    public void locks() {
        // -> lockBicycle(String bicycleDescription, double parkLatitudeInDegrees, double parkLongitudeInDegrees, String username)
        Long dataLong = f.lockBicycle("bikeA", 41.15227, -8.60929, "josuemotta");
        date = new Date(dataLong);
        simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
        dateS = simpleDateFormat.format(date);
        System.out.println("lockBicycle(\"bikeA\", 41.15227, -8.60929, \"josuemotta\")" + dateS);
        dataLong = f.unlockBicycle("bikeA", "josuemotta");
        date = new Date(dataLong);
        simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
        dateS = simpleDateFormat.format(date);
        System.out.println("unlockBicycle(\"bikeA\", \"josuemotta\"):" + dateS);
        // -> lockBicycle(String bicycleDescription, String parkIdentification, String username)
        dataLong = f.lockBicycle("bikeA", "Trindade", "josuemotta");
        date = new Date(dataLong);
        simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
        dateS = simpleDateFormat.format(date);
        System.out.println("lockBicycle(\"bikeA\", \"Trindade\", \"josuemotta\"): " + dateS);
        // -> lockescooter(string escooterdescription, double parklatitudeindegrees, double parklongitudeindegrees, string username)
        dataLong = f.lockEscooter("scooterC", 41.14063, -8.61118, "joafmas");
        date = new Date(dataLong);
        simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
        dateS = simpleDateFormat.format(date);
        System.out.println("lockEscooter(\"scooterC\", 41.14063, -8.61118, \"joafmas\"): " + dateS);
        // -> lockEscooter(String escooterDescription, String parkIdentification, String username)
        dataLong = f.lockEscooter("scooterB", "Ribeira", "brenopacheco");
        date = new Date(dataLong);
        simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
        dateS = simpleDateFormat.format(date);
        System.out.println("lockEscooter(\"scooterB\", \"Ribeira\", \"brenopacheco\"): " + dateS);
    }

    //john 
    public void unlocks() {
        // -> unlockAnyEscooterAtPark(String parkIdentification, String username, String outputFileName)
        longResult = f.unlockAnyEscooterAtPark("Ribeira", "joafmas", oUnlockAnyScooter);
        date = new Date(longResult);
        simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
        dateS = simpleDateFormat.format(date);
        System.out.println("unlockAnyEscooterAtPark(\"Ribeira\", \"joafmas\", oUnlockAnyScooter): " + dateS);
        // -> unlockAnyEscooterAtParkForDestination(String parkIdentification, String username, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees, String outputFileName)
        longResult = f.unlockAnyEscooterAtParkForDestination("Trindade", "brenopacheco", 41.14063, -8.61118, oUnlockAnyScooterForDest);
        date = new Date(longResult);
        simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
        dateS = simpleDateFormat.format(date);
        System.out.println("unlockAnyEscooterAtParkForDestination(\"Trindade\", \"brenopacheco\", 41.14063, -8.61118, oUnlockAnyScooterForDest): " + dateS);
        // -> unlockBicycle(String bicycleDescription, String username)
        longResult = f.unlockBicycle("bikeA", "josuemotta");
        date = new Date(longResult);
        simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
        dateS = simpleDateFormat.format(date);
        System.out.println("unlockBicycle(\"bikeA\", \"josuemotta\"): " + dateS);
        // -> unlockEscooter(String escooterDescription, String username)
        longResult = f.unlockEscooter("scooterE", "mariadias");
        date = new Date(longResult);
        simpleDateFormat = new SimpleDateFormat("YYYY.MM.DD HH:mm:ss");
        dateS = simpleDateFormat.format(date);
        System.out.println("unlockEscooter(\"scooterE\", \"mariadias\"): " + dateS);
    }

    public void numberVehicles() {
        // -> getNumberOfBicyclesAtPark(double parkLatitudeInDegrees, double parkLongitudeInDegrees, String outputFileName)
        intResult = f.getNumberOfBicyclesAtPark(41.14063, -8.61118, oNumBikes1);
        System.out.println("getNumberOfBicyclesAtPark(41.14063, -8.61118, oNumBikes1): " + intResult);
        // -> getNumberOfBicyclesAtPark(String parkIdentification, String outputFileName)
        f.getNumberOfBicyclesAtPark("Ribeira", oNumBikes2);
        System.out.println("getNumberOfBicyclesAtPark(\"Ribeira\", oNumBikes2): " + intResult);
        // -> getNumberOfEscootersAtPark(double parkLatitudeInDegrees, double parkLongitudeInDegrees, String outputFileName)
        System.out.println("Num of scooters at 41.14063, -8.61118: " + f.getNumberOfEscootersAtPark(41.14063, -8.61118, oNumScooters1));
        // -> getNumberOfEScootersAtPark(String parkIdentification, String outputFileName)
        System.out.println("Num of scooters at Ribeira: " + f.getNumberOfEScootersAtPark("Ribeira", oNumScooters2));
    }

    // thais
    public void freeVehicles() {
        // -> getFreeBicycleSlotsAtPark(String parkIdentification, String username)
        f.unlockBicycle("bikeA", "josuemotta");
        f.unlockEscooter("scooterA", "thaisf");
        intResult = f.getFreeBicycleSlotsAtPark("Trindade", "josuemotta");
        System.out.println("getFreeBicycleSlotsAtPark(\"Trindade\", \"josuemotta\"): " + intResult);
        // -> getFreeEscooterSlotsAtPark(String parkIdentification, String username)
        intResult = f.getFreeEscooterSlotsAtPark("Trindade", "thaisf");
        System.out.println("getFreeEscooterSlotsAtPark(\"Trindade\", \"thaisf\"): " + intResult);
        // -> getFreeSlotsAtParkForMyLoanedVehicle(String username, String parkIdentification)
        intResult = f.getFreeSlotsAtParkForMyLoanedVehicle("thaisf", "Trindade");
        System.out.println("getFreeSlotsAtParkForMyLoanedVehicle(\"thaisf\", \"Trindade\"): " + intResult);
        f.lockBicycle("bikeA", "Ribeira", "josuemotta");
    }

    // breno e john
    public void distances() {
        // -> linearDistanceTo(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees)
        intResult = f.linearDistanceTo(41.15227, -8.60929, 41.14063, -8.61118); // trindade -> ribeira
        System.out.println("linearDistanceTo(41.15227, -8.60929, 41.14063, -8.61118): " + intResult);
        // -> getNearestParks(double userLatitudeInDegrees, double userLongitudeInDegrees, String outputFileName)
        f.getNearestParks(41.1498781, -8.6142323, oNearParks1); // 1km default
        // -> getNearestParks(double userLatitudeInDegrees, double userLongitudeInDegrees, String outputFileName, int radius)
        f.getNearestParks(41.1498781, -8.6142323, oNearParks2, 5); // 5km
        f.getNearestParks(41.1498781, -8.6142323, oNearParks3, 10); // 10km
        // -> calculateElectricalEnergyToTravelFromOneLocationToAnother(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinationLatitudeInDegrees, double destinationLongitudeInDegrees, String username)
        doubleResult = f.calculateElectricalEnergyToTravelFromOneLocationToAnother(41.15227, -8.60929, 41.16875, -8.68995, "joafmas");
        Local localA = pc.getLocalComCoordenadas(41.15227, -8.60929);
        Local localB = pc.getLocalComCoordenadas(41.16875, -8.68995);
        Utilizador user = pc.getUtilizadorByUsername("joafmas");
        System.out.println("Energia para ir da Ribeira ao Castelo do Queijo: " + doubleResult + " kWh || " + Math.round(vc.caloriasEntreDoisPontos(doubleResult) * 100d) / 100d + " cal");
    }

    // breno
    public void paths() {
        // -> pathDistanceTo(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees)
        intResult = f.pathDistanceTo(41.15227, -8.60929, 41.14063, -8.61118);
        System.out.println("pathDistanceTo(41.15227, -8.60929, 41.14063, -8.61118): " + intResult);
        // -> mostEnergyEfficientRouteBetweenTwoParks(String originParkIdentification, String destinationParkIdentification, String typeOfVehicle, String vehicleSpecs, String username, String outputFileName)
        longResult = f.mostEnergyEfficientRouteBetweenTwoParks("CasteloQueijo", "Trindade", "bicycle", "16", "josuemotta", oEffRouteParks);
        System.out.println("mostEnergyEfficientRouteBetweenTwoParks(\"CasteloQueijo\", \"Trindade\", \"bicycle\", \"16\", \"josuemotta\", oEffRouteParks):" + longResult);
        // -> shortestRouteBetweenTwoParks(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinationLatitudeInDegrees, double destinationLongitudeInDegrees, String outputFileName)
        longResult = f.shortestRouteBetweenTwoParks(41.14063, -8.61118, 41.16875, -8.68995, 1, oShortRouteParks1);
        System.out.println("shortestRouteBetweenTwoParks(41.14063, -8.61118, 41.16875, -8.68995, oShortRouteParks1):" + longResult); // ribeira castelo
        // -> shortestRouteBetweenTwoParks(String originParkIdentification, String destinationParkIdentification, String outputFileName)
        longResult = f.shortestRouteBetweenTwoParks("CasteloQueijo", "Trindade", 1, oShortRouteParks2);
        System.out.println("shortestRouteBetweenTwoParks(\"CasteloQueijo\", \"Trindade\", oShortRouteParks1):" + longResult);
        // -> shortestRouteBetweenTwoParksForGivenPOIs(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinationLatitudeInDegrees, double destinationLongitudeInDegrees, String inputPOIs, String outputFileName)
        longResult = f.shortestRouteBetweenTwoParksForGivenPOIs(41.14063, -8.61118, 41.16875, -8.68995, fInputPois, oShortRouteParksPOIS1);
        System.out.println("shortestRouteBetweenTwoParksForGivenPOIs(41.14063, -8.61118, 41.16875, -8.68995, fInputPois, oShortRouteParksPOIS1): " + longResult);
        // -> shortestRouteBetweenTwoParksForGivenPOIs(String originParkIdentification, String destinationParkIdentification, String inputPOIs, String outputFileName)
        longResult = f.shortestRouteBetweenTwoParksForGivenPOIs("Ribeira", "CasteloQueijo", fInputPois, oShortRouteParksPOIS2);
        System.out.println("shortestRouteBetweenTwoParksForGivenPOIs(\"Ribeira\", \"CasteloQueijo\", fInputPois, oShortRouteParksPOIS2): " + longResult);
        // -> suggestRoutesBetweenTwoLocations(String originParkIdentification, String destinationParkIdentification, String typeOfVehicle, String vehicleSpecs, String username, int maxNumberOfSuggestions, boolean ascendingOrder, String sortingCriteria, String inputPOIs, String outputFileName)
        intResult = f.suggestRoutesBetweenTwoLocations("Ribeira", "CasteloQueijo", "escooter", "off-road", "joafmas", 3, true, "energy", fInputPois, oRouteBetweenLocs1);
        System.out.println("suggestRoutesBetweenTwoLocations(\"Ribeira\", \"CasteloQueijo\", \"escooter\", \"off-road\", \"joafmas\", 3, true, \"energy\", fInputPois, oRouteBetweenLocs1): " + intResult);
        intResult = f.suggestRoutesBetweenTwoLocations("Ribeira", "CasteloQueijo", "escooter", "off-road", "joafmas", 3, true, "shortest_distance", fInputPois, oRouteBetweenLocs2);
        System.out.println("suggestRoutesBetweenTwoLocations(\"Ribeira\", \"CasteloQueijo\", \"escooter\", \"off-road\", \"joafmas\", 3, true, \"shortest_distance\", fInputPois, oRouteBetweenLocs2): " + intResult);
        intResult = f.suggestRoutesBetweenTwoLocations("Ribeira", "CasteloQueijo", "escooter", "off-road", "joafmas", 3, true, "number_of_points", fInputPois, oRouteBetweenLocs3);
        System.out.println("suggestRoutesBetweenTwoLocations(\"Ribeira\", \"CasteloQueijo\", \"escooter\", \"off-road\", \"joafmas\", 3, true, \"number_of_points\", fInputPois, oRouteBetweenLocs3): " + intResult);

        // -> suggestEscootersToGoFromOneParkToAnother(String parkIdentification, String username, double destinationParkLatitudeInDegrees, double destinationParkLongitudeInDegrees, String outputFileName)
        System.out.println("Existem " + f.suggestEscootersToGoFromOneParkToAnother("Ribeira", "joafmas", 41.16875, -8.68995, oSuggestScooter) + " scooters capazes de fazer o percurso Ribeira - Castelo do Queijo");
    }

    // maria, josue, thais
    public void reports() {

        // -> forHowLongAVehicleIsUnlocked(String vehicleDescription)
        // retorna um valor válido
        double tempoUnlock = f.unlockEscooter(descricaoVeiculo, "thaisf");
        //System.out.println("tempoUnlock : " + tempoUnlock);
        long x = f.forHowLongAVehicleIsUnlocked(descricaoVeiculo);
        System.out.println("forHowLongAVehicleIsUnlocked teste válido : " + x);
        // não retorna valor - Scooter bloqueada
        descricaoVeiculo = "scooterF";
        x = f.forHowLongAVehicleIsUnlocked(descricaoVeiculo);
        System.out.println("forHowLongAVehicleIsUnlocked retorna 0  (Scooter bloqueada) : " + x);

        // -> getInvoiceForMonth(int month, String username, String outputPath)
        double valorTotalFatura = f.getInvoiceForMonth(11, "josuemotta", oInvoice1);
        System.out.println("getInvoiceForMonth(11,josuemotta, oInvoice) = " + valorTotalFatura);
        valorTotalFatura = f.getInvoiceForMonth(12,"josuemotta", oInvoice2);
        System.out.println("getInvoiceForMonth(12, josuemotta, oInvoice2) = " + valorTotalFatura);
        valorTotalFatura = f.getInvoiceForMonth(1, "josuemotta", oInvoice3);
        System.out.println("getInvoiceForMonth(1, josuemotta, oInvoice3) = " + valorTotalFatura);
        // -> getParkChargingReport(String parkIdentification, String outputFileName)
        long nrOfScootersAtTrindade = f.getParkChargingReport("Trindade", oChargingReport1);
        System.out.println("Número de scooters em carga no parque Trindade: " + nrOfScootersAtTrindade); //esperado: 2
        long nrOfScootersAtRibeira = f.getParkChargingReport("Ribeira", oChargingReport2);
        System.out.println("Número de scooters em carga no parque Ribeira: " + nrOfScootersAtRibeira); // esperado 2
        long nrOfScootersAtCasteloQueijo = f.getParkChargingReport("CasteloQueijo", oChargingReport3);
        System.out.println("Número de scooters em carga no parque CasteloQueijo: " + nrOfScootersAtCasteloQueijo); // esperado: 3
        // -> getUserCurrentDebt(String username, String outputFileName)
        double valorAtual = f.getUserCurrentDebt("josuemotta", oUserDebt1);
        System.out.println("getUserCurrentDebt(josuemotta, oUserDebt1) = " + valorAtual);
        valorAtual = f.getUserCurrentDebt("josuemotta", oUserDebt2);
        System.out.println("getUserCurrentDebt(josuemotta, oUserDebt2) = " + valorAtual);
        // -> getUserCurrentPoints(String username, String outputFileName)
        // retorna um valor de pontos
        double tempoLock = f.lockEscooter("scooterE", "Ribeira", usernamePoints);
        //System.out.println("lock : " + tempoLock);
        Double pontos = f.getUserCurrentPoints("josuemotta", oUserPoints);
        System.out.println("getUserCurrentPoints(josuemotta, oUserPoints) = " + pontos);
    }
}
