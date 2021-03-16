/*
Autor: Lucas Ribeiro de Pádua
*/
package com.java.desafio.principal;

import com.java.desafio.recursos.CrudDB;
import com.java.desafio.recursos.Menu;

import java.util.Scanner;

public class Cadastro {
    public static void main(String[] args) {

        //Objetos e variáveis necessárias
        Scanner sc = new Scanner(System.in);
        CrudDB crudDB = new CrudDB();
        Menu menu = new Menu();
        int escolhaMenu, id;
        String nome, e_mail, senha, escolhaRepetir;

        //Abertura da conexão com o banco de dados e frase de boas vindas
        crudDB.openConnection();
        System.out.println("***Bem vindo o sistema de gerenciamento de recursos humanos***\n");

        do {
            System.out.println(menu.getMenu());
            ;
            escolhaMenu = sc.nextInt();

            //Switch do menu
            switch (escolhaMenu) {

                //Opção Create
                case 1:
                    System.out.println("\nNovo cadastro");
                    System.out.print("Nome: ");
                    nome = sc.next();
                    System.out.print("E-mail: ");
                    e_mail = sc.next();
                    System.out.print("Senha: ");
                    senha = sc.next();
                    crudDB.create(nome, e_mail, senha);
                    break;

                //Opção Retrieve
                case 2:
                    System.out.println("\nMostrar cadastro");
                    System.out.print("Qual id? ");
                    id = sc.nextInt();
                    crudDB.retrieve(id);
                    break;

                //Opção Update
                case 3:
                    System.out.print("\nDeseja alterar dados de usuário, ou deseja alterar a senha? (U/S) ");
                    String escolhaUpdate = sc.next();

                    //Caso o usuário escolha alterar os dados da conta
                    if (escolhaUpdate.equalsIgnoreCase("u")) {
                        System.out.print("\nQual o seu id? ");
                        id = sc.nextInt();
                        System.out.print("Informe sua senha: ");
                        senha = sc.next();
                        System.out.print("\nDigite os novos dados" +
                                "\nNome: ");
                        nome = sc.next();
                        System.out.print("E-mail: ");
                        e_mail = sc.next();
                        crudDB.updateDados(id, nome, e_mail, senha);

                    }

                    //Caso o usuário escolha alterar a senha
                    else if (escolhaUpdate.equalsIgnoreCase("s")) {
                        System.out.print("Qual o seu id? ");
                        id = sc.nextInt();
                        System.out.print("Informe a senha antiga: ");
                        String senhaAntiga = sc.next();
                        System.out.print("Informe a nova senha: ");
                        senha = sc.next();
                        crudDB.updateSenha(id, senhaAntiga, senha);
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                //Opção Delete
                case 4:
                    System.out.print("\nQual id deseja excluir? ");
                    id = sc.nextInt();
                    System.out.print("Informe a senha para verificação: ");
                    senha = sc.next();
                    crudDB.delete(id, senha);
                    break;

                //Opção padrão
                default:
                    System.out.println("\nOpção inválida");
                    break;
            }

            //Escolha se deseja encerrar ou continuar o programa
            System.out.print("\nDeseja realizar outra operação? (S/N) ");
            escolhaRepetir = sc.next();

            //Laço de verificação da opção escolhida
            while (!escolhaRepetir.equalsIgnoreCase("s") && !escolhaRepetir.equalsIgnoreCase("n")) {
                System.out.println("\nOpção inválida!");
                System.out.print("\nDeseja realizar outra operação? (S/N) ");
                escolhaRepetir = sc.next();
            }

            //Enquanto a opção não for "n" o programa continuará
        } while (!escolhaRepetir.equalsIgnoreCase("n"));

        //Frase de encerramento e fechamento da conexão com o banco de dados
        System.out.println("\n\n***Obrigado por usar o sistema de gerenciamento de recursos humanos!***");
        crudDB.closeConnection();
    }
}