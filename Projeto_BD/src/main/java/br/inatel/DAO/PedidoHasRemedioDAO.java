package br.inatel.DAO;

import br.inatel.Model.Cliente;
import br.inatel.Model.PedidoHasRemedio;
import br.inatel.Model.Remedio;

import java.sql.SQLException;
public class PedidoHasRemedioDAO extends ConnectionDAO{
    boolean sucesso = false;

    //Inserir relação de pedido e remédio no Banco de Dados
    public boolean insertPedidoRemedio(PedidoHasRemedio pedidoHasRemedio){

        connect();

        String sql = "INSERT INTO pedidoHasremedio (idDoPedido,idPedido,idRemedio,preco,quantidadePedida) values (?,?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,pedidoHasRemedio.getIdPedido());
            pst.setInt(2,pedidoHasRemedio.getIdPedido());
            pst.setInt(3,pedidoHasRemedio.getIdRemedio());
            pst.setDouble(4,pedidoHasRemedio.getPreco());
            pst.setInt(5,pedidoHasRemedio.getQntPedido());
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

    public void searchPedidoRemedio(int pedidoId){

        connect();

        String sql = "SELECT * FROM pedidoHasRemedio WHERE clienteId = ?";

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                PedidoHasRemedio pedidoHasRemedio  = new PedidoHasRemedio(
                        resultSet.getInt("idDoPedido"),
                        resultSet.getInt("idPedido"),
                        resultSet.getInt("idRemedio"),
                        resultSet.getDouble("preco"),
                        resultSet.getInt("quantidadePedida")
                );
                if (pedidoHasRemedio.getIdPedido() == pedidoId) {
                    System.out.println("Id do PedidoHasRemedio: " + pedidoHasRemedio.getIdDoPedido());
                    System.out.println("Id do Pedido: " + pedidoHasRemedio.getIdPedido());
                    System.out.println("Id do Remédio: " + pedidoHasRemedio.getIdRemedio());
                    System.out.println("Preço: " + pedidoHasRemedio.getPreco());
                    System.out.println("Quantidade do remédio: " + pedidoHasRemedio.getQntPedido());
                }
            }
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
    }
}
