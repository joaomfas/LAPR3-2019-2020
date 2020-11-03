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
public class FacadeTestsScenario1 {

    FacadeCopy f;

    String fPois = "src/main/resources/FicheirosTesteScenarios/input/pois.csv";
    String fParks = "src/main/resources/FicheirosTesteScenarios/input/parks.csv";
    String fUsers = "src/main/resources/FicheirosTesteScenarios/input/users.csv";
    String fPaths = "src/main/resources/FicheirosTesteScenarios/input/paths.csv";

    String oEffRouteParks = "src/main/resources/FicheirosTesteScenarios/output/effRouteParks.csv";
    String oEffRouteParks2 = "src/main/resources/FicheirosTesteScenarios/output/effRouteParks2.csv";
    String oShortRouteParks = "src/main/resources/FicheirosTesteScenarios/output/shortRouteParks.csv";
    String oShortRouteParks2 = "src/main/resources/FicheirosTesteScenarios/output/shortRouteParks2.csv";

    int intResult;
    double doubleResult;
    long longResult;
    String stringResult;

    public FacadeTestsScenario1() {
        f = new FacadeCopy();
        inserts();
        distances();
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

    public void distances() {
        // -> linearDistanceTo(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees)
        intResult = f.linearDistanceTo(41.14582, -8.61398, 41.14723, -8.60657); // clérigos -> majestic
        System.out.println("linearDistanceTo Clérigos -> Majestic: " + intResult);
        // -> linearDistanceTo(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees)
        intResult = f.linearDistanceTo(41.14582, -8.61398, 41.14871, -8.60746); // Clérigos -> Bolhão
        System.out.println("linearDistanceTo Clérigos -> Bolhão: " + intResult);
        // -> linearDistanceTo(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees)
        intResult = f.linearDistanceTo(41.14871, -8.60746, 41.14063, -8.61118); // Bolhão -> Cais da Ribeira
        System.out.println("linearDistanceTo Bolhão -> Cais da Ribeira: " + intResult);
        // -> linearDistanceTo(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees)
        intResult = f.linearDistanceTo(41.14723, -8.60657, 41.14063, -8.61118); // Majestic -> Cais da Ribeira
        System.out.println("linearDistanceTo Majestic -> Cais da Ribeira: " + intResult);
        // -> linearDistanceTo(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees)
        intResult = f.linearDistanceTo(41.14582, -8.61398, 41.14723, -8.60657) + f.linearDistanceTo(41.14723, -8.60657, 41.14063, -8.61118);
        //  Clérigos -> Majestic -> Cais da Ribeira
        System.out.println("linearDistanceTo(41.15227, -8.60929, 41.14063, -8.61118   +  41.14723,-8.60657, 41.14063,-8.61118): " + intResult);
        // -> linearDistanceTo(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinyLatitudeInDegrees, double destinyLongitudeInDegrees)
        intResult = f.linearDistanceTo(41.14582, -8.61398, 41.14871, -8.60746) + f.linearDistanceTo(41.14871, -8.60746, 41.14063, -8.61118); // Clérigos -> Bolhão -> Cais da Ribeira
        System.out.println("linearDistanceTo(41.14582,-8.61398, 41.14871,-8.60746) + (41.14871,-8.60746, 41.14063,-8.61118: \n" + intResult);

    }

    public void paths() {

        // -> shortestRouteBetweenTwoParks(double originLatitudeInDegrees, double originLongitudeInDegrees, double destinationLatitudeInDegrees, double destinationLongitudeInDegrees, String outputFileName)
        longResult = f.shortestRouteBetweenTwoParks(41.15227, -8.60929, 41.14063, -8.61118, 1, oShortRouteParks); //Trindade -> Cais
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
