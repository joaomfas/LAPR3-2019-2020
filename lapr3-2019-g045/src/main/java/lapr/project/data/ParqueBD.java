/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Thais Farias
 */
public class ParqueBD extends DataHandler {

    public Parque getParque(String id) {

        /* Objeto "callStmt" para invocar a função "getSailor" armazenada na BD.
         *
         * FUNCTION getParque(id NUMBER) 
         * 
         */
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getParque(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getSailor".
            callStmt.setObject(2, id);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Double latitude = rSet.getDouble(2);
                Double longitude = rSet.getDouble(3);
                Integer elevacao = rSet.getInt(4);
                String descricao = rSet.getString(5);
                Integer lot_bike = rSet.getInt(6);
                Integer lot_scooter = rSet.getInt(7);
                Integer voltagem = rSet.getInt(8);
                Integer corrente = rSet.getInt(9);
                Integer ativo = rSet.getInt(10);
                Parque parque = new Parque(id, latitude, longitude, elevacao, descricao, lot_bike, lot_scooter, voltagem, corrente);
                parque.setAtivo(ativo);
                return parque;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Não existe parque com ID:" + id);
    }

    /**
     *
     * Método add parque a DB
     *
     * @param parque
     * @return
     */
    public boolean addParque(Parque parque) {
        ArrayList<Parque> pqs = new ArrayList<>();
        pqs.add(parque);
        return addAll(pqs);
    }

    public Parque getParque(Double v1, Double v2) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ ? = call getParqueComCoordenadas(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setDouble(2, v1);
            callStmt.setDouble(3, v2);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String idParque = rSet.getString(1);
                Double latitu = rSet.getDouble(2);
                Double longitu = rSet.getDouble(3);
                Integer elev = rSet.getInt(4);
                String desc = rSet.getString(5);
                Integer lotBike = rSet.getInt(6);
                Integer lotScooter = rSet.getInt(7);
                Integer voltagem = rSet.getInt(8);
                Integer corrente = rSet.getInt(9);
                Integer ativo  = rSet.getInt(10);
                Parque parque = new Parque(idParque, latitu, longitu, elev, desc, lotBike, lotScooter, voltagem, corrente);
                parque.setAtivo(ativo);
                return parque;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Não existe parque com as coordenadas: " + v1 + ", " + v2);
    }

    public boolean atualizaParque(Parque parque) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{call atualizaParque(?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setObject(1, parque.getIdParque());
            callStmt.setObject(2, parque.getLatitude());
            callStmt.setObject(3, parque.getLongitude());
            callStmt.setObject(4, parque.getElevacao());
            callStmt.setObject(5, parque.getDescricao());
            callStmt.setObject(6, parque.getLotBike());
            callStmt.setObject(7, parque.getLotScooter());
            callStmt.setObject(8, parque.getVoltagem());
            callStmt.setObject(9, parque.getCorrente());
            callStmt.setObject(10, parque.getAtivo());

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

    /**
     * Retorna os parques na base de dados.Se a flag /ativos/ for verdadeira,
 retorna somente os parques ATIVOS. Caso contrário, retorna todos os
 parques, incluindo inativos.
     *
     * @param ativos
     * @return
     */
    public List<Parque> getParques(boolean ativos) {
        CallableStatement callStmt = null;
        List<Parque> parques = null;
        
        int atv = ativos ? 1 : 0;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getParques(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setObject(2, atv);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            parques = parquesFromResultSet(rSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parques;
    }

    private List<Parque> parquesFromResultSet(ResultSet rSet) {
        ArrayList<Parque> bcs = new ArrayList<>();

        try {
            while (rSet.next()) {
                String idParque = rSet.getString(1);
                Double latitude = rSet.getDouble(2);
                Double longitude = rSet.getDouble(3);
                Integer elevacao = rSet.getInt(4);
                String desc = rSet.getString(5);
                Integer lotBike = rSet.getInt(6);
                Integer lotScooter = rSet.getInt(7);
                Integer voltagem = rSet.getInt(8);
                Integer corrente = rSet.getInt(9);
                Integer ativo  = rSet.getInt(10);
                Parque c = new Parque(idParque, latitude, longitude, elevacao, desc, lotBike, lotScooter, voltagem, corrente);
                c.setAtivo(ativo);
                bcs.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bcs;
    }

    public boolean addAll(ArrayList<Parque> pqs) {
        CallableStatement callStmt = null;
        String idParque = null;
        openConnection();
        try {
            for (Parque parque : pqs) {
                callStmt = getConnection().prepareCall("{call addParque(?,?,?,?,?,?,?,?,?) }");

                callStmt.setString(1, parque.getIdParque());
                callStmt.setDouble(2, parque.getLatitude());
                callStmt.setDouble(3, parque.getLongitude());
                callStmt.setInt(4, parque.getElevacao());
                callStmt.setString(5, parque.getDescricao());
                callStmt.setInt(6, parque.getLotBike());
                callStmt.setInt(7, parque.getLotScooter());
                callStmt.setInt(8, parque.getVoltagem());
                callStmt.setInt(9, parque.getCorrente());

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

    public int verificaLugaresDisponiveis(String id, String username) {

        int lugares = 0;

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call verificaLotacaoVeiculoParque(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.NUMBER);

            callStmt.setString(3, id);
            callStmt.setString(2, username);
            callStmt.execute();
            lugares = callStmt.getInt(1);
            return lugares;
        } catch (SQLException e) {
            Logger.getLogger(ParqueBD.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        } finally {
            closeAll();
        }

    }

}
