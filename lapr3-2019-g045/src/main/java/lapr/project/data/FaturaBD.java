
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Fatura;
import oracle.jdbc.OracleTypes;


public class FaturaBD extends DataHandler {

    public Fatura getFatura(String username, Integer mes) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getFatura(?, ?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setObject(2, username);
            callStmt.setObject(3, mes);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                Fatura fatura = new Fatura();
                fatura.setIdFatura(rSet.getInt(1));
                fatura.setUsername(username);
                fatura.setMes(mes);
                fatura.setPontosAnteriores(rSet.getInt(4));
                fatura.setPontosGanhos(rSet.getInt(5));
                fatura.setPontosDescontados(rSet.getInt(6));
                fatura.setPontosAtuais(rSet.getInt(7));
                fatura.setValorCobrado(rSet.getDouble(8));
                fatura.setValorPago(rSet.getDouble(9));
                return fatura;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Não fatura para este periodo.");
    }
    
    public List<Fatura> getFaturasUtilizador(String username) {
        List<Fatura> faturas = new ArrayList<>();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getFaturas(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setObject(2, username);
            

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                Fatura fatura = new Fatura();
                fatura.setIdFatura(rSet.getInt(1));
                fatura.setUsername(username);
                fatura.setMes(rSet.getInt(3));
                fatura.setPontosAnteriores(rSet.getInt(4));
                fatura.setPontosGanhos(rSet.getInt(5));
                fatura.setPontosDescontados(rSet.getInt(6));
                fatura.setPontosAtuais(rSet.getInt(7));
                fatura.setValorCobrado(rSet.getDouble(8));
                fatura.setValorPago(rSet.getDouble(9));
                faturas.add(fatura);
            }
            return faturas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Utilizador n tem faturas.");
    }
    

    public boolean addFatura(Fatura fatura) {
        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall(" {call addFatura(?, ?) }");
            callStmt.setObject(1, fatura.getUsername());
            callStmt.setObject(2, fatura.getMes());
            callStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    public boolean atualizaFatura(Fatura fatura) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ call atualizaFatura(?, ?, ?, ?, ?, ?, ?, ?, ?) }");
            callStmt.setObject(1, fatura.getIdFatura());
            callStmt.setObject(2, fatura.getUsername());
            callStmt.setObject(3, fatura.getMes());
            callStmt.setObject(4, fatura.getPontosAnteriores());
            callStmt.setObject(5, fatura.getPontosGanhos());
            callStmt.setObject(6, fatura.getPontosDescontados());
            callStmt.setObject(7, fatura.getPontosAtuais());
            callStmt.setObject(8, fatura.getValorCobrado());
            callStmt.setObject(9, fatura.getValorPago());
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
    
    //-getFaturaViagemDetalhe
    public List<String> getDetalheFatura(Fatura fatura) {
        return getDetalhe("{ ? = call getDetalheFatura(?) }", fatura.getIdFatura());
    }
    
    
    public List<String> getDetalheAtual(String username) {
        return getDetalhe("{ ? = call getDetalheFaturaAtual(?) }", username);
    }
    
    public List<String> getDetalhe(String stmt, Object param1) {
        List<String> resultado = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall(stmt);

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setObject(2, param1);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
  
            while (rSet.next()) {
                String descVeiculo = rSet.getString(1);
                Date dataHoraInicio = rSet.getDate(2);
                String sDataHoraInicio = format.format(dataHoraInicio);                
                Date dataHoraFim = rSet.getDate(3);
                String sDataHoraFim = format.format(dataHoraFim);
                Double poLat = rSet.getDouble(4);
                Double poLon = rSet.getDouble(5);
                Double pdLat = rSet.getDouble(6);
                Double pdLon = rSet.getDouble(7);
                Integer tempoSegundos = rSet.getInt(8);
                Double total = rSet.getDouble(9);
                resultado.add(descVeiculo + ";" + sDataHoraInicio + ";" + sDataHoraFim + 
                        ";" + poLat + ";" + poLon + ";" + pdLat + ";" + pdLon +
                        ";" + tempoSegundos + ";" + total);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Não fatura para este periodo.");
    }
    

    
    public List<String> getDetalhePontos(String username) {
        List<String> resultado = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        CallableStatement callStmt = null;
        try {
            //devolve detalhe das viagens não faturadas.
            callStmt = getConnection().prepareCall("{ ? = call getDetalhePontos(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setObject(2, username);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
  
            while (rSet.next()) {
                String descVeiculo = rSet.getString(1);
                Date dataHoraInicio = rSet.getDate(2);
                String sDataHoraInicio = format.format(dataHoraInicio);                
                Date dataHoraFim = rSet.getDate(3);
                String sDataHoraFim = format.format(dataHoraFim);
                Double poLat = rSet.getDouble(4);
                Double poLon = rSet.getDouble(5);
                Double poEle = rSet.getDouble(6);
                Double pdLat = rSet.getDouble(7);
                Double pdLon = rSet.getDouble(8);
                Double pdEle = rSet.getDouble(9);
                Double eleDif = rSet.getDouble(10);
                Integer pontos = rSet.getInt(11);
                resultado.add(descVeiculo + ";" + sDataHoraInicio + ";" + sDataHoraFim + 
                        ";" + poLat + ";" + poLon + ";"  + poEle + ";" + pdLat + ";" + pdLon + ";" + pdEle +
                        ";" + eleDif + ";" + pontos);
            }
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Não fatura para este periodo.");
    }
    
    
    
    
    
}
