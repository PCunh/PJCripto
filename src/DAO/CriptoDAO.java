
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CriptoDAO {
    private Connection conn;

    public CriptoDAO() {
    }

    public CriptoDAO(Connection conn) {
        this.conn = conn;
    }
    
    private Decimal consultarCotacao(String tipoCripto) throws SQLException {
        String query = "SELECT cotacao FROM Cripto WHERE tipo = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, tipoCripto);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getDecimal("cotacao");
        }
        throw new SQLException("Cotação não encontrada para a criptomoeda: "
+ tipoCripto);
    }
    
    public void atualizarCotacao(String tipoCripto, Decimal novaCotacao) 
            throws SQLException {
        String query = "UPDATE Criptomoedas SET cotacao = ? WHERE tipo = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDecimal(1, novaCotacao);
        stmt.setString(2, tipoCripto);
        stmt.executeUpdate();
    }
}
