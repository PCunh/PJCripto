
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexao {
    public Connection getConnection() throws SQLException{
        //Nesta linha debaixo falta colocar o link do banco de dados
        Connection conexao = DriverManager.getConnection();
        return conexao;
}
