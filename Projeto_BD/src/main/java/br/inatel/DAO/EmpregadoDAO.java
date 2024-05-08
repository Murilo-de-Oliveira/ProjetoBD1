package br.inatel.DAO;

import br.inatel.Model.Cliente;
import br.inatel.Model.Empregado;

import java.sql.SQLException;
public class EmpregadoDAO extends ConnectionDAO{
    boolean sucesso = false;

    //Inserir empregado no Banco de Dados
    public boolean insertEmpregado(Empregado empregado){

        connect();

        String sql = "INSERT INTO empregado (id,nome,sobrenome,funcao,salario,telefone) values (?,?,?,?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,empregado.getId());
            pst.setString(2,empregado.getNome());
            pst.setString(3,empregado.getSobrenome());
            pst.setString(4,empregado.getFuncao());
            pst.setInt(5,empregado.getSalario());
            pst.setString(6,empregado.getTelefone());
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

    //Buscar empregado no Banco de Dados
    public boolean selectEmpregadoId(int empregadoId) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM empregado";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                Empregado empregadoTemp = new Empregado(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("sobrenome"),
                        resultSet.getString("funcao"),
                        resultSet.getInt("salario"),
                        resultSet.getString("telefone")
                );
                if (empregadoTemp.getId() == empregadoId) {
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

    //Listar infos de um empregado no Banco de Dados
    public void selectInfosEmpregado(int empregadoId) {

        connect();

        boolean verificado;

        String sql = "SELECT * FROM empregado WHERE id = ?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, empregadoId);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Empregado empregadoTemp = new Empregado(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("sobrenome"),
                        resultSet.getString("funcao"),
                        resultSet.getInt("salario"),
                        resultSet.getString("telefone")
                );
                if (empregadoTemp.getId() == empregadoId) {
                    System.out.println("Id do Empregado: " + empregadoTemp.getId());
                    System.out.println("Nome do Empregado: " + empregadoTemp.getNome());
                    System.out.println("Sobrenome do Empregado: " + empregadoTemp.getTelefone());
                    System.out.println("Função do Empregado: " + empregadoTemp.getFuncao());
                    System.out.println("Salário do Empregado: " + empregadoTemp.getSalario());
                    System.out.println("Telefone do Empregado: " + empregadoTemp.getTelefone());
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

    //Deletar empregado no Banco de Dados
    public boolean deleteEmpregado(int empregadoId) {

        connect();

        String sql = "DELETE FROM empregado WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, empregadoId);
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
