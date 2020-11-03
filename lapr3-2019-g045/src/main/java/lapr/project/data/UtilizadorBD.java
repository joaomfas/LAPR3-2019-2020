package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.POI;
import lapr.project.model.Utilizador;
import oracle.jdbc.OracleTypes;

public class UtilizadorBD extends DataHandler {

    public Utilizador getUserByEmail2(String email) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getUserByEmail(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int idUtilizador = rSet.getInt(1);
                String username = rSet.getString(2);
                String password = rSet.getString(3);
                email = rSet.getString(4);
                String nome = rSet.getString(5);
                String cartaoCredito = rSet.getString(6);
                float peso = rSet.getFloat(7);
                float altura = rSet.getFloat(8);
                float velocidadeMedia = rSet.getFloat(9);
                String genero = rSet.getString(10);
                Integer pontos = rSet.getInt(11);
                Utilizador u = new Utilizador(idUtilizador, username, password, email, nome, cartaoCredito, peso, altura, velocidadeMedia, genero);
                u.setPontos(pontos);
                return u;
            }
        } catch (SQLException e) {
            Logger.getLogger(UtilizadorBD.class.getName()).log(Level.SEVERE, null, e);
        }
        throw new IllegalArgumentException("No User with email:" + email);
    }
    
    public Utilizador getUtilizadorByUserName(String username) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getUtilizadorByUserName(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, username);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int idUtilizador = rSet.getInt(1);
                username = rSet.getString(2);
                String password = rSet.getString(3);
                String email = rSet.getString(4);
                String nome = rSet.getString(5);
                String cartaoCredito = rSet.getString(6);
                float peso = rSet.getFloat(7);
                float altura = rSet.getFloat(8);
                float velocidadeMedia = rSet.getFloat(9);
                String genero = rSet.getString(10);
                Integer pontos = rSet.getInt(11);
                Utilizador u = new Utilizador(idUtilizador, username, password, email, nome, cartaoCredito, peso, altura, velocidadeMedia, genero);
                u.setPontos(pontos);
                return u;
            }
        } catch (SQLException e) {
            Logger.getLogger(UtilizadorBD.class.getName()).log(Level.SEVERE, null, e);
        }
        throw new IllegalArgumentException("No User with username:" + username);
    }
    
    public Utilizador getUtilizador(int idUtilizador) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getUtilizador(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idUtilizador);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                idUtilizador = rSet.getInt(1);
                String username = rSet.getString(2);
                String password = rSet.getString(3);
                String email = rSet.getString(4);
                String nome = rSet.getString(5);
                String cartaoCredito = rSet.getString(6);
                float peso = rSet.getFloat(7);
                float altura = rSet.getFloat(8);
                float velocidadeMedia = rSet.getFloat(9);
                String genero = rSet.getString(10);
                Integer pontos = rSet.getInt(11);
                Utilizador u = new Utilizador(idUtilizador, username, password, email, nome, cartaoCredito, peso, altura, velocidadeMedia, genero);
                u.setPontos(pontos);
                return u;
            }
        } catch (SQLException e) {
            Logger.getLogger(UtilizadorBD.class.getName()).log(Level.SEVERE, null, e);
        }
        throw new IllegalArgumentException("No User with userID :" + idUtilizador);
    }

    public boolean atualizaUtilizador (Utilizador user) {
        CallableStatement callStmt = null;
        try {
            openConnection();
            callStmt = getConnection().prepareCall("{call atualizaUtilizador(?,?,?,?,?,?,?,?,?) }");

            callStmt.setObject(1, user.getIdUtilizador());
            callStmt.setObject(2, user.getPassword());
            callStmt.setObject(3, user.getEmail());
            callStmt.setObject(4, user.getNome());
            callStmt.setObject(5, user.getCartaoCredito());
            callStmt.setObject(6, user.getPeso());
            callStmt.setObject(7, user.getAltura());
            callStmt.setObject(8, user.getVelocidadeMedia());
            callStmt.setObject(9, user.getGenero().substring(0, 1));

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

    public boolean addUtilizador(Utilizador user) {
        ArrayList<Utilizador> users = new ArrayList<>();
        users.add(user);
        return addAll(users);
    }

    public boolean addAll(ArrayList<Utilizador> users) {
        CallableStatement callStmt = null;
        openConnection();
        try {
            for (Utilizador user : users) {
                callStmt = getConnection().prepareCall("{ call addUtilizador(?,?,?,?,?,?,?,?,?) }");
                callStmt.setObject(1, user.getUsername());
                callStmt.setObject(2, user.getPassword());
                callStmt.setObject(3, user.getEmail());
                callStmt.setObject(4, user.getNome());
                callStmt.setObject(5, user.getCartaoCredito());
                callStmt.setObject(6, user.getPeso());
                callStmt.setObject(7, user.getAltura());
                callStmt.setObject(8, user.getVelocidadeMedia());
                callStmt.setObject(9, user.getGenero().substring(0, 1));
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
