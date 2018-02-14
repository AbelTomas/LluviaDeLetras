/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluvideletras;

import java.awt.Label;

/**
 *
 * @author Jose
 */
public class Barra extends Label{
    private int anchoV,altoB,anchoB;
    private int x, y = 450;

    
    public Barra(int anchoV){
        altoB=30;
        anchoB=50;
        this.anchoV=anchoV;
        x=this.anchoV/2-anchoB/2;

        
    }

    public int getAltoB() {
        return altoB;
    }

    public int getAnchoB() {
        return anchoB;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moverDerecha() {
        if(x<(anchoV-anchoB)){
            this.x = x+5;
        }
        
    }
    public void moverIzquierda() {
        if(x>0){
            this.x = x-5;
        }
        
    }
    
}
