/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.util.ArrayList;

/**
 *
 * @author Abel y Jose
 */
public class Letra {

    private static ArrayList letras;
    private static final int num1 = 65, num2 = 90;  //Numeros de los caracteres de las letras en mayusculas
    private int altoV;
    private int x, y = 0;
    private char nombre;
    private int velocidad = 1;

    public Letra(int anchoV, int altoV) {
        this.altoV=altoV;
        nombre = generarLetra(); //Asignamos la letra a nuestro objeto letra
        x=generarAleatorio(anchoV);
    }

    private char generarLetra() {
        char c;
        int numAleatorio;
        do {
            //generamos la letra en mayusculas de forma aleatoria
            numAleatorio = (int) Math.floor(Math.random() * (num2 - num1) + num1);
            c = (char) numAleatorio;
        } while (comprobarRepetida(c)); //Comprobamos si se repite
        letras.add(c);  //Añadimos la letra al array
        return c;
    }

    /**
     * Le pasamos la letra que queremos comprobar si esta en el array
     * devuelve true si la letra esta repetida y false si no lo está
     * @param c
     * @return 
     */
    private boolean comprobarRepetida(char c) {
        if (letras.isEmpty()) {
            return false;
        } else {
            return letras.contains(c);  //Si contiene la letra devuelve true = esta repetida
        }
    }

    /**
     * Metodo que genera la posicion x de la letra, le pasamo sel ancho de la ventana
     * @param anchoV
     * @return 
     */
    private int generarAleatorio(int anchoV) {
        return (int) Math.floor(Math.random() * anchoV);
        
    }
    public boolean mover(){
        if(comprobarPosicion()){
            cambiarY();
            return true;
        }else{
            return false;
        }
    }



    private boolean comprobarPosicion() {
        if(x>altoV){
            return false;
        }else{
            return true;
        }
    }
    
    public char getNombre() {
        return nombre;
    }

    private void cambiarY() {
        this.y+=this.velocidad;
    }

    public int getX() {
        return x;
    }

    
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
}
