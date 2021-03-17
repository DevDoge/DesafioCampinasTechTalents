/****novo banco de nados e nova tabela****/
CREATE DATABASE db_desafio;
USE db_desafio;
CREATE TABLE tabela_desafio(
id INT AUTO_INCREMENT,
nome VARCHAR(70) NOT NULL,
e_mail VARCHAR(70) NOT NULL,
senha VARCHAR(70) NOT NULL,
data_cadastro DATE,
PRIMARY KEY (id)
);

USE db_desafio;
SELECT * FROM tabela_desafio;