package br.com.catalogo.servlet;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.service.ItemMidiaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditarItemServlet", urlPatterns = {"/editar"})
public class EditarItemServlet extends HttpServlet {

    private final ItemMidiaService service = new ItemMidiaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendRedirect("listar?erro=ID do item não fornecido para edição.");
            return;
        }

        try {
            Long id = Long.parseLong(idParam);
            ItemMidia item = service.buscarPorId(id);

            if (item != null) {
                request.setAttribute("item", item);
                request.getRequestDispatcher("/jsp/editar.jsp").forward(request, response);
            } else {
                response.sendRedirect("listar?erro=Item não encontrado para edição.");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("listar?erro=ID inválido para edição.");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("listar?erro=" + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        try {
            // 1. Coleta os dados do formulário
            Long id = Long.parseLong(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            String autorDiretor = request.getParameter("autorDiretor");
            Integer anoLancamento = Integer.parseInt(request.getParameter("anoLancamento"));
            String genero = request.getParameter("genero");
            String sinopse = request.getParameter("sinopse");
            String tipoMidia = request.getParameter("tipoMidia");

            // 2. Cria o objeto Model
            ItemMidia itemEditado = new ItemMidia(id, titulo, autorDiretor, anoLancamento, genero, sinopse, tipoMidia);

            // 3. Chama o Service para salvar (que fará o update)
            service.salvar(itemEditado);

            // 4. Redireciona para a listagem
            response.sendRedirect("listar?mensagem=Item atualizado com sucesso!");

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "O ID ou o ano de lançamento deve ser um número válido.");
            request.getRequestDispatcher("/jsp/editar.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/jsp/editar.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao atualizar o item: " + e.getMessage());
            request.getRequestDispatcher("/jsp/editar.jsp").forward(request, response);
        }
    }
}
