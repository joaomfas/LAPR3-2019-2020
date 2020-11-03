package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

public class POIBD extends DataHandler {

    public boolean addPOI(POI poi) {
        ArrayList<POI> pois = new ArrayList<>();
        pois.add(poi);
        return addAll(pois);
    }

    public POI getPOI(Double v1, Double v2) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPOIComCoordenadas(?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setObject(2, v1);
            callStmt.setObject(3, v2);
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Double latitude = rSet.getDouble("latitude");
                Double longitude = rSet.getDouble("longitude");
                Integer elevacao = rSet.getInt("elevacao");
                String descricao = rSet.getString("descricao");
                POI poi = new POI(latitude, longitude, elevacao, descricao);
                return poi;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CaminhoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Nenhum POI com coordenadas: " + v1 + ", " + v2);
    }

    public List<POI> getPOIs() {
        CallableStatement callStmt = null;
        List<POI> lista = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPOIS(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setObject(2, null);
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                Double latitude = rSet.getDouble("latitude");
                Double longitude = rSet.getDouble("longitude");
                Integer elevacao = rSet.getInt("elevacao");
                String descricao = rSet.getString("descricao");
                POI poi = new POI(latitude, longitude, elevacao, descricao);
                lista.add(poi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CaminhoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll();
        }
        return lista;
    }

    public boolean removePOI(POI poi) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call removePOI(?,?) }");
            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setObject("lat", poi.getLatitude());
            callStmt.setObject("lon", poi.getLongitude());
            callStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(POIBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll();
        }
        return false;
    }

    public boolean addAll(ArrayList<POI> pois) {
        CallableStatement callStmt = null;
        openConnection();
        try {
            for (POI poi : pois) {
                callStmt = getConnection().prepareCall("{call addPOI(?,?,?,?) }");

                callStmt.setObject(1, poi.getLatitude());
                callStmt.setObject(2, poi.getLongitude());
                callStmt.setObject(3, poi.getElevacao());
                callStmt.setObject(4, poi.getDescricao());

                callStmt.execute();
            }
            commit();
            return true;
        } catch (SQLException e) {
            rollback();
            Logger.getLogger(ParqueBD.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            closeAll();
        }
    }

}
