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
import java.awt.Label;
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
    private int pos,anchoV=500,altoV=500;
    private Controlador c;
    private int numLetras;
    private Barra b;
    private CheckboxMenuItem[] cbmi;
    private Label lblMarcador;
    
    
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
        
        cbmi=new CheckboxMenuItem[5];
        
        for(int i=0;i<cbmi.length;i++){
            cbmi[i]=new CheckboxMenuItem("Nivel "+(i+1));
            cbmi[i].addItemListener(c);
            MenuShortcut msControl=new MenuShortcut(49+i);
            cbmi[i].setShortcut(msControl);
            level.add(cbmi[i]);
        }
        cbmi[0].setState(true);
        
        lblMarcador=new Label("Marcador: 0");
        lblMarcador.setBounds(0, 50, 100, 30);
        this.add(lblMarcador);
        
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
        
        for(int i=0;i<cbmi.length;i++){
            if(cbmi[i].getState()==true){
                c.cambiarNivel(Integer.parseInt(cbmi[i].getLabel().split(" ")[1]));
                break;
            }
        }
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
        for(int i=0;i<letras.size();i++){
            //System.out.println(caracter==letras.get(i).getNombre()+caracter+"-"+letras.get(i).getNombre());
            if(caracter==letras.get(i).getNombre()){
                System.out.println("rrrrtttt");
                this.remove(botones.get(i));
                letras.remove(i);
                botones.remove(i);
                c.letraEliminada(letras.get(i).getNombre());
                numLetras--;
                Letra.eliminarChar(caracter+"");
                setMarcador();
            }
        }
    }
    
    public void comprobarChoque(){
        for(int i=0;i<letras.size();i++){
            if(c.comprobarPos(letras.get(i).getX(), letras.get(i).getY())){
                System.out.println("CHOQUE");
                letras.get(i).cambioDireccion();
                
            }else{
                
                
            }
        }
        

        
        
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
    
    /**
     * Marca todos los CheckboxMenuItem de nivel a false.
     */
    public void cbmiFalse(){
        for(int i=0;i<cbmi.length;i++){
            cbmi[i].setState(false);
        }
    }
    /**
     * Marca el CheckboxMenuItem que recibe a true.
     * @param item 
     */
    public void cbmiTrue(String item){
        for(int i=0;i<cbmi.length;i++){
            if(cbmi[i].getLabel()==item){
                cbmi[i].setState(true);
                break;
            }
        }
    }
    
    public void cambiarFondo(){
        int red = (int)(Math.random()*255);
        int green = (int)(Math.random()*255);
        int blue = (int)(Math.random()*255);
        
        Color color = new Color(red,green,blue);
        this.setBackground(color);
    }
    
    public void actualizarVelocidad(int velocidad){
        for(int i=0;i<letras.size();i++){
            letras.get(i).setVelocidad(velocidad);
        }
    }
    
    public void setMarcador(){
        lblMarcador.setText("Marcador: "+c.incrementarMarcador());
    } 
}
