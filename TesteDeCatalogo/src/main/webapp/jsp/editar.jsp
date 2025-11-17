<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Item: ${item.titulo}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        .menu a { margin-right: 15px; text-decoration: none; color: #0066cc; }
        .menu a:hover { text-decoration: underline; }
        .erro { color: red; font-weight: bold; margin-bottom: 10px; }
        form { max-width: 600px; margin-top: 20px; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input[type="text"], input[type="number"], textarea, select { width: 100%; padding: 8px; margin-top: 5px; box-sizing: border-box; border: 1px solid #ddd; border-radius: 4px; }
        textarea { resize: vertical; }
        button { background-color: #FFA500; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; margin-top: 15px; }
        button:hover { background-color: #cc8400; }
    </style>
</head>
<body>
    <h1>Editar Item de Mídia</h1>

    <div class="menu">
        <a href="./">Início</a>
        <a href="listar">Listar Itens</a>
    </div>

    <c:if test="${not empty erro}">
        <p class="erro">Erro: ${erro}</p>
    </c:if>

    <c:if test="${item != null}">
        <form action="editar" method="POST">
            <input type="hidden" name="id" value="${item.id}">

            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" value="${item.titulo}" required>

            <label for="autorDiretor">Autor/Diretor:</label>
            <input type="text" id="autorDiretor" name="autorDiretor" value="${item.autorDiretor}" required>

            <label for="anoLancamento">Ano de Lançamento:</label>
            <input type="number" id="anoLancamento" name="anoLancamento" value="${item.anoLancamento}" required min="1800" max="2100">

            <label for="genero">Gênero:</label>
            <input type="text" id="genero" name="genero" value="${item.genero}" required>

            <label for="tipoMidia">Tipo de Mídia:</label>
            <select id="tipoMidia" name="tipoMidia" required>
                <option value="Filme" <c:if test="${item.tipoMidia eq 'Filme'}">selected</c:if>>Filme</option>
                <option value="Série" <c:if test="${item.tipoMidia eq 'Série'}">selected</c:if>>Série</option>
                <option value="Livro" <c:if test="${item.tipoMidia eq 'Livro'}">selected</c:if>>Livro</option>
                <option value="Música" <c:if test="${item.tipoMidia eq 'Música'}">selected</c:if>>Música</option>
                <option value="Outro" <c:if test="${item.tipoMidia eq 'Outro'}">selected</c:if>>Outro</c:option>
            </select>

            <label for="sinopse">Sinopse:</label>
            <textarea id="sinopse" name="sinopse" rows="5">${item.sinopse}</textarea>

            <button type="submit">Salvar Alterações</button>
        </form>
    </c:if>
    <c:if test="${item == null}">
        <p class="erro">Item não encontrado para edição.</p>
    </c:if>
</body>
</html>
