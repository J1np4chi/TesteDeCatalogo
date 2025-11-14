<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Mídias</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        ul { list-style-type: none; padding: 0; }
        li { margin-bottom: 10px; }
        a { text-decoration: none; color: #007bff; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <h1>Bem-vindo ao TesteDeCatalogo</h1>
    <p>Selecione uma opção abaixo:</p>
    <ul>
        <li><a href="itens">Ver Catálogo de Mídias</a></li>
        <li><a href="cadastrar">Cadastrar Novo Item</a></li>
    </ul>
</body>
</html>
<!--
Função do arquivo:
Esta é a página inicial do projeto. Ela serve como um ponto de entrada simples, fornecendo links de navegação para a listagem de itens (/itens) e para o formulário de cadastro (/cadastrar).
A diretiva `contentType="text/html; charset=UTF-8"` garante a correta exibição de caracteres especiais.
-->
