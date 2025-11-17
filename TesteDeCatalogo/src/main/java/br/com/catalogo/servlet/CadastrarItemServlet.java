package br.com.catalogo.servlet;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.service.ItemMidiaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CadastrarItemServlet", urlPatterns = {"/cadastro"})
public class CadastrarItemServlet extends HttpServlet {

    private final ItemMidiaService service = new ItemMidiaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Encaminha para a página de cadastro
        request.getRequestDispatcher("/jsp/cadastro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Configura o encoding para evitar problemas com caracteres especiais
        request.setCharacterEncoding("UTF-8");

        try {
            // 1. Coleta os dados do formulário
            String titulo = request.getParameter("titulo");
            String autorDiretor = request.getParameter("autorDiretor");
            Integer anoLancamento = Integer.parseInt(request.getParameter("anoLancamento"));
            String genero = request.getParameter("genero");
            String sinopse = request.getParameter("sinopse");
            String tipoMidia = request.getParameter("tipoMidia");

            // 2. Cria o objeto Model
            ItemMidia novoItem = new ItemMidia(titulo, autorDiretor, anoLancamento, genero, sinopse, tipoMidia);

            // 3. Chama o Service para salvar
            service.salvar(novoItem);

            // 4. Redireciona para a listagem
            response.sendRedirect("listar?mensagem=Item cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "O ano de lançamento deve ser um número válido.");
            request.getRequestDispatcher("/jsp/cadastro.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("/jsp/cadastro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao cadastrar o item: " + e.getMessage());
            request.getRequestDispatcher("/jsp/cadastro.jsp").forward(request, response);
        }
    }
}
