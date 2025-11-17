package br.com.catalogo.dao;

import br.com.catalogo.model.ItemMidia;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ItemMidiaDAOInMemory implements ItemMidiaDAO {

    private final Map<Long, ItemMidia> repositorio = new ConcurrentHashMap<>();
    private final AtomicLong contadorId = new AtomicLong(0);

    public ItemMidiaDAOInMemory() {
        // Dados iniciais
        salvar(new ItemMidia("O Senhor dos Anéis: A Sociedade do Anel", "Peter Jackson", 2001, "Fantasia", "Um hobbit herda um anel e embarca em uma jornada épica.", "Filme"));
        salvar(new ItemMidia("Game of Thrones", "David Benioff, D.B. Weiss", 2011, "Fantasia Medieval", "Nove famílias nobres lutam pelo controle das terras de Westeros.", "Série"));
        salvar(new ItemMidia("1984", "George Orwell", 1949, "Ficção Distópica", "Em um futuro totalitário, um homem tenta se rebelar contra o Grande Irmão.", "Livro"));
    }

    @Override
    public ItemMidia salvar(ItemMidia item) {
        if (item.getId() == null) {
            item.setId(contadorId.incrementAndGet());
            repositorio.put(item.getId(), item);
        } else {
            repositorio.put(item.getId(), item);
        }
        return item;
    }

    @Override
    public ItemMidia buscarPorId(Long id) {
        return repositorio.get(id);
    }

    @Override
    public List<ItemMidia> buscarTodos() {
        return new ArrayList<>(repositorio.values());
    }

    @Override
    public boolean remover(Long id) {
        return repositorio.remove(id) != null;
    }

    @Override
    public List<ItemMidia> buscarPorTermo(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return buscarTodos();
        }
        final String termoLowerCase = termo.toLowerCase();
        return repositorio.values().stream()
                .filter(item -> item.getTitulo().toLowerCase().contains(termoLowerCase) ||
                                item.getAutorDiretor().toLowerCase().contains(termoLowerCase) ||
                                item.getGenero().toLowerCase().contains(termoLowerCase) ||
                                item.getSinopse().toLowerCase().contains(termoLowerCase))
                .collect(Collectors.toList());
    }
}
