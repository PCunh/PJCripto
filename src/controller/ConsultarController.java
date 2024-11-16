
package controller;

import modell.Usuario;
import view.Menu;
import view.Saldo;

public class ConsultarController {
    private Usuario usuario;
    private Saldo view;

    public ConsultarController() {
    }
    
    public ConsultarController(Usuario usuario, Saldo view) {
        this.usuario = usuario;
        this.view = view;
    }
    
    public void Saldo(){
        view.Saldo(usuario.getCarteira());
    }
    
    public void Menu(){
        Menu menu = new Menu(usuario);
        menu.setVisible(true);
        view.dispose();
    }
}
