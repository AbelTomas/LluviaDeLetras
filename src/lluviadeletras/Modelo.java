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
    private int marcador, velocidad=1, nivel=1;
    private int xbarra,ybarra,xbarra2,ybarra2,lado, ladobarra, altoBarra;

    
    public Modelo(Controlador c) {
        this.c=c;
        ladobarra=c.getLadoBarra();
        altoBarra=c.getAltoBarra();
        lado=c.getLado();
        System.out.println(altoBarra+" "+ladobarra);
        
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
    public void setXBarra2(int x){
        xbarra2=x;
    }
    public void setYBarra2(int y){
        ybarra2=y;
    }

    /**
     * COmprueba si la letra se ha salido de la ventana por arriba
     * @param x
     * @param y
     */
    public boolean comprobarSalida(int x, int y) {
        //false si ha chocado y true si se ha salido
        System.out.println(altoBarra);
        System.out.println(y<(ybarra2+altoBarra));
        if (x>xbarra2 && x<(xbarra2+ladobarra) && y<(ybarra2+altoBarra)) {
            System.out.println("choque con barra");
            return false;
        }else if(y<ybarra2){
            finJuego(2);
        }
        System.out.println("no hay choque");
        
        return true;
    }

    /**
     * Finaliza la ejecucion del programa
     * @param n parametro que indicara en que momento se ha llamado a esta funcion.
     */
    public void finJuego(int n) {
        c.finTime();
        System.exit(n);
    }
    /**
     * Establece el nivel en el modelo y llama a actualizar velocidad de la vista
     * @param nivel 
     */
    public void cambiarNivel(int nivel){
        this.nivel=nivel;
        modifVelocidad();
        if (nivel>=3) {
            c.cambiarPosicionBarras();
        }
        c.actualizarV(velocidad);
        c.finTime();
    }
    /**
     * Ajusta la velocidad de caida de la letra al nivel.
     */
    public void modifVelocidad(){
        velocidad=nivel;
    }
    /**
     * Aumenta el marcador
     * @return devuelve el numero del marcador
     */
    public int incrementarMarcador(){
        marcador++;
        return marcador;

    }
    /**
     * Disminuye el marcador
     * @return devuelve el valor del marcador
     */
    public int restarMarcador(){
        marcador--;
        return marcador;
    }
    
    public int getVelocidad(){
        return velocidad;
    }
    
}
