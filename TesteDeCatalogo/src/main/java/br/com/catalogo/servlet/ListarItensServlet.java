package br.com.catalogo.servlet;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.service.ItemMidiaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarItensServlet", urlPatterns = {"/listar", "/buscar"})
public class ListarItensServlet extends HttpServlet {

    private final ItemMidiaService service = new ItemMidiaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String termoBusca = request.getParameter("termo");
        List<ItemMidia> itens;

        if (termoBusca != null && !termoBusca.trim().isEmpty()) {
            itens = service.buscarPorTermo(termoBusca);
            request.setAttribute("termoBusca", termoBusca);
        } else {
            itens = service.buscarTodos();
        }

        request.setAttribute("itens", itens);
        request.getRequestDispatcher("/jsp/listar.jsp").forward(request, response);
    }
}
