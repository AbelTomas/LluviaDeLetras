/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class Modelo {
    private int marcador,velocidad,nivel,numLetras;
    private ArrayList<Character> letrasUsadas;
    
    public Modelo(){
        letrasUsadas=new ArrayList<Character>();
    }
    
    public char generarLetras(){
        int i;
        char ale;
        do{
            ale=(char)(int)(Math.random()*(90-65)+65);
            for(i=0;i<numLetras && letrasUsadas.get(i)!=ale;i++);  
        }while(i!=numLetras);
        letrasUsadas.add(ale);
        numLetras++;
        for(int j=0;j<letrasUsadas.size();j++){
            System.out.print(letrasUsadas.get(j));
        }
        System.out.println("");
        return ale;
    }
    
    public void letraEliminada(char letra){
        for(int i=0;i<letrasUsadas.size();i++){
            if(letrasUsadas.get(i)==letra){
                letrasUsadas.remove(i-1);
                numLetras--;
            }
        }
    }
}
