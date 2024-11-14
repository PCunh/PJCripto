
package controller;

import modell.Usuario;
import javax.swing.JOptionPane;
import view.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.usuarioDAO;
import DAO.Conexao;
import view.Menu;


public class LoginController {
    private Login view;
    
    /**
     *
     * @param view
     */
    public LoginController(Login view){
        this.view = view;
    }

//    public LoginController(Login aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
    public void loginUsuario(){
        Usuario usuario = new Usuario(null, view.getTxtcpf().getText(), view.getTxtsenha().getText());
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            usuarioDAO dao = new usuarioDAO(conn);
            ResultSet res = dao.consultar(usuario);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "Login feito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                String cpf = res.getString("usuario");
                String senha = res.getString("senha");
                Usuario usuario1 = new Usuario(cpf, senha);
                //UsuarioFrame uf = new UsuarioFrame(usuario1);
                lf.setVisible(true);
                view.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(view, "Login não foi encontrado", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    }
}
