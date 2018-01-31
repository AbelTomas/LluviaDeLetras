/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Jose
 */
public class Controlador implements ActionListener{
    private Timer timer,timer2;
    private Vista v;
    private Modelo m;
    
    public Controlador(){
        v=new Vista(this);
        m=new Modelo();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        v.eliminarLetra(e);
    }
}
