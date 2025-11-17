<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listagem de Itens</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        .menu a { margin-right: 15px; text-decoration: none; color: #0066cc; }
        .menu a:hover { text-decoration: underline; }
        .mensagem { color: green; font-weight: bold; margin-bottom: 10px; }
        .erro { color: red; font-weight: bold; margin-bottom: 10px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .acoes a { margin-right: 10px; }
    </style>
</head>
<body>
    <h1>Catálogo de Mídias</h1>

    <div class="menu">
        <a href="./">Início</a>
        <a href="cadastro">Cadastrar Novo Item</a>
    </div>

    <c:if test="${not empty param.mensagem}">
        <p class="mensagem">${param.mensagem}</p>
    </c:if>
    <c:if test="${not empty param.erro}">
        <p class="erro">${param.erro}</p>
    </c:if>

    <h2>Buscar Itens</h2>
    <form action="buscar" method="GET">
        <input type="text" name="termo" placeholder="Buscar por título, autor, etc." value="${termoBusca != null ? termoBusca : ''}">
        <button type="submit">Buscar</button>
        <c:if test="${not empty termoBusca}">
            <a href="listar">Limpar Busca</a>
        </c:if>
    </form>

    <h2>Itens Cadastrados</h2>
    <c:choose>
        <c:when test="${not empty itens}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Autor/Diretor</th>
                        <th>Ano</th>
                        <th>Tipo</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${itens}">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.titulo}</td>
                            <td>${item.autorDiretor}</td>
                            <td>${item.anoLancamento}</td>
                            <td>${item.tipoMidia}</td>
                            <td class="acoes">
                                <a href="detalhes?id=${item.id}">Detalhes</a>
                                <a href="editar?id=${item.id}">Editar</a>
                                <a href="remover?id=${item.id}" onclick="return confirm('Tem certeza que deseja remover ${item.titulo}?');">Remover</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Nenhum item encontrado.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
