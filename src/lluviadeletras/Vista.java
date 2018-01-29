/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.Button;
import java.awt.CheckboxMenuItem;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
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
    private int pos;
    private Controlador c;
    
    public Vista(Controlador c){
        this.c=c;
        letras=new ArrayList();
        botones=new ArrayList();
        generarMenu();
        
        this.setLayout(null);
        this.setBounds(100, 100, 500, 500);
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
    
    public void generarLetras(char letra){
        //System.out.println(letra);
        pos=(int)(Math.random()*500);
        letras.add(new Letra(pos,letra));
        
        btnLetra=new Button(""+letra);
        btnLetra.setName(letra+"");
        btnLetra.setBounds(letras.get(letras.size()-1).getX(), 0, 20, 20);
        btnLetra.addActionListener(c);
        this.add(btnLetra);
        botones.add(btnLetra);
        
        this.setVisible(true);
        
    }
    
    public void mover(){
        for(int i=0;i<botones.size();i++){
            letras.get(i).setY(5);
            botones.get(i).setLocation(letras.get(i).getX(),letras.get(i).getY());
        }
        this.setVisible(true);
    }
    
    public void eliminarLetra(ActionEvent e){
        for(int i=0;i<letras.size();i++){
            if(e.getActionCommand().equals(String.valueOf(letras.get(i).getNombre()))){
                this.remove(botones.get(i));
                letras.remove(i);
                botones.remove(i);
                c.letraEliminada(letras.get(i).getNombre());
                
            }
        }
    }
}
