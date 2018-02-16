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
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Jose
 */
public class Vista extends Frame {
    
    private ArrayList<Letra> letras;
    private ArrayList<Label> botones;
    private Label btnLetra;
    private int anchoV = 500, altoV = 500;
    private Controlador c;
    private int numLetras;
    private Barra b, b2;
    private CheckboxMenuItem[] cbmi;
    private Label lblMarcador;
    private Label lblNivel;
    private int aciertos;
    private int nivel = 1;
    private Panel negro;
    private Label mensaje;
    
    public Vista(Controlador c) {
        this.c = c;
        
        b = new Barra(anchoV, 450);
        b.setBackground(Color.red);
        b.setBounds(b.getX(), b.getY(), b.getAnchoB(), b.getAltoB());
        
        b2 = new Barra(anchoV, 30);
        b2.setBackground(Color.red);
        b2.setBounds(b2.getX(), b2.getY(), b2.getAnchoB(), b2.getAltoB());
        
        mensaje = new Label("Pulsa ENTER para continuar");
        mensaje.setBounds(200, 200, 160, 30);
        mensaje.setForeground(Color.white);
        mensaje.setBackground(Color.black);
        mensaje.setVisible(false);
        this.add(mensaje);
        
        negro = new Panel();
        negro.setBackground(Color.black);
        negro.setBounds(0, 0, anchoV, altoV);
        negro.setVisible(false);
        this.add(negro);
        
        this.add(b);
        this.add(b2);
        letras = new ArrayList();
        botones = new ArrayList();
        generarMenu();
        this.addKeyListener(c);
        this.setLayout(null);
        this.setBounds(100, 100, anchoV, altoV);
        this.setVisible(true);
    }

    /**
     * Creamos todos los elementos del menu y los añadimos a la ventana
     */
    public void generarMenu() {
        MenuBar mb = new MenuBar();
        
        Menu arch = new Menu("Archivo");
        mb.add(arch);
        Menu level = new Menu("Nivel");
        mb.add(level);
        
        MenuItem salir = new MenuItem("Salir");
        arch.add(salir);
        
        cbmi = new CheckboxMenuItem[5];
        
        for (int i = 0; i < cbmi.length; i++) {
            cbmi[i] = new CheckboxMenuItem("Nivel " + (i + 1));
            cbmi[i].addItemListener(c);
            MenuShortcut msControl = new MenuShortcut(49 + i);
            cbmi[i].setShortcut(msControl);
            level.add(cbmi[i]);
        }
        cbmi[0].setState(true);
        
        lblMarcador = new Label("Marcador: 0");
        lblMarcador.setBounds(10, 50, 100, 30);
        this.add(lblMarcador);
        
        lblNivel = new Label();
        lblNivel.setBounds(400, 50, 100, 30);
        this.add(lblNivel);
        for (int i = 0; i < cbmi.length; i++) {
            if (cbmi[i].getState() == true) {
                int nivel = Integer.parseInt(cbmi[i].getLabel().split(" ")[1]);
                lblNivel.setText("Nivel: " + nivel);
                break;
            }
        }
        
        this.setMenuBar(mb);
    }

    /**
     * Metodo que genera una instancia de la clase letra, crea el boton y lo
     * añade a la ventana Este metodo tendra que ser llamado periodicamente para
     * generar mas letras.
     */
    public void generarLetras() {
        letras.add(new Letra(500, 500));
        
        btnLetra = new Label("" + letras.get(numLetras).getNombre());
        btnLetra.setBounds(letras.get(numLetras).getX(), 0, letras.get(numLetras).getLadoLetra(), letras.get(numLetras).getLadoLetra());
        this.add(btnLetra);
        botones.add(btnLetra);
        
        this.setVisible(true);
        
        letras.get(numLetras).setVelocidad(c.getVelocidad());
        numLetras++;

        /*for(int i=0;i<cbmi.length;i++){
            if(cbmi[i].getState()==true){
                c.cambiarNivel(Integer.parseInt(cbmi[i].getLabel().split(" ")[1]));
                break;
            }
        }*/
    }

    /**
     * Metodo encargado de mover todos los botones llamando a comprobar si ha
     * chocado con la barra Este metodo sera llamado periodicamente.
     */
    public void mover() {
        comprobarChoque();
        for (int i = 0; i < botones.size(); i++) {
            letras.get(i).mover();
            botones.get(i).setLocation(letras.get(i).getX(), letras.get(i).getY());
            b.setBounds(b.getX(), b.getY(), b.getAnchoB(), b.getAltoB());
            b2.setBounds(b2.getX(), b2.getY(), b2.getAnchoB(), b2.getAltoB());
        }
        this.setVisible(true);
    }

