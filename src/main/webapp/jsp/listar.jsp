<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Listagem de Itens</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .acoes a { margin-right: 10px; }
        .busca { margin-bottom: 20px; }
        .erro { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h1>Catálogo de Mídias</h1>

    <p><a href="../index.jsp">← Voltar para o Início</a> | <a href="cadastrar">Novo Item</a></p>

    <div class="busca">
        <form action="itens" method="get">
            <input type="text" name="q" placeholder="Buscar por Título ou Sinopse" value="<c:out value="${termoBusca}"/>">
            <button type="submit">Buscar</button>
            <c:if test="${not empty termoBusca}">
                <a href="itens">Limpar Busca</a>
            </c:if>
        </form>
    </div>

    <c:if test="${not empty erro}">
        <p class="erro"><c:out value="${erro}"/></p>
    </c:if>

    <c:choose>
        <c:when test="${empty itens}">
            <p>Nenhum item de mídia encontrado.</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Autor/Diretor</th>
                        <th>Ano</th>
                        <th>Gênero</th>
                        <th>Tipo</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${itens}">
                        <tr>
                            <td><c:out value="${item.id}"/></td>
                            <td><c:out value="${item.titulo}"/></td>
                            <td><c:out value="${item.autorDiretor}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.anoLancamento != null}">
                                        <c:out value="${item.anoLancamento}"/>
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><c:out value="${item.genero}"/></td>
                            <td><c:out value="${item.tipoMidia}"/></td>
                            <td class="acoes">
                                <a href="detalhes?id=<c:out value="${item.id}"/>">Detalhes</a> |
                                <a href="editar?id=<c:out value="${item.id}"/>">Editar</a> |
                                <a href="excluir?id=<c:out value="${item.id}"/>" onclick="return confirm('Tem certeza que deseja excluir este item?');">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
<!--
Função do arquivo:
Esta página JSP exibe a lista de itens de mídia em uma tabela.
Ela usa a taglib JSTL (`<c:forEach>`) para iterar sobre a lista de itens (`${itens}`) passada pelo Servlet.
O uso de `<c:out>` em todos os valores garante a proteção contra ataques de Cross-Site Scripting (XSS).
Inclui um formulário de busca e links para as ações de Detalhes, Editar e Excluir.
-->
