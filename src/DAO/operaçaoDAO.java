
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class operaçaoDAO {
    private Connection conn;

    public operaçaoDAO() {
    }

    public operaçaoDAO(Connection conn) {
        this.conn = conn;
    }
     
    //Classe pública para consulta de extrato linkado ao banco de dados
    public List<operacao> consultarExtrato(String ID) throws SQLException {
        List<operacao> extrato = new ArrayList<>();
        String query = "SELECT * FROM Operacoes WHERE ID = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, ID);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            operacao operacao = new operacao(rs.getInt("ID"), 
                    rs.getString("tipo"), 
                    rs.getBigDecimal("quantidade"), rs.getTimestamp("data"));
            extrato.add(operacao);
        }
        return extrato;
    }
    
    private void registrarOperacao(String tipo, String moeda, Decimal quantidade, Decimal valor) throws SQLException {
        String query = "INSERT INTO Operacoes (ID, tipo, moeda, quantidade, valor, data) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "ID"); 
        stmt.setString(2, tipo);
        stmt.setString(3, moeda);
        stmt.setBigDecimal(4, quantidade);
        stmt.setBigDecimal(5, valor);
        stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
        stmt.executeUpdate();
    }
}
