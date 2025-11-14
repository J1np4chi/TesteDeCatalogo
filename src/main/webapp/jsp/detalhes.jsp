<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Detalhes: <c:out value="${item.titulo}"/></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        .detalhe-item { border: 1px solid #ccc; padding: 15px; border-radius: 5px; max-width: 600px; }
        .detalhe-item p { margin: 10px 0; }
        .detalhe-item strong { display: inline-block; width: 150px; }
    </style>
</head>
<body>
    <h1>Detalhes do Item: <c:out value="${item.titulo}"/></h1>

    <p><a href="itens">← Voltar para a Lista</a> | <a href="editar?id=<c:out value="${item.id}"/>">Editar Item</a></p>

    <div class="detalhe-item">
        <p><strong>ID:</strong> <c:out value="${item.id}"/></p>
        <p><strong>Título:</strong> <c:out value="${item.titulo}"/></p>
        <p><strong>Autor/Diretor:</strong> <c:out value="${item.autorDiretor}"/></p>
        <p><strong>Ano de Lançamento:</strong>
            <c:choose>
                <c:when test="${item.anoLancamento != null}">
                    <c:out value="${item.anoLancamento}"/>
                </c:when>
                <c:otherwise>
                    Não informado
                </c:otherwise>
            </c:choose>
        </p>
        <p><strong>Gênero:</strong> <c:out value="${item.genero}"/></p>
        <p><strong>Tipo de Mídia:</strong> <c:out value="${item.tipoMidia}"/></p>
        <p><strong>Sinopse:</strong></p>
        <p style="white-space: pre-wrap;"><c:out value="${item.sinopse}"/></p>
    </div>
</body>
</html>
<!--
Função do arquivo:
Esta página JSP exibe todos os detalhes de um item de mídia específico.
Ela recebe o objeto `item` do Servlet e usa `<c:out>` para exibir cada campo de forma segura, prevenindo XSS.
Inclui um tratamento para exibir "Não informado" caso o campo `anoLancamento` seja nulo.
-->
