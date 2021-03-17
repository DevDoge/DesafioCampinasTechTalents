# DesafioCampinasTechTalents
This is a course conclusion work for Campinas Tech Talents. Here it contains a system for presenting CRUD and JUnit 5 presentation, demonstrating the knowledge learned throughout the learning path.


Instruções para a criação do servidor e banco de dados:

Primeiro crie um servidor padrão, contendo o como porta host "3306" e o hostname como "localhost".
O nome de usuário deve ser "root" e a senha deve ser "BitServer12@" (sempre respeitando letras maiúsculas e minúsculas, tudo sem as aspas).

Em seguida adicione o script .sql da pasta "MySQL", ou copie o conteúdo a baixo para o Query tab, executando para criar o banco de dados e a tabela:

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

Depois de adicionar o MySQL acima e contruir o banco de dados, já pode adicionar o projeto em sua máquina e executar o programa da classe principal "Cadastro.java".

Abaixo está o código para abrir a conexão (já incluso na classe Conexao.java), por isso é importante o banco de dados e a tabela serem criados exatamente como nas instruções:

        try {
            String URL = "jdbc:mysql://localhost:3306/db_desafio";
            String USER = "root";
            String PASSWORD = "BitServer12@";
            cnt = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
            System.out.println(e.getMessage());
        }            
            
**Aviso importante: Na sua IDE o JDBC deve estar na versão 8.0.23 e o JUnit deve estar na versão 5.4.2 para o correto funcionamento do programa!**
