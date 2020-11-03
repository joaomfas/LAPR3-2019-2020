package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Caminho;
import lapr.project.model.Parque;
import oracle.jdbc.OracleTypes;

public class CaminhoBD extends DataHandler {

    /**
     * *
     * Adiciona um caminho à base de dados. Retorna o id do caminho adicionado
     *
     * @param c
     * @return
     */
    public boolean addCaminho(Caminho c) {
        ArrayList<Caminho> cms = new ArrayList<>();
        cms.add(c);
        return addAll(cms);
    }

    /**
     * *
     * Faz update de um caminho. É utilizado o id do objeto inserido. Retorna
     * true se o update funcionar.
     *
     * @param c
     * @return
     */
    public boolean updateCaminho(Caminho c) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{call atualizaCaminho(?,?,?,?,?,?,?) }");

            callStmt.setDouble(1, c.getLatA());
            callStmt.setDouble(2, c.getLonA());
            callStmt.setDouble(3, c.getLatB());
            callStmt.setDouble(4, c.getLonB());
            callStmt.setDouble(5, c.getCoefCinetico());
            callStmt.setDouble(6, c.getVelVento());
            callStmt.setDouble(7, c.getDirVento());

            callStmt.execute();
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    public boolean addAll(ArrayList<Caminho> cms) {
        CallableStatement callStmt = null;
        openConnection();
        try {
            for (Caminho c : cms) {
                callStmt = getConnection().prepareCall("{call addCaminho(?,?,?,?,?,?,?) }");

                callStmt.setObject(1, c.getLatA());
                callStmt.setObject(2, c.getLonA());
                callStmt.setObject(3, c.getLatB());
                callStmt.setObject(4, c.getLonB());
                callStmt.setObject(5, c.getCoefCinetico());
                callStmt.setObject(6, c.getDirVento());
                callStmt.setObject(7, c.getVelVento());

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

    public boolean removeCaminho(Caminho c) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call removeCaminho(?,?,?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);
            callStmt.setObject("latA", c.getLatA());
            callStmt.setObject("lonA", c.getLonA());
            callStmt.setObject("latB", c.getLatB());
            callStmt.setObject("lonB", c.getLonB());

            callStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CaminhoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll();
        }
        return false;
    }

    public Caminho getCaminho(double lat1, double lon1, double lat2, double lon2) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCaminhoComCoordenadas(?, ?, ?, ?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setDouble(2, lat1);
            callStmt.setDouble(3, lon1);
            callStmt.setDouble(4, lat2);
            callStmt.setDouble(5, lon2);
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                double latA = rSet.getDouble("latitude_inicial");
                double lonA = rSet.getDouble("longitude_inicial");
                double latB = rSet.getDouble("latitude_final");
                double lonB = rSet.getDouble("longitude_final");
                double coefCinetico = rSet.getDouble("coeficiente_cinetico");
                double dirVento = rSet.getDouble("direcao_vento");
                double velVento = rSet.getDouble("velocidade_vento");
                Caminho c = new Caminho(latA, lonA, latB, lonB, coefCinetico, dirVento, velVento);
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CaminhoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Nenhum caminho encontrado entre { [ " + lat1 + ", " + lon1 + " ] e [ " + lat2 + ", " + lon2 + "] }");

    }

    public List<Caminho> getCaminhos() {
        CallableStatement callStmt = null;
        List<Caminho> lista = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCaminhos(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setObject(2, null);
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                double latA = rSet.getDouble("latitude_inicial");
                double lonA = rSet.getDouble("longitude_inicial");
                double latB = rSet.getDouble("latitude_final");
                double lonB = rSet.getDouble("longitude_final");
                double coefCinetico = rSet.getDouble("coeficiente_cinetico");
                double dirVento = rSet.getDouble("direcao_vento");
                double velVento = rSet.getDouble("velocidade_vento");
                Caminho c = new Caminho(latA, lonA, latB, lonB, coefCinetico, dirVento, velVento);
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CaminhoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAll();
        }
        return lista;
    }
}
