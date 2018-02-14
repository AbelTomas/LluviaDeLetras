/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluvideletras;

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
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;


/**
 *
 * @author Jose
 */
public class Vista extends Frame{
    private ArrayList<Letra> letras;
    private ArrayList<Button> botones;
    private Button btnLetra;
    private int pos,anchoV=500,altoV=500;
    private Controlador c;
    private int numLetras;
    private Barra b;
    private CheckboxMenuItem[] cbmi;
    private Label lblMarcador;
    private Label lblNivel;
    private int aciertos;
    
    
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
        lblMarcador.setBounds(10, 50, 100, 30);
        this.add(lblMarcador);
        
        lblNivel=new Label();
        lblNivel.setBounds(400, 50, 100, 30);
        this.add(lblNivel);
        for(int i=0;i<cbmi.length;i++){
            if(cbmi[i].getState()==true){
                int nivel=Integer.parseInt(cbmi[i].getLabel().split(" ")[1]);
                    lblNivel.setText("Nivel: "+nivel);
                break;
            }
        }
        
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
    /**
     * Comprueba si existe la letra, si es así la elimina sino llama a cambiar de color el fondo y a restar en el marcador.
     * @param caracter 
     */
    public void eliminarLetra(char caracter){
        int i;
        for(i=0;i<letras.size() && caracter!=letras.get(i).getNombre();i++);
        
        if(i==letras.size()){
            System.out.println("caracter:"+caracter);
            lblMarcador.setText("Marcador: "+c.restarMarcador());
            cambiarFondo(Color.red);
            Timer timer=new Timer(100,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cambiarFondo(Color.white);
                }
            });
            timer.start();
        }else{
            this.remove(botones.get(i));
            letras.remove(i);
            botones.remove(i);
            numLetras--;
            Letra.eliminarChar(i);
            setMarcador();
        }
        
    }
    
    public void comprobarChoque(){
        for(int i=0;i<letras.size();i++){
            if(letras.get(i).getVelocidad()<0){
                c.comprobarSalida(letras.get(i).getX(), letras.get(i).getY());
                
            }else
            if(c.comprobarPos(letras.get(i).getX(), letras.get(i).getY())){
                System.out.println("CHOQUE");
                letras.get(i).cambioDireccion();
                
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
            if(cbmi[i].getLabel().equals(item)){
                lblNivel.setText("Nivel: "+cbmi[i].getLabel().split(" ")[1]);
                cbmi[i].setState(true);
                break;
            }
        }
    }
    
    public void cambiarFondo(Color color){
        this.setBackground(color);
    }
    
    
    public void actualizarVelocidad(int velocidad){
        for(int i=0;i<letras.size();i++){
            if(letras.get(i).getVelocidad()>=0){
                letras.get(i).setVelocidad(velocidad);
            }else{
                letras.get(i).setVelocidad(-velocidad);
            }
            
        }
    }
    
    public void setMarcador(){
        int marcador=c.incrementarMarcador();
        comprobarMarcador(marcador);
        lblMarcador.setText("Marcador: "+marcador);
    }
    
    public void comprobarMarcador(int marcador){
        aciertos++;
        if(aciertos==2){
            aciertos=0;
            for(int i=0;i<cbmi.length;i++){
                if(cbmi[i].getState()==true){
                    int nivel=Integer.parseInt(cbmi[i].getLabel().split(" ")[1]);
                    cbmiFalse();
                    cbmiTrue("Nivel "+(nivel+1));
                    c.cambiarNivel(nivel+1);
                    break;
                }
            }
            
        }
    }
}
