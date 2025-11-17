<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalhes do Item: ${item.titulo}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        .menu a { margin-right: 15px; text-decoration: none; color: #0066cc; }
        .menu a:hover { text-decoration: underline; }
        .detalhes { margin-top: 20px; border: 1px solid #ccc; padding: 15px; border-radius: 5px; max-width: 600px; }
        .detalhes p { margin: 10px 0; }
        .detalhes strong { display: inline-block; width: 120px; }
        .acoes { margin-top: 15px; }
        .acoes a { margin-right: 10px; padding: 8px 12px; text-decoration: none; border-radius: 4px; }
        .acoes .editar { background-color: #FFA500; color: white; }
        .acoes .remover { background-color: #f44336; color: white; }
    </style>
</head>
<body>
    <h1>Detalhes do Item de Mídia</h1>

    <div class="menu">
        <a href="./">Início</a>
        <a href="listar">Listar Itens</a>
    </div>

    <c:if test="${item != null}">
        <div class="detalhes">
            <h2>${item.titulo}</h2>
            <p><strong>ID:</strong> ${item.id}</p>
            <p><strong>Tipo:</strong> ${item.tipoMidia}</p>
            <p><strong>Autor/Diretor:</strong> ${item.autorDiretor}</p>
            <p><strong>Ano de Lançamento:</strong> ${item.anoLancamento}</p>
            <p><strong>Gênero:</strong> ${item.genero}</p>
            <p><strong>Sinopse:</strong> ${item.sinopse}</p>
        </div>

        <div class="acoes">
            <a href="editar?id=${item.id}" class="editar">Editar</a>
            <a href="remover?id=${item.id}" class="remover" onclick="return confirm('Tem certeza que deseja remover ${item.titulo}?');">Remover</a>
        </div>
    </c:if>
    <c:if test="${item == null}">
        <p>Item não encontrado.</p>
    </c:if>
</body>
</html>
