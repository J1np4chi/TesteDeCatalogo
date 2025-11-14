<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Novo Item</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        form div { margin-bottom: 15px; }
        label { display: block; font-weight: bold; }
        input[type="text"], input[type="number"], textarea, select { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
        .erro { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h1>Cadastrar Novo Item de Mídia</h1>

    <p><a href="itens">← Voltar para a Lista</a></p>

    <c:if test="${not empty erro}">
        <p class="erro"><c:out value="${erro}"/></p>
    </c:if>

    <form action="cadastrar" method="post">
        <div>
            <label for="titulo">Título (Obrigatório):</label>
            <input type="text" id="titulo" name="titulo" value="<c:out value="${item.titulo}"/>" required>
        </div>
        <div>
            <label for="autorDiretor">Autor/Diretor:</label>
            <input type="text" id="autorDiretor" name="autorDiretor" value="<c:out value="${item.autorDiretor}"/>">
        </div>
        <div>
            <label for="anoLancamento">Ano de Lançamento:</label>
            <input type="number" id="anoLancamento" name="anoLancamento" value="<c:out value="${item.anoLancamento}"/>" min="1800" max="2100">
        </div>
        <div>
            <label for="genero">Gênero:</label>
            <input type="text" id="genero" name="genero" value="<c:out value="${item.genero}"/>">
        </div>
        <div>
            <label for="tipoMidia">Tipo de Mídia:</label>
            <select id="tipoMidia" name="tipoMidia" required>
                <option value="">Selecione...</option>
                <option value="Livro" <c:if test="${item.tipoMidia eq 'Livro'}">selected</c:if>>Livro</option>
                <option value="Filme" <c:if test="${item.tipoMidia eq 'Filme'}">selected</c:if>>Filme</option>
                <option value="Série" <c:if test="${item.tipoMidia eq 'Série'}">selected</c:if>>Série</option>
            </select>
        </div>
        <div>
            <label for="sinopse">Sinopse:</label>
            <textarea id="sinopse" name="sinopse" rows="5"><c:out value="${item.sinopse}"/></textarea>
        </div>
        <div>
            <button type="submit">Cadastrar</button>
        </div>
    </form>
</body>
</html>
<!--
Função do arquivo:
Esta página JSP contém o formulário para cadastrar um novo item de mídia.
O formulário envia os dados via POST para o Servlet /cadastrar.
Ele usa JSTL/EL para pré-preencher os campos com os valores do objeto `item` (caso haja um erro de validação e o Servlet retorne para esta página).
O campo Título é marcado como obrigatório (`required`).
-->
