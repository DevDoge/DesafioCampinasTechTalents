package test.com.java.desafio.recursos;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CrudDBTest extends ConexaoTest {

    private final String SQL_QUERY = "SELECT * FROM tabela_desafio WHERE id=";
    Date date = new Date(Calendar.getInstance().getTimeInMillis());
    private PreparedStatement prst;
    private Statement statement;
    private ResultSet resultSet;

    boolean create(String nome, String e_mail, String senha) {
        try {

            if (!cnt.isClosed()) {
                String SQL_INSERT = "INSERT INTO tabela_desafio (nome, e_mail, senha, data_cadastro)" +
                        "VALUES (?,?,?,?)";
                prst = cnt.prepareStatement(SQL_INSERT);
                prst.setString(1, nome);
                prst.setString(2, e_mail);
                prst.setString(3, senha);
                prst.setString(4, String.valueOf(date));

                prst.executeUpdate();

                System.out.println("Usuário cadastrado com sucesso!");

                statement = cnt.createStatement();

                statement.executeQuery("SELECT * FROM tabela_desafio " +
                        "WHERE (nome='" + nome + "' AND e_mail='" + e_mail + "' AND senha='" + senha + "')");

                resultSet = statement.getResultSet();
                if (resultSet.next()) {

                    System.out.println("Seu ID de usuário é: " +
                            resultSet.getString("id"));
                    return true;
                }
            } else {
                System.out.println("Sem conexão");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Test
    void createTest() {
        openConnection();
        assertTrue(create("nomeTest", "e_mailTest", "senhaTest"));
        closeConnection();
    }

    boolean updateDados(int id, String nome, String e_mail, String senha) {
        String SQL_UPDATE = "UPDATE tabela_desafio SET nome=?, e_mail=?" +
                "WHERE id=";
        try {
            if (!cnt.isClosed()) {
                prst = cnt.prepareStatement(SQL_UPDATE + id,
                        Statement.RETURN_GENERATED_KEYS);

                statement = cnt.createStatement();
                statement.executeQuery(SQL_QUERY + id);
                resultSet = statement.getResultSet();

                if (resultSet.next()){
                    if (senha.equals(resultSet.getString("senha"))){
                        prst.setString(1, nome);
                        prst.setString(2, e_mail);
                        prst.executeUpdate();
                        System.out.println("Dados atualizados com sucesso!");
                        return true;

                    }else {
                        System.out.println("Senha ou ID incorretos");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Test
    void updateDadosTest() {
        openConnection();
        assertTrue(updateDados(35, "nome", "E-mail", "senhaTest"));
        closeConnection();
    }


    boolean updateSenha(int id, String senhaAntiga, String senhaNova) {
        try {
            if (!cnt.isClosed()) {
                prst = cnt.prepareStatement("UPDATE tabela_desafio SET senha=?" +
                                "WHERE id=" + id,
                        Statement.RETURN_GENERATED_KEYS);
                statement = cnt.createStatement();
                statement.executeQuery(SQL_QUERY + id);
                resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    if (senhaAntiga.equals(resultSet.getString("senha"))) {

                        prst.setString(1, senhaNova);
                        prst.executeUpdate();
                        System.out.println("Senha atualizada com sucesso!");
                        return true;
                    } else {
                        System.out.println("A senha antiga está incorreta");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Test
    void updadeSenhaTest() {
        openConnection();
        assertTrue(updateSenha(35, "senhaTest", "senhaTest"));
        closeConnection();
    }


    boolean retrieve(int id) {
        try {
            statement = cnt.createStatement();
            statement.executeQuery(SQL_QUERY + id);
            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("ID: " + resultSet.getInt("id") + "\nNome: " +
                        resultSet.getString("nome") + "\nE-mail: " +
                        resultSet.getString("e_mail") + "\nData de Cadastro: " +
                        sdf.format(resultSet.getDate("data_cadastro")));
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Test
    void retrieveTest() {
        openConnection();
        assertTrue(retrieve(35));
        closeConnection();
    }


    boolean delete(int id, String senha) {
        try {
            String SQL_DELETE = "DELETE FROM tabela_desafio WHERE id=";

            statement = cnt.createStatement();
            statement.executeQuery(SQL_QUERY + id);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                if (senha.equals(resultSet.getString("senha"))) {
                    prst = cnt.prepareStatement(SQL_DELETE + id);
                    prst.executeUpdate();
                    System.out.println("Usuário deletado com sucesso!");
                    return true;
                } else {
                    System.out.println("A senha está incorreta");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Test
    void deleteTest(){
        openConnection();
        assertTrue(delete(35, "senha"));
        closeConnection();
    }
}