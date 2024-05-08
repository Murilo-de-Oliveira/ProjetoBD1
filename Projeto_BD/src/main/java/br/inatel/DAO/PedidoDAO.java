package br.inatel.DAO;

import br.inatel.Model.PedidoHasRemedio;
import br.inatel.Model.Pedido;

import java.sql.SQLException;
public class PedidoDAO extends ConnectionDAO{
    boolean sucesso = false;

    //Inserir pedido no Banco de Dados
    public boolean insertPedido(Pedido pedido){

        connect();

        String sql = "INSERT INTO cliente (idPedido,data,clienteId, empregadoId) values (?,?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,pedido.getIdPedido());
            pst.setString(2,pedido.getData());
            pst.setInt(3,pedido.getClienteId());
            pst.setInt(4,pedido.getEmpregadoId());
            pst.execute();
            sucesso = true;
        } catch (SQLException ex){
            System.out.println("Erro de conexao  = " + ex.getMessage());
            sucesso = false;
        }finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexao " + e.getMessage());
            }
        }
        return sucesso;
    }

    //Buscar pedido no Banco de Dados
    public boolean selectPedidoId(int idPedido, int clienteId) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM pedido";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                Pedido pedidoTemp = new Pedido(
                        resultSet.getInt("idPedido"),
                        resultSet.getString("data"),
                        resultSet.getInt("clienteId"),
                        resultSet.getInt("empregadoId")
                );
                if (pedidoTemp.getIdPedido() == idPedido && pedidoTemp.getClienteId() == clienteId) {
                    verificado = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());

        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return verificado;
    }

    //Buscar pedidos de um cliente no Banco de Dados
    public boolean selectPedidosIds(int clienteId) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM pedido WHERE clienteId=?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, clienteId);
            resultSet = pst.executeQuery();

            System.out.println("Lista de Id's de pedidos do cliente: ");
            while (resultSet.next()) {
                Pedido pedidoTemp = new Pedido(
                        resultSet.getInt("idPedido"),
                        resultSet.getString("data"),
                        resultSet.getInt("clienteId"),
                        resultSet.getInt("empregadoId")
                );
                System.out.println("Id: " + pedidoTemp.getIdPedido());
            }
            verificado = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            verificado = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return verificado;
    }

    //Listar infos de um pedido no Banco de Dados
    public void selectInfosPedidos(int idPedido, int clienteId) {

        connect();

        boolean verificado;

        String sql = "SELECT * FROM pedido WHERE idPedido = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idPedido);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Pedido pedidoTemp = new Pedido(
                        resultSet.getInt("idPedido"),
                        resultSet.getString("data"),
                        resultSet.getInt("clienteId"),
                        resultSet.getInt("empregadoId")
                );
                if (pedidoTemp.getClienteId() == clienteId) {
                    System.out.println("Data do pedido: " + pedidoTemp.getData());
                    System.out.println("Id do cliente do pedido: " + pedidoTemp.getClienteId());
                    System.out.println("Id do empregado do pedido: " + pedidoTemp.getEmpregadoId());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
    }

    //Deletar pedido no Banco de Dados
    public boolean deletePedido(int idPedido, int clienteId) {

        connect();

        String sql = "DELETE FROM pedido WHERE idPedido=? AND clienteId=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idPedido);
            pst.setInt(2,clienteId);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
}
