package br.com.catalogo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexoes {

    private static final String URL = "jdbc:mysql://localhost:3306/catalogo_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    // Variável para armazenar a conexão atual (opcional, mas útil para o closeConnection)
    private static Connection connection = null;

    /**
     * Bloco static comentado para evitar falhas de inicialização no Codespaces,
     * conforme solicitado. O driver moderno (MySQL Connector/J 8.0+) é
     * carregado automaticamente pelo JDBC 4.0+.
     */
    /*
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado.");
            e.printStackTrace();
        }
    }
    */

    /**
     * Retorna uma nova conexão com o banco de dados.
     * @return Uma conexão ativa.
     * @throws SQLException Se ocorrer um erro de conexão.
     */
    public static Connection getConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.err.println("Erro ao obter conexão com o banco de dados.");
            throw e;
        }
    }

    /**
     * Fecha a conexão que foi aberta pelo método getConnection().
     * O nome do método é closeConnection, conforme solicitado.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Resetar a variável
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão.");
                e.printStackTrace();
            }
        }
    }
}
