package br.com.catalogo.dao;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.util.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemMidiaDAOJdbc implements ItemMidiaDAO {

    private static final String INSERT_SQL = "INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE item_midia SET titulo = ?, autor_diretor = ?, ano_lancamento = ?, genero = ?, sinopse = ?, tipo_midia = ? WHERE id = ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM item_midia WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM item_midia";
    private static final String DELETE_SQL = "DELETE FROM item_midia WHERE id = ?";
    private static final String SEARCH_SQL = "SELECT * FROM item_midia WHERE titulo LIKE ? OR autor_diretor LIKE ? OR genero LIKE ? OR sinopse LIKE ?";

    @Override
    public ItemMidia salvar(ItemMidia item) {
        // Implementação JDBC (Esqueleto)
        try (Connection conn = FabricaDeConexoes.getConnection()) {
            if (item.getId() == null) {
                // INSERT
                try (PreparedStatement stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                    preencherStatement(stmt, item);
                    stmt.executeUpdate();
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            item.setId(rs.getLong(1));
                        }
                    }
                }
            } else {
                // UPDATE
                try (PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
                    preencherStatement(stmt, item);
                    stmt.setLong(7, item.getId());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaDeConexoes.closeConnection();
        }
        return item;
    }

    @Override
    public ItemMidia buscarPorId(Long id) {
        // Implementação JDBC (Esqueleto)
        ItemMidia item = null;
        try (Connection conn = FabricaDeConexoes.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = extrairItemMidia(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaDeConexoes.closeConnection();
        }
        return item;
    }

    @Override
    public List<ItemMidia> buscarTodos() {
        // Implementação JDBC (Esqueleto)
        List<ItemMidia> lista = new ArrayList<>();
        try (Connection conn = FabricaDeConexoes.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {
            while (rs.next()) {
                lista.add(extrairItemMidia(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaDeConexoes.closeConnection();
        }
        return lista;
    }

    @Override
    public boolean remover(Long id) {
        // Implementação JDBC (Esqueleto)
        try (Connection conn = FabricaDeConexoes.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            FabricaDeConexoes.closeConnection();
        }
    }

    @Override
    public List<ItemMidia> buscarPorTermo(String termo) {
        // Implementação JDBC (Esqueleto)
        List<ItemMidia> lista = new ArrayList<>();
        String termoLike = "%" + (termo == null ? "" : termo) + "%";
        try (Connection conn = FabricaDeConexoes.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SEARCH_SQL)) {
            stmt.setString(1, termoLike);
            stmt.setString(2, termoLike);
            stmt.setString(3, termoLike);
            stmt.setString(4, termoLike);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(extrairItemMidia(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaDeConexoes.closeConnection();
        }
        return lista;
    }

    private void preencherStatement(PreparedStatement stmt, ItemMidia item) throws SQLException {
        stmt.setString(1, item.getTitulo());
        stmt.setString(2, item.getAutorDiretor());
        stmt.setInt(3, item.getAnoLancamento());
        stmt.setString(4, item.getGenero());
        stmt.setString(5, item.getSinopse());
        stmt.setString(6, item.getTipoMidia());
    }

    private ItemMidia extrairItemMidia(ResultSet rs) throws SQLException {
        return new ItemMidia(
                rs.getLong("id"),
                rs.getString("titulo"),
                rs.getString("autor_diretor"),
                rs.getInt("ano_lancamento"),
                rs.getString("genero"),
                rs.getString("sinopse"),
                rs.getString("tipo_midia")
        );
    }
}
