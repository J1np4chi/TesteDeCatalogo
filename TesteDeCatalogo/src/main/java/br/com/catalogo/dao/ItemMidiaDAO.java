package br.com.catalogo.dao;

import br.com.catalogo.model.ItemMidia;
import java.util.List;

public interface ItemMidiaDAO {
    
    /**
     * Salva um novo ItemMidia ou atualiza um existente.
     * @param item O ItemMidia a ser salvo ou atualizado.
     * @return O ItemMidia salvo, com o ID preenchido se for um novo registro.
     */
    ItemMidia salvar(ItemMidia item);

    /**
     * Busca um ItemMidia pelo seu ID.
     * @param id O ID do ItemMidia.
     * @return O ItemMidia encontrado ou null se não existir.
     */
    ItemMidia buscarPorId(Long id);

    /**
     * Retorna todos os ItemMidia.
     * @return Uma lista de todos os ItemMidia.
     */
    List<ItemMidia> buscarTodos();

    /**
     * Remove um ItemMidia pelo seu ID.
     * @param id O ID do ItemMidia a ser removido.
     * @return true se o item foi removido com sucesso, false caso contrário.
     */
    boolean remover(Long id);

    /**
     * Busca ItemMidia que contenham o termo de busca no título, autor/diretor, gênero ou sinopse.
     * @param termo O termo de busca.
     * @return Uma lista de ItemMidia que correspondem ao termo.
     */
    List<ItemMidia> buscarPorTermo(String termo);
}
