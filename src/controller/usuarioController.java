
package controller;

import DAO.usuarioDAO;
import DAO.Conexao;
import javax.swing.JOptionPane;
import modell.Usuario;
import view.Login;
import java.sql.Connection;
import java.sql.SQLException;

public class usuarioController {
    private Login view;
    private Usuario usuario;

    public usuarioController(Login view, Usuario usuario) {
        this.view = view;
        this.usuario = usuario;
    }
    
    public void atualiza(){
        String CPF = view.getTxtCPF().getText();
        String novaSenha = view.getTxtSenha().getText();
        Usuario usuario = new Usuario("", CPF, novaSenha);
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            usuarioDAO dao = new usuarioDAO(conn);
            dao.atualizar(usuario);
            JOptionPane.showMessageDialog(view, "Senha Atualizada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, "Senha não atualizada!", "ERRO", JOptionPane.ERROR_MESSAGE);       
    }
}
