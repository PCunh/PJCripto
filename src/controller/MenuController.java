
package controller;

import modell.Usuario;
import view.Menu;
import view.Sacar;

public class MenuController {
    private Menu view;
    
    public MenuController(Menu view){
        this.view = view;
    }
    
    public void ConsultaSaldo(Usuario usuario){
        ConsultaSaldo saldo = new ConsultaSaldo(usuario);
        saldo.setVisible(true);
        view.dispose();
    }
    
    public void ConsultaExtrato(Usuario usuario){
        ConsultaExtrato extrato = new ConsultaExtrato(usuario);
        extrato.setVisible(true);
        view.dispose();
    }
    
    public void Deposita(Usuario usuario){
        Deposita deposita = new Deposita(usuario);
        deposita.setVisible(true);
        view.dispose();
    }
    
    public void Sacar(Usuario usuario){
        Sacar sacar = new Sacar(usuario);
        sacar.setVisible(true);
        view.dispose();
    }
    
    public void ComprarCripto(Usuario usuario){
        ComprarCripto comprar = new ComprarCripto(usuario);
        comprar.setVisible(true);
        view.dispose();
    }
    
    public void VenderCripto(Usuario usuario){
        VenderCripto vender = new VenderCripto(usuario);
        vender.setVisible(true);
        view.dispose();
    }
    
    public void Sair(){
        System.exit(0);
    }
}
