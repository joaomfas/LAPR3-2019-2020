package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import lapr.project.model.Viagem;
import oracle.jdbc.OracleTypes;

public class ViagemBD extends DataHandler {

    public Viagem getViagemByUtilizador(Integer id_utilizador) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getViagemUtilizador(?) }");
            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getViagemByUtilizador".
            callStmt.setInt(2, id_utilizador);

            // Executa a invocação da função "getViagemByUtilizador".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                Integer id_viagem = rSet.getInt(1);

                id_utilizador = rSet.getInt(2);

                String desc_veiculo = rSet.getString(3);

                String id_parque_inicio = rSet.getString(4);

                String id_parque_fim = rSet.getString(5);

                Date data_hora_inicio = rSet.getDate(6);

                Date data_hora_fim = rSet.getDate(7);

                return new Viagem(id_viagem, id_utilizador, desc_veiculo, id_parque_inicio, id_parque_fim, data_hora_inicio, data_hora_fim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Não existem viagens de momento para este utilizador id : " + id_utilizador);
    }
    
    public Viagem getViagemByVeiculo(String descVeiculo) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getViagemVeiculo(?) }");
            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getViagemByUtilizador".
            callStmt.setObject(2, descVeiculo);

            // Executa a invocação da função "getViagemByUtilizador".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                Integer id_viagem = rSet.getInt(1);

                Integer id_utilizador = rSet.getInt(2);

                String desc_veiculo = rSet.getString(3);

                String id_parque_inicio = rSet.getString(4);

                String id_parque_fim = rSet.getString(5);

                Date data_hora_inicio = rSet.getDate(6);

                Date data_hora_fim = rSet.getDate(7);

                return new Viagem(id_viagem, id_utilizador, desc_veiculo, id_parque_inicio, id_parque_fim, data_hora_inicio, data_hora_fim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Não existem viagens de momento para este utilizador id : " + descVeiculo);
    }

    public boolean addViagem(Viagem viagem) {

        try {
            openConnection();
            CallableStatement callStmt = getConnection().prepareCall(" {call addViagem(?,?,?,?) }");
            callStmt.setObject(1, viagem.getIdUtilizador());
            callStmt.setObject(2, viagem.getDescVeiculo());
            callStmt.setObject(3, viagem.getIdParqueInicio());
            callStmt.setObject(4, viagem.getDataHoraInicio());
            callStmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;

    }

    
    public boolean updateViagem(Viagem viagem) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{ call atualizaViagem(?,?,?,?,?,?) }");
            callStmt.setObject(1, viagem.getIdUtilizador());
            callStmt.setObject(2, viagem.getDescVeiculo());
            callStmt.setObject(3, viagem.getIdParqueInicio());
            callStmt.setObject(4, viagem.getIdParqueFim());
            callStmt.setObject(5, viagem.getDataHoraInicio());
            callStmt.setObject(6, viagem.getDataHoraFim());

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


}
