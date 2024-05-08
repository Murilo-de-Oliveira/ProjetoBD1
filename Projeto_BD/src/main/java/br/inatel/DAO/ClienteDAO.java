package br.inatel.DAO;

import br.inatel.Model.Cliente;
import java.sql.SQLException;

public class ClienteDAO extends ConnectionDAO{
    boolean sucesso = false;

    //Inserir cliente no Banco de Dados
    public boolean insertCliente(Cliente cliente){

        connect();

        String sql = "INSERT INTO cliente (id,nome,telefone,endereco,data) values (?,?,?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,cliente.getId());
            pst.setString(2,cliente.getNome());
            pst.setString(3,cliente.getTelefone());
            pst.setString(4,cliente.getEndereco());
            pst.setString(5,cliente.getData());
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

    //Buscar cliente no Banco de Dados
    public boolean selectClienteId(int clienteId) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM cliente";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                Cliente clienteTemp = new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("data")
                );
                if (clienteTemp.getId() == clienteId) {
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

    //Listar infos de um cliente no Banco de Dados
    public void selectInfosCliente(int clienteId) {

        connect();

        boolean verificado;

        String sql = "SELECT * FROM cliente WHERE id = ?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, clienteId);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Cliente clienteTemp = new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("data")
                );
                if (clienteTemp.getId() == clienteId) {
                    System.out.println("Id do Cliente: " + clienteTemp.getId());
                    System.out.println("Nome do Cliente: " + clienteTemp.getNome());
                    System.out.println("Telefone do Cliente: " + clienteTemp.getTelefone());
                    System.out.println("Endereço do Cliente: " + clienteTemp.getEndereco());
                    System.out.println("Data de inserção do Cliente: " + clienteTemp.getData());
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

    //Deletar cliente no Banco de Dados
    public boolean deleteCliente(int clienteId) {

        connect();

        String sql = "DELETE FROM cliente WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, clienteId);
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
