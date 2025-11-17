package br.com.catalogo.servlet;

import br.com.catalogo.service.ItemMidiaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoverItemServlet", urlPatterns = {"/remover"})
public class RemoverItemServlet extends HttpServlet {

    private final ItemMidiaService service = new ItemMidiaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendRedirect("listar?erro=ID do item não fornecido para remoção.");
            return;
        }

        try {
            Long id = Long.parseLong(idParam);
            boolean removido = service.remover(id);

            if (removido) {
                response.sendRedirect("listar?mensagem=Item removido com sucesso!");
            } else {
                response.sendRedirect("listar?erro=Item não encontrado ou não pôde ser removido.");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("listar?erro=ID inválido para remoção.");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("listar?erro=" + e.getMessage());
        }
    }
}
