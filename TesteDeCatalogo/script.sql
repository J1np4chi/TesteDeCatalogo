-- Script SQL para criação da tabela item_midia
-- Este script é para referência e uso com a implementação ItemMidiaDAOJdbc.java
-- O projeto principal usa ItemMidiaDAOInMemory por padrão.

-- Criação do banco de dados (se necessário)
-- CREATE DATABASE IF NOT EXISTS catalogo_db;
-- USE catalogo_db;

-- Criação da tabela
CREATE TABLE IF NOT EXISTS item_midia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor_diretor VARCHAR(255) NOT NULL,
    ano_lancamento INT NOT NULL,
    genero VARCHAR(100),
    sinopse TEXT,
    tipo_midia VARCHAR(50) NOT NULL
);

-- Inserção de dados de exemplo (opcional)
/*
INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES
('O Senhor dos Anéis: A Sociedade do Anel', 'Peter Jackson', 2001, 'Fantasia', 'Um hobbit herda um anel e embarca em uma jornada épica.', 'Filme'),
('Game of Thrones', 'David Benioff, D.B. Weiss', 2011, 'Fantasia Medieval', 'Nove famílias nobres lutam pelo controle das terras de Westeros.', 'Série'),
('1984', 'George Orwell', 1949, 'Ficção Distópica', 'Em um futuro totalitário, um homem tenta se rebelar contra o Grande Irmão.', 'Livro');
*/
