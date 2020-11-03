package lapr.project.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
import lapr.project.data.*;
import lapr.project.assessment.*;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        //load database properties

        try {
            Properties properties
                    = new Properties(System.getProperties());
            InputStream input = new FileInputStream("target/classes/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Initial Database Setup
        BDUtils bdutil = new BDUtils();
        bdutil.startBD();

        new FacadeTests();
        bdutil.startBD();
        //new FacadeTestsScenario1();
        //bdutil.startBD();
        //new FacadeTestsScenario3();
        //bdutil.startBD();
        //new FacadeTestsScenario4();
    }
}
