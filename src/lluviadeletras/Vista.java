/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.Button;
import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Jose
 */
public class Vista extends Frame{
    private ArrayList<Letra> letras;
    private ArrayList<Button> botones;
    private Timer timer;
    private Button btnLetra;
    private int pos,anchoV=500,altoV=500;
    private Controlador c;
    private int numLetras;
    private Barra b;
    
    
    public Vista(Controlador c){
        this.c=c;
        b=new Barra(anchoV);
        b.setBackground(Color.red);
        b.setBounds(b.getX(), b.getY(), b.getAnchoB(), b.getAltoB());
        this.add(b);
        letras=new ArrayList();
        botones=new ArrayList();
        generarMenu();
        this.addKeyListener(c);
        this.setLayout(null);
        this.setBounds(100, 100, anchoV, altoV);
        this.setVisible(true);
    }
    
    public void generarMenu(){
        MenuBar mb=new MenuBar();
        
        Menu arch=new Menu("Archivo");
        mb.add(arch);
        Menu level=new Menu("Nivel");
        mb.add(level);
        
        MenuItem salir=new MenuItem("Salir");
        arch.add(salir);
        
        CheckboxMenuItem level1=new CheckboxMenuItem("Nivel 1",true);
        MenuShortcut msControl1=new MenuShortcut(49);
        level1.setShortcut(msControl1);
        
        CheckboxMenuItem level2=new CheckboxMenuItem("Nivel 2");
        MenuShortcut msControl2=new MenuShortcut(50);
        level2.setShortcut(msControl2);
        
        CheckboxMenuItem level3=new CheckboxMenuItem("Nivel 3");
        MenuShortcut msControl3=new MenuShortcut(51);
        level3.setShortcut(msControl3);
        
        CheckboxMenuItem level4=new CheckboxMenuItem("Nivel 4");
        MenuShortcut msControl4=new MenuShortcut(52);
        level4.setShortcut(msControl4);
        
        CheckboxMenuItem level5=new CheckboxMenuItem("Nivel 5");
        MenuShortcut msControl5=new MenuShortcut(53);
        level5.setShortcut(msControl5);
        
        level.add(level1);
        level.add(level2);
        level.add(level3);
        level.add(level4);
        level.add(level5);
        
        
        this.setMenuBar(mb);
    }
    
    public void generarLetras(){
        letras.add(new Letra(500,500));
        
        btnLetra=new Button(""+letras.get(numLetras).getNombre());
        btnLetra.setBounds(letras.get(numLetras).getX(), 0, letras.get(numLetras).getLadoLetra(), letras.get(numLetras).getLadoLetra());
        this.add(btnLetra);
        botones.add(btnLetra);
        
        this.setVisible(true);
        numLetras++;
    }
    
    public void mover(){
        comprobarChoque();
        for(int i=0;i<botones.size();i++){
            letras.get(i).mover();
            botones.get(i).setLocation(letras.get(i).getX(),letras.get(i).getY());
            b.setBounds(b.getX(), b.getY(), b.getAnchoB(), b.getAltoB());
        }
        this.setVisible(true);
    }
    
    public void eliminarLetra(char caracter){
        System.out.println(caracter);
        System.out.println(letras);
        
        for(int i=0;i<letras.size();i++){
            //System.out.println(caracter==letras.get(i).getNombre()+caracter+"-"+letras.get(i).getNombre());
            if(caracter==letras.get(i).getNombre()){
                this.remove(botones.get(i));
                letras.get(i).eliminarChar(i);
                letras.remove(i);
                botones.remove(i);
                numLetras--;
               
            }
        }
    }
    
    public void comprobarChoque(){
        for(int i=0;i<letras.size();i++){
            if(letras.get(i).getVelocidad()<0){
                System.out.println("-------->");
                if (c.comprobarSalida(letras.get(i).getX(), letras.get(i).getY())) {
                    eliminarLetra(letras.get(i).getNombre());
                    System.out.println("Letra fuera de la ventana");
                }
            }else
            if(c.comprobarPos(letras.get(i).getX(), letras.get(i).getY())){
                System.out.println("CHOQUE");
                letras.get(i).cambioDireccion();
                
            }
        }
        

        
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
    public void moverDerechaBarra(){
        b.moverDerecha();
      
    }
    public void moverIzquierdaBarra(){
        b.moverIzquierda();
    }
    public int getXBarra(){
        return b.getX();
    }
    public int getYBarra(){
        return b.getY();
    }
}
