/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.Label;

/**
 *
 * @author Jose
 */
public class Barra extends Label {

    private int anchoV, altoB, anchoB;
    private int x, y;

    public Barra(int anchoV, int y) {
        this.y = y;
        altoB = 30;
        anchoB = 50;
        this.anchoV = anchoV;
        x = this.anchoV / 2 - anchoB / 2;

    }

    public int getAltoB() {
        return altoB;
    }

    public int getAnchoB() {
        return anchoB;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * mueve la barra hacia la derecha comprobando que no se sale de la ventana
     */
    public void moverDerecha() {

        this.x = x + 5;

    }

    /**
     * mueve la barra hacia la izquierda comprobando que no se sale de la
     * ventana
     */
    public void moverIzquierda() {

        this.x = x - 5;

    }

}
