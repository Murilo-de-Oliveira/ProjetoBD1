package br.inatel.DAO;
import java.sql.*;
public class ConnectionDAO {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement pst;

    static final String user = "root";
    static final String password = "?0jy@@T8";
    static final String database = "mydb";

    static final String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    public boolean check = false;

    public void connect(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            //System.out.println("Erro de conexão: " + e.getMessage());
            System.out.println("Deu erro aqui: " + e.getMessage());
        }
    }
}
