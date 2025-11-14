package com.jin.catalogo.service;

import com.jin.catalogo.dao.ItemMidiaDAO;
import com.jin.catalogo.dao.ItemMidiaDAOInMemory;
import com.jin.catalogo.dao.ItemMidiaDAOJdbc;
import com.jin.catalogo.model.ItemMidia;
import java.util.List;

/**
 * Camada de Serviço (Service) para ItemMidia.
 * Contém a lógica de negócio e atua como intermediário entre os Servlets e o DAO.
 */
public class ItemMidiaService {

    private final ItemMidiaDAO dao;

    /**
     * Construtor que inicializa o DAO.
     * COMENTÁRIO IMPORTANTE: Para alternar entre a versão em memória e a versão JDBC,
     * basta descomentar a linha desejada e comentar a outra.
     */
    public ItemMidiaService() {
        // Versão em memória (para testes rápidos)
        this.dao = new ItemMidiaDAOInMemory();

        // Versão JDBC (para uso com banco de dados MySQL)
        // this.dao = new ItemMidiaDAOJdbc();
    }

    /**
     * Valida e insere um novo item de mídia.
     * @param item O item a ser inserido.
     * @throws IllegalArgumentException Se o título for nulo ou vazio.
     */
    public void inserir(ItemMidia item) {
        validar(item);
        dao.inserir(item);
    }

    /**
     * Lista todos os itens de mídia.
     * @return Uma lista de ItemMidia.
     */
    public List<ItemMidia> listarTodos() {
        return dao.listarTodos();
    }

    /**
     * Busca um item de mídia pelo seu ID.
     * @param id O ID do item.
     * @return O ItemMidia encontrado ou null.
     */
    public ItemMidia buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    /**
     * Valida e atualiza um item de mídia existente.
     * @param item O item com os dados atualizados.
     * @throws IllegalArgumentException Se o título for nulo ou vazio.
     */
    public void atualizar(ItemMidia item) {
        validar(item);
        dao.atualizar(item);
    }

    /**
     * Exclui um item de mídia pelo seu ID.
     * @param id O ID do item a ser excluído.
     */
    public void excluir(int id) {
        dao.excluir(id);
    }

    /**
     * Busca itens de mídia por termo.
     * @param termo O termo de busca.
     * @return Uma lista de ItemMidia que correspondem ao termo.
     */
    public List<ItemMidia> buscarPorTermo(String termo) {
        return dao.buscarPorTermo(termo);
    }

    /**
     * Realiza a validação das regras de negócio.
     * @param item O item a ser validado.
     * @throws IllegalArgumentException Se a validação falhar.
     */
    private void validar(ItemMidia item) {
        if (item.getTitulo() == null || item.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O título do item de mídia é obrigatório e não pode ser vazio.");
        }
        // Outras validações de negócio podem ser adicionadas aqui
    }
}
