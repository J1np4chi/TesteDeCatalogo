package com.jin.catalogo.dao;

import com.jin.catalogo.model.ItemMidia;
import java.util.List;

/**
 * Interface que define o contrato para as operações de persistência de ItemMidia.
 */
public interface ItemMidiaDAO {

    /**
     * Insere um novo item de mídia.
     * @param item O item a ser inserido.
     */
    void inserir(ItemMidia item);

    /**
     * Lista todos os itens de mídia.
     * @return Uma lista de ItemMidia.
     */
    List<ItemMidia> listarTodos();

    /**
     * Busca um item de mídia pelo seu ID.
     * @param id O ID do item.
     * @return O ItemMidia encontrado ou null se não existir.
     */
    ItemMidia buscarPorId(int id);

    /**
     * Atualiza um item de mídia existente.
     * @param item O item com os dados atualizados.
     */
    void atualizar(ItemMidia item);

    /**
     * Exclui um item de mídia pelo seu ID.
     * @param id O ID do item a ser excluído.
     */
    void excluir(int id);

    /**
     * Busca itens de mídia cujo título ou sinopse contenham o termo de busca.
     * @param termo O termo de busca.
     * @return Uma lista de ItemMidia que correspondem ao termo.
     */
    List<ItemMidia> buscarPorTermo(String termo);
}
