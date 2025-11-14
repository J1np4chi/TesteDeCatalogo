package com.jin.catalogo.dao;

import com.jin.catalogo.model.ItemMidia;
import com.jin.catalogo.util.FabricaDeConexoes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do DAO de ItemMidia que armazena os dados em um banco de dados MySQL
 * usando JDBC e PreparedStatement.
 */
public class ItemMidiaDAOJdbc implements ItemMidiaDAO {

    private static final String INSERT_SQL = "INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM item_midia";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM item_midia WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE item_midia SET titulo = ?, autor_diretor = ?, ano_lancamento = ?, genero = ?, sinopse = ?, tipo_midia = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM item_midia WHERE id = ?";
    private static final String SEARCH_SQL = "SELECT * FROM item_midia WHERE titulo LIKE ? OR sinopse LIKE ?";

    /**
     * Insere um novo item de mídia no banco de dados.
     */
    @Override
    public void inserir(ItemMidia item) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = FabricaDeConexoes.getConnection();
            stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            
            // 1. titulo
            stmt.setString(1, item.getTitulo());
            // 2. autor_diretor
            stmt.setString(2, item.getAutorDiretor());
            // 3. ano_lancamento (trata nulidade)
            if (item.getAnoLancamento() != null) {
                stmt.setInt(3, item.getAnoLancamento());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            // 4. genero
            stmt.setString(4, item.getGenero());
            // 5. sinopse
            stmt.setString(5, item.getSinopse());
            // 6. tipo_midia
            stmt.setString(6, item.getTipoMidia());

            stmt.executeUpdate();

            // Recupera o ID gerado
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                item.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir item de mídia: " + e.getMessage());
            throw new RuntimeException("Erro ao inserir item de mídia.", e);
        } finally {
            FabricaDeConexoes.closeConnection(conn);
            // Os recursos Statement e ResultSet serão fechados automaticamente com o try-with-resources,
            // mas como FabricaDeConexoes.closeConnection só fecha a Connection, vamos fechar os outros aqui.
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* ignore */ }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* ignore */ }
        }
    }

    /**
     * Lista todos os itens de mídia do banco de dados.
     */
    @Override
    public List<ItemMidia> listarTodos() {
        List<ItemMidia> lista = new ArrayList<>();
        try (Connection conn = FabricaDeConexoes.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapResultSetToItemMidia(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar todos os itens de mídia: " + e.getMessage());
            throw new RuntimeException("Erro ao listar todos os itens de mídia.", e);
        }
        return lista;
    }

    /**
     * Busca um item de mídia pelo seu ID.
     */
    @Override
    public ItemMidia buscarPorId(int id) {
        ItemMidia item = null;
        try (Connection conn = FabricaDeConexoes.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = mapResultSetToItemMidia(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar item de mídia por ID: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar item de mídia por ID.", e);
        }
        return item;
    }

    /**
     * Atualiza um item de mídia existente no banco de dados.
     */
    @Override
    public void atualizar(ItemMidia item) {
        try (Connection conn = FabricaDeConexoes.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {

            // 1. titulo
            stmt.setString(1, item.getTitulo());
            // 2. autor_diretor
            stmt.setString(2, item.getAutorDiretor());
            // 3. ano_lancamento (trata nulidade)
            if (item.getAnoLancamento() != null) {
                stmt.setInt(3, item.getAnoLancamento());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            // 4. genero
            stmt.setString(4, item.getGenero());
            // 5. sinopse
            stmt.setString(5, item.getSinopse());
            // 6. tipo_midia
            stmt.setString(6, item.getTipoMidia());
            // 7. id (WHERE clause)
            stmt.setInt(7, item.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar item de mídia: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar item de mídia.", e);
        }
    }

    /**
     * Exclui um item de mídia pelo seu ID.
     */
    @Override
    public void excluir(int id) {
        try (Connection conn = FabricaDeConexoes.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao excluir item de mídia: " + e.getMessage());
            throw new RuntimeException("Erro ao excluir item de mídia.", e);
        }
    }

    /**
     * Busca itens de mídia cujo título ou sinopse contenham o termo de busca.
     */
    @Override
    public List<ItemMidia> buscarPorTermo(String termo) {
        List<ItemMidia> lista = new ArrayList<>();
        if (termo == null || termo.trim().isEmpty()) {
            return listarTodos();
        }
        try (Connection conn = FabricaDeConexoes.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SEARCH_SQL)) {

            String termoLike = "%" + termo + "%";
            stmt.setString(1, termoLike);
            stmt.setString(2, termoLike);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetToItemMidia(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar itens de mídia por termo: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar itens de mídia por termo.", e);
        }
        return lista;
    }

    /**
     * Mapeia um ResultSet para um objeto ItemMidia.
     * @param rs O ResultSet.
     * @return O objeto ItemMidia preenchido.
     * @throws SQLException Se houver um erro ao acessar os dados do ResultSet.
     */
    private ItemMidia mapResultSetToItemMidia(ResultSet rs) throws SQLException {
        ItemMidia item = new ItemMidia();
        item.setId(rs.getInt("id"));
        item.setTitulo(rs.getString("titulo"));
        item.setAutorDiretor(rs.getString("autor_diretor"));
        
        // Trata a nulidade do campo ano_lancamento
        int ano = rs.getInt("ano_lancamento");
        if (rs.wasNull()) {
            item.setAnoLancamento(null);
        } else {
            item.setAnoLancamento(ano);
        }
        
        item.setGenero(rs.getString("genero"));
        item.setSinopse(rs.getString("sinopse"));
        item.setTipoMidia(rs.getString("tipo_midia"));
        return item;
    }
}
