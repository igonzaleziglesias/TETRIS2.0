package tetris;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.*;

public class CrearEntorno extends JFrame {

    JLabel marcador;
    JLabel estado;

    public CrearEntorno() {

        setSize(502, 630);
        setResizable(false);
//        definir
        marcador = new JLabel("0");
        estado = new JLabel("En juego");
//        marcador.setForeground(Color.white);
        Font auxFont = marcador.getFont();
        marcador.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));//aumentamos el tamaño de la letra
        marcador.setForeground(Color.WHITE);//dar color de letra blanco
//        add(marcador, BorderLayout.SOUTH);

        Font auxFont2 = estado.getFont();
        estado.setFont(new Font(auxFont2.getFontName(), auxFont2.getStyle(), 30));//aumentamos el tamaño de la letra
        estado.setForeground(Color.WHITE);//dar color de letra blanco

        JPanel fondo = new JPanel();
        Ventana ventana = new Ventana(this);        //juego
        Interfaz interfaz = new Interfaz(this);  //informacion

        //dimensiones
        fondo.setSize(500, 640);
//        barra.setBounds(25, 400, 200, 100);
        //añadir elementos
        ventana.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        ventana.setLayout(null);
        interfaz.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        interfaz.setLayout(null);

        interfaz.setBackground(Color.BLACK);
        ventana.setBackground(Color.BLACK);
        fondo.setBackground(Color.WHITE);

        //ASIGNACION Y DIMENSION DE OBJETOS
        interfaz.add(marcador);
        marcador.setBounds(170, 140, 100, 50);
        interfaz.add(estado);
        estado.setBounds(40, 190, 200, 50);

        add(interfaz);
        add(ventana);
        add(fondo);
        ventana.startGame();

        setTitle("Tetris");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void exit() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel getScore() {
        return marcador;
    }

    public JLabel getEstado() {
        return estado;
    }

}
