package lapr.project.data;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exemplo de classe cujas instâncias manipulam dados de BD Oracle.
 */
public class DataHandler {
    /**
     * O URL da BD.
     */
    private String jdbcUrl = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";

    /**
     * O nome de utilizador da BD.
     */
    private String username = "LAPR3_2019_G045";

    /**
     * A password de utilizador da BD.
     */
    private String password = "qwertyytrewq";

    /**
     * A ligação à BD.
     */
    private Connection connection;

    /**
     * A invocação de "stored procedures".
     */
    private CallableStatement callStmt;

    /**
     * Conjunto de resultados retornados por "stored procedures".
     */
    private ResultSet rSet;

    /**
     * Use connection properties set on file application.properties
     */
    public DataHandler() {
			
    }

    /**
     * Constrói uma instância de "DataHandler" recebendo, por parâmetro, o URL
     * da BD e as credenciais do utilizador.
     *
     * @param jdbcUrl  o URL da BD.
     * @param username o nome do utilizador.
     * @param password a password do utilizador.
     */
    public DataHandler(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        connection = null;
        callStmt = null;
        rSet = null;
    }

    /**
     * Allows running entire scripts
     *
     * @param fileName
     * @throws IOException
     * @throws SQLException
     */
    public void scriptRunner(String fileName) throws IOException, SQLException {

        openConnection();

        ScriptRunner runner = new ScriptRunner(getConnection(), false, false);

        runner.runScript(new BufferedReader(new FileReader(fileName)));

        closeAll();

    }

    /**
     * Estabelece a ligação à BD.
     */
    protected void openConnection() {
        try {
            connection = DriverManager.getConnection(
                    jdbcUrl, username, password);
            autocommitOff();
        } catch (SQLException e) {
			Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    /**
     * Fecha os objetos "ResultSet", "CallableStatement" e "Connection", e
     * retorna uma mensagem de erro se alguma dessas operações não for bem
     * sucedida. Caso contrário retorna uma "string" vazia.
     */
    protected String closeAll() {

        StringBuilder message = new StringBuilder();

        if (rSet != null) {
            try {
                rSet.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            rSet = null;
        }

        if (callStmt != null) {
            try {
                callStmt.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            callStmt = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            connection = null;
        }
        return message.toString();
    }


    protected Connection getConnection() {
        if (connection == null)
            openConnection();
        return connection;
    }


    public void autocommitOff(){
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void commit(){
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
