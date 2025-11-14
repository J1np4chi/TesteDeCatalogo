package com.jin.catalogo.dao;

import com.jin.catalogo.model.ItemMidia;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Implementação do DAO de ItemMidia que armazena os dados em memória (ArrayList).
 * Útil para testes rápidos sem a necessidade de um banco de dados.
 */
public class ItemMidiaDAOInMemory implements ItemMidiaDAO {

    private static final List<ItemMidia> REPOSITORIO = new ArrayList<>();
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    // Bloco estático para popular alguns dados iniciais
    static {
        ItemMidia item1 = new ItemMidia("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, "Fantasia", "Uma jornada épica para destruir um anel maligno.", "Livro");
        item1.setId(ID_GENERATOR.incrementAndGet());
        REPOSITORIO.add(item1);

        ItemMidia item2 = new ItemMidia("Interestelar", "Christopher Nolan", 2014, "Ficção Científica", "Um grupo de exploradores viaja através de um buraco de minhoca.", "Filme");
        item2.setId(ID_GENERATOR.incrementAndGet());
        REPOSITORIO.add(item2);
    }

    /**
     * Insere um novo item de mídia, atribuindo um ID.
     */
    @Override
    public void inserir(ItemMidia item) {
        item.setId(ID_GENERATOR.incrementAndGet());
        REPOSITORIO.add(item);
    }

    /**
     * Lista todos os itens de mídia.
     */
    @Override
    public List<ItemMidia> listarTodos() {
        return new ArrayList<>(REPOSITORIO); // Retorna uma cópia para evitar modificações externas
    }

    /**
     * Busca um item de mídia pelo seu ID.
     */
    @Override
    public ItemMidia buscarPorId(int id) {
        return REPOSITORIO.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Atualiza um item de mídia existente.
     */
    @Override
    public void atualizar(ItemMidia item) {
        for (int i = 0; i < REPOSITORIO.size(); i++) {
            if (REPOSITORIO.get(i).getId().equals(item.getId())) {
                REPOSITORIO.set(i, item);
                return;
            }
        }
    }

    /**
     * Exclui um item de mídia pelo seu ID.
     */
    @Override
    public void excluir(int id) {
        REPOSITORIO.removeIf(item -> item.getId() == id);
    }

    /**
     * Busca itens de mídia cujo título ou sinopse contenham o termo de busca (case-insensitive).
     */
    @Override
    public List<ItemMidia> buscarPorTermo(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return listarTodos();
        }
        final String termoLower = termo.toLowerCase();
        return REPOSITORIO.stream()
                .filter(item -> item.getTitulo().toLowerCase().contains(termoLower) ||
                                item.getSinopse().toLowerCase().contains(termoLower))
                .collect(Collectors.toList());
    }
}
