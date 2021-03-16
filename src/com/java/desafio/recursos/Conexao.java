/*
Autor: Lucas Ribeiro de Pádua
*/
package com.java.desafio.recursos;

import java.sql.*;

public class Conexao {

    Connection cnt;

    public void openConnection() {
        try {
            String URL = "jdbc:mysql://localhost:3306/db_desafio";
            String USER = "root";
            String PASSWORD = "BitServer12@";
            cnt = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection (){
        try {
            cnt.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
