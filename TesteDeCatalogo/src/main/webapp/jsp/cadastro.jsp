<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Novo Item</title>
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
        button { background-color: #4CAF50; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; margin-top: 15px; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <h1>Cadastrar Novo Item de Mídia</h1>

    <div class="menu">
        <a href="./">Início</a>
        <a href="listar">Listar Itens</a>
    </div>

    <c:if test="${not empty erro}">
        <p class="erro">Erro: ${erro}</p>
    </c:if>

    <form action="cadastro" method="POST">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required>

        <label for="autorDiretor">Autor/Diretor:</label>
        <input type="text" id="autorDiretor" name="autorDiretor" required>

        <label for="anoLancamento">Ano de Lançamento:</label>
        <input type="number" id="anoLancamento" name="anoLancamento" required min="1800" max="2100">

        <label for="genero">Gênero:</label>
        <input type="text" id="genero" name="genero" required>

        <label for="tipoMidia">Tipo de Mídia:</label>
        <select id="tipoMidia" name="tipoMidia" required>
            <option value="">Selecione</option>
            <option value="Filme">Filme</option>
            <option value="Série">Série</option>
            <option value="Livro">Livro</option>
            <option value="Música">Música</option>
            <option value="Outro">Outro</option>
        </select>

        <label for="sinopse">Sinopse:</label>
        <textarea id="sinopse" name="sinopse" rows="5"></textarea>

        <button type="submit">Cadastrar Item</button>
    </form>
</body>
</html>
