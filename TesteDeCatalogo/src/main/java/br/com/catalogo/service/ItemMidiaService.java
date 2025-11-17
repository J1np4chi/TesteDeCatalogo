package br.com.catalogo.service;

import br.com.catalogo.dao.ItemMidiaDAO;
import br.com.catalogo.dao.ItemMidiaDAOInMemory;
import br.com.catalogo.model.ItemMidia;
import java.util.List;

public class ItemMidiaService {

    private final ItemMidiaDAO dao;

    /**
     * Construtor que inicializa o Service com ItemMidiaDAOInMemory por padrão,
     * conforme solicitado.
     */
    public ItemMidiaService() {
        this.dao = new ItemMidiaDAOInMemory();
    }

    /**
     * Construtor para injeção de dependência (útil para testes ou troca de implementação).
     * @param dao A implementação de ItemMidiaDAO a ser utilizada.
     */
    public ItemMidiaService(ItemMidiaDAO dao) {
        this.dao = dao;
    }

    // --- Métodos de Validação e Negócio ---

    private void validarItem(ItemMidia item) throws IllegalArgumentException {
        if (item.getTitulo() == null || item.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O título é obrigatório.");
        }
        if (item.getAutorDiretor() == null || item.getAutorDiretor().trim().isEmpty()) {
            throw new IllegalArgumentException("O autor/diretor é obrigatório.");
        }
        if (item.getAnoLancamento() == null || item.getAnoLancamento() <= 0) {
            throw new IllegalArgumentException("O ano de lançamento deve ser um valor válido.");
        }
        if (item.getTipoMidia() == null || item.getTipoMidia().trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo de mídia é obrigatório.");
        }
    }

    public ItemMidia salvar(ItemMidia item) throws IllegalArgumentException {
        validarItem(item);
        return dao.salvar(item);
    }

    public ItemMidia buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return dao.buscarPorId(id);
    }

    public List<ItemMidia> buscarTodos() {
        return dao.buscarTodos();
    }

    public boolean remover(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return dao.remover(id);
    }

    public List<ItemMidia> buscarPorTermo(String termo) {
        // A validação do termo é feita dentro do DAO, mas o Service pode adicionar lógica de negócio
        return dao.buscarPorTermo(termo);
    }
}