    /**
     * Comprueba si existe la letra, si es así la elimina sino llama a cambiar
     * de color el fondo y a restar en el marcador.
     *
     * @param caracter la letra que queremos eliminar
     */
    public void eliminarLetra(char caracter) {
        int i;
        for (i = 0; i < letras.size() && caracter != letras.get(i).getNombre(); i++);
        System.out.println((int) caracter);
        if (i == letras.size()) {
            if (caracter >= 65 && caracter <= 90) {
                System.out.println("caracter:" + caracter);
                lblMarcador.setText("Marcador: " + c.restarMarcador());
                cambiarFondo(Color.red);
                Timer timer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cambiarFondo(Color.white);
                    }
                });
                timer.start();
            }
            
        } else {
            this.remove(botones.get(i));
            letras.remove(i);
            botones.remove(i);
            numLetras--;
            Letra.eliminarChar(i);
            setMarcador();
        }
        
    }

    /**
     * Llama letra a letra al metodo comprobar salida, comprobando si ha salido
     * por arriba o por abajo
     */
    public void comprobarChoque() {
        for (int i = 0; i < letras.size(); i++) {
            if (letras.get(i).getVelocidad() < 0) {
                if (!c.comprobarSalida(letras.get(i).getX(), letras.get(i).getY())) {
                    letras.get(i).cambioDireccion();
                }
                
            } else if (c.comprobarPos(letras.get(i).getX(), letras.get(i).getY())) {
                System.out.println("CHOQUE");
                letras.get(i).cambioDireccion();
                
            }
        }
        
    }

    /**
     * Llama a mover derecha de la barra
     */
    public void moverDerechaBarra() {
        if (b.getX() < anchoV-b.getAnchoB() && b2.getX() < anchoV-b2.getAnchoB()) {
            System.out.println("mueve a la derecha");
            b.moverDerecha();
            b2.moverDerecha();
        }
    }

    /**
     * Llama a mover izquierda de la barra
     */
    public void moverIzquierdaBarra() {
        if (b.getX() > 0 && b2.getX() > 0) {
            System.out.println("mueve a la izquierda");
            b.moverIzquierda();
            b2.moverIzquierda();
        }
        
    }
    
    public int getXBarra() {
        return b.getX();
    }

    public int getXBarra2() {
        return b2.getX();
    }
    
    public int getYBarra() {
        return b.getY();
    }

    public int getYBarra2() {
        return b2.getY();
    }

    /**
     * Marca todos los CheckboxMenuItem de nivel a false.
     */
    public void cbmiFalse() {
        for (int i = 0; i < cbmi.length; i++) {
            cbmi[i].setState(false);
        }
    }

    /**
     * Marca el CheckboxMenuItem que recibe a true.
     *
     * @param item
     */
    public void cbmiTrue(String item) {
        
        for (int i = 0; i < cbmi.length; i++) {
            if (cbmi[i].getLabel().equals(item)) {
                lblNivel.setText("Nivel: " + cbmi[i].getLabel().split(" ")[1]);
                cbmi[i].setState(true);
                break;
            }
        }
    }

    /**
     * Cambia el fondo de la vista al color del parametro
     *
     * @param color color que queremos de fondo
     */
    public void cambiarFondo(Color color) {
        this.setBackground(color);
    }

    /**
     * Establece la velocidad de todas las letras al numero que se le pasa
     *
     * @param velocidad
     */
    public void actualizarVelocidad(int velocidad) {
        ponerPanelNegro();
        for (int i = 0; i < letras.size(); i++) {
            if (letras.get(i).getVelocidad() >= 0) {
                letras.get(i).setVelocidad(velocidad);
            } else {
                letras.get(i).setVelocidad(-velocidad);
            }
            
        }
    }
    
    private void ponerPanelNegro() {
        negro.setVisible(true);
        mensaje.setVisible(true);
    }
    
    public void quitarPanelNegro() {
        negro.setVisible(false);
        mensaje.setVisible(false);
    }

    /**
     * Actualiza el label del marcador y actualiza el marcador del modelo
     */
    public void setMarcador() {
        int marcador = c.incrementarMarcador();
        comprobarMarcador(marcador);
        lblMarcador.setText("Marcador: " + marcador);
    }

    /**
     * Comprueba el numero de aciertos seguidos y si son 10 aumenta el nivel
     *
     * @param marcador
     */
    public void comprobarMarcador(int marcador) {
        aciertos++;
        if (aciertos == 10 && nivel < 5) {
            aciertos = 0;
            for (int i = 0; i < cbmi.length; i++) {
                if (cbmi[i].getState() == true) {
                    nivel = Integer.parseInt(cbmi[i].getLabel().split(" ")[1]);
                    cbmiFalse();
                    cbmiTrue("Nivel " + (nivel + 1));
                    c.cambiarNivel(nivel + 1);
                    break;
                }
            }
            
        }
    }

    public int getLado() {
        return Letra.getLadoLetra();
    }
    
    public int getAltoBarra() {
        return b.getAltoB();
    }
    
    public int getLadoBarra() {
        return b.getAnchoB();
    }
    
    public void cambiarPosicionBarras() {
        b.setX(anchoV / 2 - b.getAnchoB());
        b2.setX(anchoV / 2);
        //  b.setBounds((anchoV/2-b.getAnchoB()), b.getY(), b.getAnchoB(), b.getAltoB());
        // b2.setBounds((anchoV/2), b2.getY(), b2.getAnchoB(), b2.getAltoB());
    }
}
