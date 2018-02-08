/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author Jose
 */
public class Controlador extends KeyAdapter {
    private Timer timer,timer2;
    private Vista v;
    private Modelo m;
    
    public Controlador(){
        v=new Vista(this);
        m=new Modelo();
        m.setYBarra(v.getYBarra());
        generarLetras();
        mover();
    }
    
    public void generarLetras(){
        timer=new Timer(2000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v.generarLetras();
            }
        });
        timer.start();
    }
    
    public void mover(){
        timer2=new Timer(100,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v.mover();
            }
        });
        timer2.start();
    }
    
    public void letraEliminada(char letra){
        m.letraEliminada(letra);
    }
    
    public boolean comprobarPos(int xletra, int yletra){
       return m.comprobarChoque(xletra,yletra);
    }
    /**
     * Mueve la barra
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Letra pulsada");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                v.moverDerechaBarra();
                m.setXBarra(v.getXBarra());
                break;
            case KeyEvent.VK_LEFT:
                v.moverIzquierdaBarra();
                m.setXBarra(v.getXBarra());
                break;
            default:
                System.out.println(Character.toUpperCase(e.getKeyChar()));
                v.eliminarLetra(Character.toUpperCase(e.getKeyChar()));
                break;
        }
        
    }
    public void setXbarra(int x){
        m.setXBarra(x);
    }
}
