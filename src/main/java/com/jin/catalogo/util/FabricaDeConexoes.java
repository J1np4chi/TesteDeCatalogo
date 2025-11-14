package com.jin.catalogo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitária para fornecer conexões com o banco de dados.
 */
public class FabricaDeConexoes {

    // URL de conexão com o banco de dados MySQL.
    // O usuário deve alterar o nome do banco (catalogo_db), USER e PASS.
    private static final String URL = "jdbc:mysql://localhost:3306/catalogo_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Altere para o seu usuário do MySQL
    private static final String PASS = "password"; // Altere para sua senha do MySQL

    /**
     * Retorna uma nova conexão com o banco de dados.
     * @return Objeto Connection.
     * @throws SQLException Se ocorrer um erro de conexão.
     */
    public static Connection getConnection() throws SQLException {
        // O driver do MySQL (a partir da versão 8) se registra automaticamente,
        // então não é estritamente necessário usar Class.forName().
        // No entanto, é uma boa prática para garantir que o driver esteja carregado.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Se o driver não for encontrado, a exceção é lançada.
            throw new SQLException("Driver JDBC do MySQL não encontrado.", e);
        }
        
        // Retorna a conexão usando o DriverManager
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * Fecha um objeto Connection.
     * @param conn A conexão a ser fechada.
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
