-- ARQUIVO: script.sql --
-- Script SQL para criação do banco de dados e da tabela item_midia.

-- Cria o banco de dados se não existir e o seleciona
CREATE DATABASE IF NOT EXISTS catalogo_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE catalogo_db;

-- Cria a tabela item_midia
CREATE TABLE IF NOT EXISTS item_midia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor_diretor VARCHAR(255),
    ano_lancamento INT,
    genero VARCHAR(100),
    sinopse TEXT,
    tipo_midia VARCHAR(50) NOT NULL
);

-- Exemplo de inserção de dados (opcional)
INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES
('O Guia do Mochileiro das Galáxias', 'Douglas Adams', 1979, 'Ficção Científica', 'As aventuras de Arthur Dent após a destruição da Terra.', 'Livro'),
('A Origem', 'Christopher Nolan', 2010, 'Ação/Ficção Científica', 'Um ladrão que rouba segredos corporativos usando tecnologia de compartilhamento de sonhos.', 'Filme');

--
-- Função do arquivo:
-- Este script contém os comandos SQL necessários para criar o banco de dados `catalogo_db` e a tabela `item_midia` com a estrutura exata solicitada.
-- Ele deve ser executado no MySQL antes de rodar a aplicação com o DAO JDBC.
--
