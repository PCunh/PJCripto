
package controller;

import DAO.CarteiraDAO;
import javax.swing.JOptionPane;
import modell.Usuario;
import view.Depositar;

public class DepositarController {
    private Usuario usuario;
    private Depositar view;

    public DepositarController() {
    }

    public DepositarController(Usuario usuario, Depositar view) {
        this.usuario = usuario;
        this.view = view;
    }
    
    public void Depositar(double valor){
        if(valor > 0){
            CarteiraDAO.Depositar(usuario.getCarteira(), valor);
            JOptionPane.showMessageDialog(view, "Depósito realizado com sucesso");
            Menu();
        }else{
            JOptionPane.showMessageDialog(view, "ERRO");
        }
    }
    
    public void Menu(){
        Menu menu = new Menu(usuario);
        menu.setVisible(true);
        view.dispose();
    }
}
