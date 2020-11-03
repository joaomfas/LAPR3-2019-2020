package lapr.project.data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDUtils {

    DataHandler dh = new DataHandler();
    String folderPath = System.getProperty("user.dir");

    public void startBD() {
        try {
            dh.scriptRunner(folderPath + "/src/main/resources/BaseDeDados/tabelas.sql");
            dh.scriptRunner(folderPath + "/src/main/resources/BaseDeDados/functions.sql");
            dh.scriptRunner(folderPath + "/src/main/resources/BaseDeDados/inserts.sql");
            dh.scriptRunner(folderPath + "/src/main/resources/BaseDeDados/triggers.sql");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(BDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startBDFacade() {
        try {
            dh.scriptRunner("../lapr3-2019-g045" + "/src/main/resources/BaseDeDados/tabelas.sql");
            dh.scriptRunner("../lapr3-2019-g045" + "/src/main/resources/BaseDeDados/functions.sql");
            dh.scriptRunner("../lapr3-2019-g045" + "/src/main/resources/BaseDeDados/inserts.sql");
            dh.scriptRunner("../lapr3-2019-g045" + "/src/main/resources/BaseDeDados/triggers.sql");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(BDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void runStript(String sript) {
        try {
            dh.scriptRunner(folderPath + sript);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(BDUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
