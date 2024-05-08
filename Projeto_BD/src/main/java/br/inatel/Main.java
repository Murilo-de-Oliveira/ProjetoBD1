package br.inatel;

import br.inatel.Model.*;
import br.inatel.DAO.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Estanciando variáveis de entrada e controle
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        boolean flag_cliente;
        boolean flag_remedio;
        boolean flag_pedido;
        int op;

        //Instanciando as classes de BD utilizadas
        ClienteDAO clienteDAO = new ClienteDAO();
        EmpregadoDAO empregadoDAO = new EmpregadoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        RemedioDAO remedioDAO = new RemedioDAO();
        PedidoHasRemedioDAO pedidoHasRemedioDAO = new PedidoHasRemedioDAO();

        //Informações do cliente
        int idCliente;
        String nome;
        String telefone;
        String endereco;
        //String dataCliente;

        //Informações do pedido
        int idPedido;
        int clienteId;
        int empregadoId;
        int remedioId;
        int qntPedido;
        //String dataPedido;

        //Informações do remédio
        int idRemedio;
        String nomeRemedio;
        String fabricante;
        String tipoRemedio;
        int estoque;
        String validade;
        double preco;

        //Instanciando a classe de adquirir a data
        Data data = new Data();
        /*
        Empregado empregado1 = new Empregado(
                3,
                "Pedro",
                "Silva",
                "Gerente",
                5000,
                "12341234"
        );
        if(empregadoDAO.insertEmpregado(empregado1)){
            System.out.println("Empregado adicionado com sucesso\n");
        }
        empregadoDAO.selectInfosEmpregado(3);
        if(empregadoDAO.deleteEmpregado(3)){
            System.out.println("\nEmpregado removido com sucesso\n");
        }
        */
        empregadoDAO.selectInfosEmpregado(3);
        while(flag){
            System.out.println("Bem vindo ao sistema da Farmácia");
            System.out.println("Selecione uma das opções abaixo: ");
            System.out.println("1 - Gerenciamento de Clientes");
            System.out.println("2 - Gerenciamento de Remédios");
            System.out.println("3 - Gerenciamento de Pedidos");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");
            op = sc.nextInt();
            //sc.nextLine();

            switch (op){
                case 1 -> {
                    flag_cliente = true;
                    while (flag_cliente){
                        System.out.println("Grenciamento de Clientes");
                        System.out.println("Selecione uma das opções abaixo: ");
                        System.out.println("1 - Adicionar Cliente");
                        System.out.println("2 - Ver informações de um cliente");
                        System.out.println("3 - Deletar um cliente");
                        System.out.println("4 - Voltar");
                        System.out.print("Opção: ");
                        op = sc.nextInt();
                        sc.nextLine();
                        switch (op){
                            case 1 -> {
                                System.out.println("Cadastro de Cliente");
                                System.out.println("Entre com as informações do cliente: ");
                                System.out.println("Id: ");
                                idCliente = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Nome: ");
                                nome = sc.nextLine();
                                System.out.println("Telefone: ");
                                telefone = sc.nextLine();
                                System.out.println("Endereço: ");
                                endereco = sc.nextLine();

                                Cliente cliente = new Cliente(
                                        idCliente,
                                        nome,
                                        telefone,
                                        endereco,
                                        data.getDateTime()
                                );

                                clienteDAO.insertCliente(cliente);
                            }
                            case 2 -> {
                                System.out.println("Ver infos de um Cliente");
                                System.out.print("Entre com o id do cliente: ");
                                int idTemp = sc.nextInt();
                                if(clienteDAO.selectClienteId(idTemp)){
                                    clienteDAO.selectInfosCliente(idTemp);
                                }
                            }
                            case 3 -> {
                                System.out.println("Deletar um Cliente");
                                System.out.print("Entre com o id do cliente: ");
                                int idTemp = sc.nextInt();
                                if(clienteDAO.selectClienteId(idTemp)){
                                    clienteDAO.deleteCliente(idTemp);
                                }
                            }
                            case 4 -> flag_cliente = false;
                            default -> System.out.println("Valor inválido");
                        }
                    }
                }

                case 2 -> {
                    flag_remedio = true;
                    while(flag_remedio){
                        System.out.println("Grenciamento de Remedios");
                        System.out.println("Selecione uma das opções abaixo: ");
                        System.out.println("1 - Adicionar Remédio");
                        System.out.println("2 - Ver informações de um Remédio");
                        System.out.println("4 - Deletar um Remédio");
                        System.out.println("5 - Voltar");
                        System.out.print("Opção: ");
                        op = sc.nextInt();
                        sc.nextLine();
                        switch (op){
                            case 1 -> {
                                System.out.println("Cadastro de Remédio");
                                System.out.println("Entre com as informações do remédio: ");
                                System.out.println("Id: ");
                                idRemedio = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Nome: ");
                                nomeRemedio = sc.nextLine();
                                System.out.println("Fabricante: ");
                                fabricante = sc.nextLine();
                                System.out.println("Tipo do remédio: ");
                                tipoRemedio = sc.nextLine();
                                System.out.println("Validade: ");
                                validade = sc.nextLine();
                                System.out.println("Estoque: ");
                                estoque = sc.nextInt();
                                System.out.println("Preço: ");
                                preco = sc.nextDouble();

                                Remedio remedio = new Remedio(
                                        idRemedio,
                                      nomeRemedio,
                                      fabricante,
                                      tipoRemedio,
                                      estoque,
                                      validade,
                                      preco
                                );

                                remedioDAO.insertRemedio(remedio);
                            }
                            case 2 -> {
                                System.out.println("Ver infos de um Remédio");
                                System.out.print("Entre com o id do remédio: ");
                                int idTemp = sc.nextInt();
                                if(remedioDAO.selectRemedioId(idTemp)){
                                    remedioDAO.selectInfosRemedio(idTemp);
                                }
                            }
                            case 3 -> {
                                System.out.println("Deletar um Remédio");
                                System.out.print("Entre com o id do remédio: ");
                                int idTemp = sc.nextInt();
                                if(remedioDAO.selectRemedioId(idTemp)){
                                    remedioDAO.deleteRemedio(idTemp);
                                }
                            }
                            case 4 -> flag_remedio = false;
                            default -> System.out.println("Valor inválido");
                        }
                    }
                }

                case 3 -> {
                    flag_pedido = true;
                    while (flag_pedido){
                        System.out.println("Grenciamento de Pedidos");
                        System.out.println("Selecione uma das opções abaixo: ");
                        System.out.println("1 - Adicionar Pedido");
                        System.out.println("2 - Ver informações de um pedido");
                        System.out.println("3 - Adicionar um remédio ao pedido");
                        System.out.println("4 - Deletar um pedido");
                        System.out.println("5 - Voltar");
                        System.out.print("Opção: ");
                        op = sc.nextInt();
                        sc.nextLine();
                        switch (op){
                            case 1 -> {
                                System.out.println("Cadastro de Pedido");
                                System.out.println("Entre com as informações do pedido: ");
                                System.out.println("Id do pedido: ");
                                idPedido = sc.nextInt();
                                do{
                                    System.out.println("Id do cliente: ");
                                    clienteId = sc.nextInt();
                                    if(!clienteDAO.selectClienteId(clienteId)){
                                        System.out.println("Cliente não cadastrado");
                                    }
                                }while(!clienteDAO.selectClienteId(clienteId));
                                do{
                                    System.out.println("Id do Empregado: ");
                                    empregadoId = sc.nextInt();
                                    if(!empregadoDAO.selectEmpregadoId(empregadoId)){
                                        System.out.println("Empregado não cadastrado");
                                    }
                                }while(!empregadoDAO.selectEmpregadoId(empregadoId));
                                do{
                                    System.out.println("Id do Remedio: ");
                                    remedioId = sc.nextInt();
                                    if(!remedioDAO.selectRemedioId(remedioId)){
                                        System.out.println("Remedio não cadastrado");
                                    }
                                }while(!remedioDAO.selectRemedioId(remedioId));
                                System.out.println("Quantidade do remédio");
                                qntPedido = sc.nextInt();

                                Pedido pedido = new Pedido(
                                        idPedido,
                                        data.getDateTime(),
                                        clienteId,
                                        empregadoId
                                );

                                PedidoHasRemedio pedidoHasRemedio = new PedidoHasRemedio(
                                        idPedido,
                                        idPedido,
                                        remedioId,
                                        2, //inserir um método ou algo assim pro preço
                                        qntPedido
                                );

                                pedidoDAO.insertPedido(pedido);
                                pedidoHasRemedioDAO.insertPedidoRemedio(pedidoHasRemedio);
                            }
                            case 2 -> {
                                System.out.println("Ver infos de um Pedido");
                                System.out.print("Entre com o id do pedido: ");
                                int idPedidoTemp = sc.nextInt();
                                System.out.print("Entre com o id do cliente: ");
                                int idClienteTemp = sc.nextInt();
                                if(pedidoDAO.selectPedidoId(idPedidoTemp, idClienteTemp)){
                                    pedidoDAO.selectInfosPedidos(idPedidoTemp, idClienteTemp);
                                    pedidoHasRemedioDAO.searchPedidoRemedio(idPedidoTemp);
                                }
                            }

                            case 3 -> {
                                System.out.println("Adicionar remédios a um Pedido");
                                System.out.print("Entre com o id: ");
                                int idDoPedidoTemp = sc.nextInt();
                                System.out.print("Entre com o id do pedido: ");
                                int idPedidoTemp = sc.nextInt();
                                System.out.print("Entre com o id do cliente: ");
                                int idClienteTemp = sc.nextInt();
                                System.out.print("Entre com o id do remedio: ");
                                int idRemedioTemp = sc.nextInt();
                                System.out.println("Quantidade do remédio");
                                qntPedido = sc.nextInt();
                                if(pedidoDAO.selectPedidoId(idPedidoTemp, idClienteTemp)){
                                    PedidoHasRemedio pedidoHasRemedio = new PedidoHasRemedio(
                                            idDoPedidoTemp,
                                            idPedidoTemp,
                                            idRemedioTemp,
                                            2, //inserir um método ou algo assim pro preço
                                            qntPedido
                                    );
                                    pedidoHasRemedioDAO.insertPedidoRemedio(pedidoHasRemedio);
                                }
                            }

                            case 4 -> {
                                System.out.println("Deletar um Pedido");
                                System.out.print("Entre com o id do pedido: ");
                                int idPedidoTemp = sc.nextInt();
                                System.out.print("Entre com o id do cliente: ");
                                int idClienteTemp = sc.nextInt();
                                if(pedidoDAO.deletePedido(idPedidoTemp, idClienteTemp)){
                                    pedidoDAO.deletePedido(idPedidoTemp, idClienteTemp);
                                }
                            }
                            case 5 -> flag_pedido = false;
                            default -> System.out.println("Valor inválido");
                        }
                    }
                }
                case 4 -> flag = false;
                case 5 -> System.out.println("Opção inválida");
            }
        }
        sc.close();
    }
}