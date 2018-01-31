/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Jose
 */
public class ControlBarra implements KeyListener{
    private Barra b;
    
    public ControlBarra(Barra b){
        this.b=b;
    }

    @Override
    public void keyTyped(KeyEvent e) {
     }
    
   
    /**
     * Mueve la barra
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            b.moverDerecha();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            b.moverIzquierda();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
     }
    
}
