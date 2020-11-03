/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.assessment;

/**
 *
 * @author Thais Farias
 */
public class FacadeTestsScenario4 {
       FacadeCopy f;

    String fPois = "src/main/resources/FicheirosTesteScenarios/input/pois4.csv";
    String fParks = "src/main/resources/FicheirosTesteScenarios/input/parks4.csv";
    String fUsers = "src/main/resources/FicheirosTesteScenarios/input/users3.csv";
    String fPaths = "src/main/resources/FicheirosTesteScenarios/input/paths4.csv";

    String oEffRouteParks = "src/main/resources/FicheirosTesteScenarios/output/effRouteParks4.csv";
    String oEffRouteParks2 = "src/main/resources/FicheirosTesteScenarios/output/effRouteParks4_2.csv";
    String oShortRouteParks = "src/main/resources/FicheirosTesteScenarios/output/shortRouteParks4.csv";
    String oShortRouteParks2 = "src/main/resources/FicheirosTesteScenarios/output/shortRouteParks_4.csv";

    int intResult;
    double doubleResult;
    long longResult;
    String stringResult;

    public FacadeTestsScenario4() {
        f = new FacadeCopy();
        inserts();
        paths();
        
        
    }

    public void inserts() {
        // -> addParks(String inputFile)
        f.addParks(fParks);
        //f.addParks(fParksR);

        f.addPOIs(fPois);
        //f.addPOIs(fPoisR);
        
        f.addPaths(fPaths);
        // -> addPaths(String inputFile)
        
        f.addUsers(fUsers);
        // -> add Users
    }
 
    public void paths() {

        // in Between
      
        // -> shortestRouteBetweenTwoParks(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinationLatitudeInDegrees, double destinationLongitudeInDegrees, String outputFileName)
        longResult = f.shortestRouteBetweenTwoParks(41.15227,-8.60929, 41.14063,-8.61118, 1, oShortRouteParks); //Trindade -> Cais
        System.out.println("shortestRouteBetweenTwoParks(41.15227;-8.60929  - Trindade, 41.14063;-8.61118 - Cais Ribeira, oShortRouteParks):" + longResult);

        // -> shortestRouteBetweenTwoParks(String originParkIdentification, String destinationParkIdentification, String outputFileName)
        longResult = f.shortestRouteBetweenTwoParks("Ribeira", "Trindade", 1, oShortRouteParks2);
        System.out.println("shortestRouteBetweenTwoParks(\"Ribeira\", \"Trindade\", oShortRouteParks1):" + longResult);

        // -> mostEnergyEfficientRouteBetweenTwoParks(String originParkIdentification, String destinationParkIdentification, String typeOfVehicle, String vehicleSpecs, String username, String outputFileName)
        longResult = f.mostEnergyEfficientRouteBetweenTwoParks("Trindade", "Ribeira", "bicycle", "17", "josuemotta", oEffRouteParks);
        System.out.println("mostEnergyEfficientRouteBetweenTwoParks(\"Trindade\", \"Ribeira\", \"bicycle\", \"17\", \"josuemotta\", oEffRouteParks):" + longResult);

        // -> mostEnergyEfficientRouteBetweenTwoParks(String originParkIdentification, String destinationParkIdentification, String typeOfVehicle, String vehicleSpecs, String username, String outputFileName)
        longResult = f.mostEnergyEfficientRouteBetweenTwoParks("Ribeira", "Trindade", "bicycle", "17", "josuemotta", oEffRouteParks2);
        System.out.println("mostEnergyEfficientRouteBetweenTwoParks(\"Ribeira\", \"Trindade\", \"bicycle\", \"17\", \"josuemotta\", oEffRouteParks):" + longResult);

    }
}
