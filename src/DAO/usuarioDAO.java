
package DAO;

//Import's nescessários que fazem o projeto funcionar
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

//Classe pública com argumento privado que conecta com o banco de dados
public class usuarioDAO {
    private Connection conn;
    
    //Construtor parametrizado
    public usuarioDAO(Connection conn) {
        this.conn = conn;
    }
    
    //Classe booleana para o login linkado ao banco de dados  
      public boolean efetuarLogin(String CPF, String senha) throws SQLException{
        String query = "SELECT * FROM Usuario WHERE CPF = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, CPF);
        statement.setString(2, senha);
        ResultSet rs = statement.executeQuery();
        return rs.next(); 
    }

    //Classe pública para a consulta de saldo linkado ao banco de dados 
    public Decimal consultarSaldo(String moeda) throws SQLException {
        String query = "SELECT saldo FROM Carteira WHERE ID = ? AND moeda = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "ID"); 
        stmt.setString(2, moeda);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getDecimal("saldo");
        }
        return Decimal.ZERO;
    }

    //Classe pública para deposito linkado ao banco de dados 
    public void depositarReais(Decimal valor) throws SQLException {
        String query = "UPDATE Carteira SET saldo "
                + "= saldo + ? WHERE ID = ? AND moeda = 'BRL'";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setDecimal(1, valor);
        statement.setString(2, "ID"); 
        statement.executeUpdate();
    }

    //Classe pública booleana para o saque em reais linkado ao banco de dados
    public boolean sacarReais(Decimal valor) throws SQLException {
        Decimal saldoAtual = consultarSaldo("BRL");
        if (saldoAtual.compareTo(valor) >= 0) {
            String query = "UPDATE Carteira SET saldo "
                    + "= saldo - ? WHERE ID = ? AND moeda = 'BRL'";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDecimal(1, valor);
            statement.setString(2, "ID"); 
            statement.executeUpdate();
            return true;
        }
        return false;
    }
    
    //Classe pública booleana para a compra de cripto linkado ao banco de dados
    public boolean comprarCriptomoeda(String tipoCripto, Decimal quantidade)
                                      throws SQLException {
    // Verificar o saldo em reais do usuário
        Decimal saldoReais = consultarSaldo("BRL");
        Decimal cotacaoCripto = consultarCotacao(tipoCripto);
        Decimal valorCompra = cotacaoCripto.multiply(quantidade);

        // Verificar se o saldo em reais é suficiente para a compra
        if (saldoReais.compareTo(valorCompra) >= 0) {
            // Atualizar saldo em reais
            String atualizarReais = "UPDATE Carteira SET saldo = "
                        + "saldo - ? WHERE ID = ? AND moeda = 'BRL'";
            PreparedStatement stmtReais = conn.prepareStatement(atualizarReais);
            stmtReais.setDecimal(1, valorCompra);
            stmtReais.setString(2, "ID"); 
            stmtReais.executeUpdate();

            // Atualizar saldo da criptomoeda
            String atualizarCripto = "UPDATE Carteira SET saldo = "
                        + "saldo + ? WHERE ID = ? AND moeda = ?";
            PreparedStatement stmtCripto = conn.prepareStatement(atualizarCripto);
            stmtCripto.setDecimal(1, quantidade);
            stmtCripto.setString(2, "ID");
            stmtCripto.setString(3, tipoCripto);
            stmtCripto.executeUpdate();

            // Registrar operação
            registrarOperacao("COMPRA", tipoCripto, quantidade, valorCompra);

            return true;
        }
        return false; // Saldo insuficiente
    }
        
    public boolean venderCriptomoeda(String tipoCripto, Decimal quantidade) 
            throws SQLException {
    // Verificar saldo da criptomoeda
    Decimal saldoCripto = consultarSaldo(tipoCripto);
    Decimal cotacaoCripto = consultarCotacao(tipoCripto);
    Decimal valorVenda = cotacaoCripto.multiply(quantidade);

    // Verificar se o usuário possui quantidade suficiente da criptomoeda
    if (saldoCripto.compareTo(quantidade) >= 0) {
        // Atualizar saldo da criptomoeda
        String atualizarCripto = "UPDATE Carteira SET saldo = "
                + "saldo - ? WHERE ID = ? AND moeda = ?";
        PreparedStatement stmtCripto = conn.prepareStatement(atualizarCripto);
        stmtCripto.setDecimal(1, quantidade);
        stmtCripto.setString(2, "ID");
        stmtCripto.setString(3, tipoCripto);
        stmtCripto.executeUpdate();

        // Atualizar saldo em reais
        String atualizarReais = "UPDATE Carteira SET saldo = "
                + "saldo + ? WHERE ID = ? AND moeda = 'BRL'";
        PreparedStatement stmtReais = conn.prepareStatement(atualizarReais);
        stmtReais.setBigDecimal(1, valorVenda);
        stmtReais.setString(2, "ID");
        stmtReais.executeUpdate();

        // Registrar operação
        registrarOperacao("VENDA", tipoCripto, quantidade, valorVenda);

        return true;
        }
    return false; // Saldo insuficiente da criptomoeda
    }

}
    
    

