package br.com.catalogo.servlet;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.service.ItemMidiaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetalhesItemServlet", urlPatterns = {"/detalhes"})
public class DetalhesItemServlet extends HttpServlet {

    private final ItemMidiaService service = new ItemMidiaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendRedirect("listar?erro=ID do item não fornecido.");
            return;
        }

        try {
            Long id = Long.parseLong(idParam);
            ItemMidia item = service.buscarPorId(id);

            if (item != null) {
                request.setAttribute("item", item);
                request.getRequestDispatcher("/jsp/detalhes.jsp").forward(request, response);
            } else {
                response.sendRedirect("listar?erro=Item não encontrado.");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("listar?erro=ID inválido.");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("listar?erro=" + e.getMessage());
        }
    }
}
