package lapr.project.assessment.scenario003;

import lapr.project.assessment.ProjectUtils;
import lapr.project.assessment.Serviceable;
import lapr.project.assessment.TargetProject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;


public class Scenario003Test {
    private String getSeparator() {
        return File.separator;
    }

    private String getScenarioID() {
        return this.getClass().getSimpleName().substring(8, 11);
    }

    /**
     * Assessment Scenario.
     */
    @Test
    @DisplayName("Assessment Scenario")
    public void assessmentScenario() {
        System.out.println(getScenarioID());

        int expectedOutput = 1;
        int output = -1;

        Serviceable facade = TargetProject.getServiceableObject();

        System.out.println("Adicionar Utilizadores: users001.csv");


        String usersFileName =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "users001.csv";
        System.out.println(usersFileName);
        output = facade.addUsers(usersFileName);
        System.out.println("Número de utilizadores adicionados:" + output);

        System.out.println("Adicionar parques: parks001.csv");
        String parksFileName =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "parks001.csv";
        output = facade.addParks(parksFileName);
        System.out.println("Número de parques adicionados:" + output);

        System.out.println("Obter nearest parks");
        String resultFileName =
                ProjectUtils.getApplicationFolder() + getSeparator()
                        + "target" + getSeparator() + "test-classes"
                        + getSeparator() + "scenario" + getScenarioID()
                        + getSeparator() + "output-getNearestParks-001.csv";
        System.out.println(resultFileName);

        facade.getNearestParks(41.148302, -8.611029,
                resultFileName);

        System.out.println("Adicionar trajetos: paths001.csv");
        String pathsFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "paths001.csv";
        output = facade.addPaths(pathsFilename);
        System.out.println("Número de paths adicionados:" + output);

        System.out.println("Adicionar bicicletas: bicycles001.csv");
        String bicyclesFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "bicycles001.csv";
        output = facade.addBicycles(bicyclesFilename);
        System.out.println("Número de bicicletas adicionadas:" + output);

        System.out.println("Adicionar escooters: escooters001.csv");
        String escootersFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "escooters001.csv";
        output = facade.addEscooters(escootersFilename);
        System.out.println("Número de escooters adicionadas:" + output);

        System.out.println(
                "Obter charging report Trindade: output-chargingReport-001.csv");
        String chargingFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-chargingReport-001.csv";
        long outputLong = facade.getParkChargingReport("Trindade",
                chargingFilename);
        System.out.println("Número de escooters a carregar:" + outputLong);

        System.out.println(
                "Obter User Current Debt: output-johnCurrentDebt-001.csv");
        String currentDebtFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-johnCurrentDebt-001.csv";
        double outputDouble = facade.getUserCurrentDebt("john",
                currentDebtFilename);
        System.out.println("Current debt do john:" + outputDouble);

        System.out.println(
                "Obter User Current Points: output-johnCurrentPoints-001.csv");
        String currentPointsFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-johnCurrentPoints-001.csv";
        outputDouble = facade.getUserCurrentPoints("john",
                currentPointsFilename);
        System.out.println("Número de pontos do John:" + outputDouble);

        System.out.println(
                "John unlocks bicyle10 at Ribeira");
        outputLong = facade.unlockBicycle("bicycle10", "john");
        System.out.println("unlock time in mili:" + outputLong);

        System.out.println(
                "John locks bicyle10 at Trindade");
        outputLong = facade.lockBicycle("bicycle10", "Trindade", "john");
        System.out.println("lock time in mili:" + outputLong);

        System.out.println(
                "Obter User Current Debt: output-johnCurrentDebt-002.csv");
        currentDebtFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-johnCurrentDebt-002.csv";
        outputDouble = facade.getUserCurrentDebt("john",
                currentDebtFilename);
        System.out.println("Current debt do john:" + outputDouble);

        System.out.println(
                "Obter User Current Points: output-johnCurrentPoints-002.csv");
        currentPointsFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-johnCurrentPoints-002.csv";
        outputDouble = facade.getUserCurrentPoints("john",
                currentPointsFilename);
        System.out.println("Número de pontos do John:" + outputDouble);

        System.out.println(
                "Obter John Invoice for month: output-johnInvoice-001.csv");
        String invoiceFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-johnInvoice-001.csv";
        outputDouble = facade.getInvoiceForMonth(1, "john",
                invoiceFilename);
        System.out.println("John's user debt:" + outputDouble);

        System.out.println(
                "Obter Caminho mais curto entre Trindade e Castelo do Queijo, "
                        + "sem "
                        + "pontos de interesse: "
                        + "output-shortestPath-001.csv");
        String pathFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-shortestPath-001.csv";
        outputDouble = facade.shortestRouteBetweenTwoParks(
                "Trindade", "CasteloQueijo", 0, pathFilename);
        System.out.println(
                "Distância em metros da Trindade a Castelo do Queijo:"
                        + outputDouble);

        System.out.println(
                "Obter energia para caminho Trindade e "
                        + "Castelo do queijo, "
                        + "sem "
                        + "pontos de interesse: "
                        + "output-energy-001.csv");
        pathFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-energy-001.csv";
        outputDouble =
                facade.calculateElectricalEnergyToTravelFromOneLocationToAnother(
                        41.15227, -8.60929, 41.16875, -8.68995, "john");
        System.out.println(
                "Distância em metros da Trindade a Castelo do Queijo:"
                        + outputDouble);


        System.out.println(
                "Obter energia para caminho Castelo do Queijo e "
                        + "Trindade, "
                        + "sem "
                        + "pontos de interesse: "
                        + "output-energy-002.csv");
        pathFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-energy-002.csv";
        outputDouble =
                facade.calculateElectricalEnergyToTravelFromOneLocationToAnother(
                        41.16875, -8.68995, 41.15227, -8.60929, "john");
        System.out.println(
                "Distância em metros da Castelo do Queijo à Trindade:"
                        + outputDouble);

        System.out.println("Adicionar Pontos de interesse: pois001.csv");
        pathsFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "pois001.csv";
        output = facade.addPOIs(pathsFilename);
        System.out.println("Número de POIs adicionados:" + output);

        System.out.println("Adicionar trajetos: paths002.csv");
        pathsFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "paths002.csv";
        output = facade.addPaths(pathsFilename);
        System.out.println("Número de paths adicionados:" + output);


        System.out.println(
                "Obter Caminho mais curto entre Trindade e Ribeira, "
                        + "com 2 "
                        + "pontos de interesse: "
                        + "output-shortestPath-002.csv");
        pathFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-shortestPath-002.csv";
        outputDouble = facade.shortestRouteBetweenTwoParks(
                "Trindade", "Ribeira", 2, pathFilename);
        System.out.println(
                "Distância em metros da Trindade à Ribeira:" + outputDouble);

        System.out.println(
                "Rota mais eficiente de bicicleta 26 entre Trindade e Ribeira"
                        + " , "
                        + "output-shortestPath-003.csv");
        pathFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-shortestPath-003.csv";
        outputDouble = facade.
                mostEnergyEfficientRouteBetweenTwoParks(
                        "Trindade",
                        "Ribeira",
                        "bicycle",
                        "26",
                        "john",
                        pathFilename);

        System.out.println(
                "Rota mais eficiente entre Trindade e Ribeira:" + outputDouble);

        System.out.println(
                "Rota mais eficiente de escooter cidade entre Trindade e "
                        + "Ribeira"
                        + " , "
                        + "output-shortestPath-004.csv");
        pathFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-shortestPath-004.csv";
        outputDouble = facade.
                mostEnergyEfficientRouteBetweenTwoParks(
                        "Trindade",
                        "Ribeira",
                        "escooter",
                        "city",
                        "john",
                        pathFilename);

        System.out.println(
                "Rota mais eficiente entre Trindade e Ribeira:" + outputDouble);

        System.out.println(
                "Rota mais eficiente de bicicleta entre Ribeira e Trindade, "
                        + "output-shortestPath-005.csv");
        pathFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-shortestPath-005.csv";
        outputDouble = facade.
                mostEnergyEfficientRouteBetweenTwoParks(
                        "Ribeira",
                        "Trindade",
                        "bicycle",
                        "26",
                        "john",
                        pathFilename);

        System.out.println(
                "Rota mais eficiente entre Ribeira e Trindade:" + outputDouble);

        System.out.println(
                "Sugerir scooter para ir da Ribeira à Trindade, "
                        + "output-escooter-001.csv");
        escootersFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-escooter-001.csv";
        outputDouble = facade.suggestEscootersToGoFromOneParkToAnother(
                "Ribeira",
                "john", 41.15227, -8.60929,
                escootersFilename);

        System.out.println(
                "Scooter para ir da Ribeira à Trindade:" + outputDouble);

        System.out.println(
                "Sugerir caminhos de Trindade à "
                        + "Ribeira: output-paths-001.csv");
        String poisFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "input-pois-001.csv";
        pathsFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-paths-001.csv";
        outputDouble = facade.suggestRoutesBetweenTwoLocations(
                "Trindade",
                "Ribeira",
                "bicycle",
                "29",
                "john",
                15,
                true,
                "energy",
                poisFilename,
                pathsFilename);

        System.out.println(
                "Número de sugestões para ir da da Trindade à Ribeira:"
                        + outputDouble);

        System.out.println(
                "Sugerir caminhos de Trindade à "
                        + "Ribeira: output-paths-002.csv");
        poisFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "input-pois-001.csv";
        pathsFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-paths-002.csv";
        outputDouble = facade.suggestRoutesBetweenTwoLocations(
                "Trindade",
                "Ribeira",
                "bicycle",
                "29",
                "mary",
                15,
                true,
                "energy",
                poisFilename,
                pathsFilename);

        System.out.println(
                "Número de sugestões para ir da da Trindade à Ribeira:"
                        + outputDouble);


        System.out.println(
                "Sugerir caminhos de Trindade à "
                        + "Ribeira: output-paths-003.csv");
        poisFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "input-pois-001.csv";
        pathsFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-paths-003.csv";
        outputDouble = facade.suggestRoutesBetweenTwoLocations(
                "Trindade",
                "Ribeira",
                "escooter",
                "city",
                "john",
                15,
                true,
                "energy",
                poisFilename,
                pathsFilename);

        System.out.println(
                "Número de sugestões para ir da da Trindade à Ribeira:"
                        + outputDouble);

        System.out.println(
                "Sugerir caminhos de Trindade à "
                        + "Ribeira: output-paths-004.csv");
        poisFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "input-pois-001.csv";
        pathsFilename =
                ProjectUtils.getApplicationFolder()
                        + getSeparator() + "target" + getSeparator() + "test"
                        + "-classes" + getSeparator() + "scenario"
                        + getScenarioID()
                        + getSeparator() + "output-paths-004.csv";
        outputDouble = facade.suggestRoutesBetweenTwoLocations(
                "Trindade",
                "Ribeira",
                "escooter",
                "city",
                "mary",
                15,
                true,
                "energy",
                poisFilename,
                pathsFilename);

        System.out.println(
                "Número de sugestões para ir da da Trindade à Ribeira:"
                        + outputDouble);
    }
}
