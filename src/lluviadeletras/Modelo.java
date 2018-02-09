/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;


/**
 *
 * @author Jose
 */
public class Modelo {
    private Controlador c;
    private int marcador, velocidad, nivel, numLetras;
    private int xbarra,ybarra,lado = 20, ladobarra=50;

    
    public Modelo(Controlador c) {
        this.c=c;
        
    }
    
   
    
    

    /**
     * comprueba si la letra ha chocado con la barra
     *
     * @param xletra
     * @param yletra
     * @return
     */
    public boolean comprobarChoque(int xletra, int yletra) {
        if ((yletra + lado) >= ybarra) {
            if ((xletra + lado) >= xbarra && xletra <= (xbarra + ladobarra)) {
                //ha chocado con la barra
                return true;
            } else {
                //no se ha chocado con la barra.
                finJuego(1);
                return false;
            }
        }
        return false;

    }
    public void setXBarra(int x){
        xbarra=x;
    }
    public void setYBarra(int y){
        ybarra=y;
    }

    /**
     * COmprueba si la letra se ha salido de la ventana por arriba
     * @param x
     * @param y
     * @return 
     */
    public void comprobarSalida(int x, int y) {
        if (y<0) {
            finJuego(2);
        }
    }

    private void finJuego(int n) {
        c.finTime();
        System.exit(n);
    }
    public void cambiarNivel(int nivel){
        this.nivel=nivel;
        modifVelocidad();
        actualizarVelocidad();
    }
    public void modifVelocidad(){
        velocidad=nivel;
    }
    public void actualizarVelocidad(){
        c.actualizarV(velocidad);
    }
    
    public int incrementarMarcador(){
        marcador++;
        return marcador;

    }
    
}
