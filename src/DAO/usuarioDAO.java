
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class usuarioDAO {
    private Connection conn;
    
    public usuarioDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar (Usuario usuario) throws SQLException{
        String sql = "select * from aluno where CPF = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getCPF());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public void inserir(Usuario usuario) throws SQLException{
        String sql = "insert into usuario(cpf, senha) values (' " 
                + usuario.getCPF() + "', '" 
                + usuario.getSenha() + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    
    public void atualizar(Usuario usuario) throws SQLException{
        String sql = "update aluno set senha = ? where usuario = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getCPF());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        conn.close();
    }
    
    public void remover(Usuario usuario) throws SQLException{
        String sql = "delete from usuario where CPF = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getCPF());
        statement.execute();
        conn.close();
    }
}
