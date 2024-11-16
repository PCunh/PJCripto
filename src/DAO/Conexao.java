
package DAO;

//Todos os import's nescessários e que linka o banco de dados
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/*Aqui começa uma classe pública que
linka com o banco de dados atráves do link abaixo*/
public class Conexao {
    public Connection getConnection() throws SQLException{
        Connection conexao = DriverManager.getConnection
        ("jdbc:postgresql://localhost:5432/Usuários","postgres","PEDRO");
        return conexao;
    }
}
