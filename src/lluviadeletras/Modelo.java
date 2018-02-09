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

    private int marcador, velocidad, nivel, numLetras;
    private int xbarra,ybarra,lado = 20, ladobarra=50;
    
    public Modelo() {
       // letrasUsadas = new ArrayList<Character>();
    }
    
    /*
    public void letraEliminada(char letra) {
        for (int i = 0; i < letrasUsadas.size(); i++) {
            if (letrasUsadas.get(i) == letra) {
                letrasUsadas.remove(i - 1);
                numLetras--;
            }
        }
    }*/

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
                
                return false;
            }
        }
        return false;

        /*for(int i=0;i<letras.size();i++){
        if(!letras.isEmpty())
            if(letras.get(i).getY()+letras.get(i).getLadoLetra()>=b.getY()){
                if((letras.get(i).getX()+letras.get(i).getLadoLetra())>=b.getX() && letras.get(i).getX()<=(b.getX()+b.getAnchoB())){
                    System.out.println("CHOQUE");
                    letras.get(i).cambioDireccion();
                    return true;
                }else{
                    System.out.println("JUEGO ACABADO");
                    break;
                }
            }
        }
        System.out.println("NO HAY CHOQUE");
        return false;*/
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
    public boolean comprobarSalida(int x, int y) {
        return y<0;
    }
    
}
