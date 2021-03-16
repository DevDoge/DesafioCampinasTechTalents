package test.com.java.desafio.recursos;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConexaoTest {

    Connection cnt;

    @Test
    void openConnection() {
        try {
            String URL = "jdbc:mysql://localhost:3306/db_desafio";
            String USER = "root";
            String PASSWORD = "BitServer12@";
            cnt = DriverManager.getConnection(URL, USER, PASSWORD);

            assertFalse(cnt.isClosed());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void closeConnection() {
        try {
            openConnection();

            if (!cnt.isClosed()){
                cnt.close();
            }

            assertTrue(cnt.isClosed());

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}