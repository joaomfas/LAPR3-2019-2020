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
public class FacadeTestsScenario3 {
      FacadeCopy f;

    String fPois = "src/main/resources/FicheirosTesteScenarios/input/pois3.csv";
    String fParks = "src/main/resources/FicheirosTesteScenarios/input/parks3.csv";
    String fUsers = "src/main/resources/FicheirosTesteScenarios/input/users3.csv";
    String fPaths = "src/main/resources/FicheirosTesteScenarios/input/paths3.csv";

    String oEffRouteParks = "src/main/resources/FicheirosTesteScenarios/output/effRouteParksScenario3.csv";
    String oEffRouteParks2 = "src/main/resources/FicheirosTesteScenarios/output/effRouteParksScenario3_2.csv";
    String oShortRouteParks = "src/main/resources/FicheirosTesteScenarios/output/shortRouteParksScenario3.csv";
    String oShortRouteParks2 = "src/main/resources/FicheirosTesteScenarios/output/shortRouteParksScenario_3.csv";

    int intResult;
    double doubleResult;
    long longResult;
    String stringResult;

    public FacadeTestsScenario3() {
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
        // -> addPaths(String inputFile)
        f.addPaths(fPaths);
        
        f.addUsers(fUsers);
        //f.addUsers(fUsersR);
        // -> registerUser(String username, String email, String password, String visaCardNumber, int height, int weight, String gender)
        // josué
    }

    public void paths() {

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