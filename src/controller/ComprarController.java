
package controller;

import DAO.CarteiraDAO;
import javax.swing.JOptionPane;
import modell.Usuario;
import view.Menu;

public class ComprarController {
    private Usuario usuario;
    private ComprarCripto view;

    public ComprarController() {
    }

    public ComprarController(Usuario usuario, ComprarCripto view) {
        this.usuario = usuario;
        this.view = view;
    }
    
    public void Comprar(Moeda moeda,double valor){
        if(CarteiraDAO.comprarMoeda(usuario.getCarteira(), moeda, valor)){
            JOptionPane.showMessageDialog(view, "Compra realizada com sucesso");
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
