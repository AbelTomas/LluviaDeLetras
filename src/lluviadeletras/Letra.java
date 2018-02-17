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

    private static final ArrayList letras = new ArrayList();
    private static final int num1 = 48, num2 = 91;  //Numeros de los caracteres de las letras en mayusculas
    private int altoV;
    private int x, y = 0;
    private char nombre;
    private int velocidad;
    private static int ladoLetra = 15;

    public Letra(int anchoV, int altoV) {
        this.altoV = altoV;
        //letras=new ArrayList();
        nombre = generarLetra(); //Asignamos la letra a nuestro objeto letra
        x = generarXAleatoria(anchoV);
    }

    /**
     * Metodo que devuelve una letra aleatoria comprobando que no esta repetida.
     *
     * @return
     */
    private char generarLetra() {
        char c;
        int numAleatorio;
        do {
            //generamos la letra en mayusculas de forma aleatoria
            numAleatorio = (int) Math.floor(Math.random() * (num2 - num1) + num1);
            c = (char) numAleatorio;
        } while (comprobarRepetida(c) || c<65 && c>57); //Comprobamos si se repite
        letras.add(c);  //Añadimos la letra al array
        return c;
    }

    /**
     * Le pasamos la letra que queremos comprobar si esta en el array devuelve
     * true si la letra esta repetida y false si no lo está
     *
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
     * Metodo que genera la posicion x de la letra, le pasamos el ancho de la
     * ventana
     *
     * @param anchoV
     * @return
     */
    private int generarXAleatoria(int anchoV) {
        return (int) Math.floor(Math.random() * ((anchoV-ladoLetra-10+1) + (10)));

    }

    /**
     * Actualiza la posicion y de la letra
     */
    public void mover() {
        this.y += this.velocidad;
    }

    public char getNombre() {
        return nombre;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    /**
     * actualiza la velocidad de la letra
     *
     * @param velocidad
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Establece un valor negativo para la velocidad de la letra
     */
    public void cambioDireccion() {
        this.velocidad = -velocidad;
    }

    /**
     * Elimina del array estatico el caracter que se le pasa
     *
     * @param letra
     */
    public static void eliminarChar(int letra) {
        letras.remove(letra);
    }

    public static int getLadoLetra() {
        return ladoLetra;
    }

    public int getVelocidad() {
        return velocidad;
    }

}
