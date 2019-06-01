package tetris;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class Interfaz extends JPanel {

    public static JTable tabla = new JTable();

    public Interfaz(CrearEntorno juego) {

        setBounds(250, 0, 250, 600);//dimension del panel
        JLabel nombre = new JLabel();
        Font auxFont = nombre.getFont();
        nombre.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 50));//aumentamos el tamaño de la letra
        nombre.setForeground(Color.WHITE);//dar color de letra blanco
        nombre.setText("TETRIS");//asignar texto

        JLabel score = new JLabel();
        Font auxFont2 = score.getFont();
        score.setFont(new Font(auxFont2.getFontName(), auxFont2.getStyle(), 30));//aumentamos el tamaño de la letra
        score.setForeground(Color.WHITE);//dar color de letra blanco
        score.setText("SCORE:");//asignar texto

        JLabel puntuaciones = new JLabel();
        puntuaciones.setForeground(Color.WHITE);//color de letra blanco
        puntuaciones.setBackground(Color.BLACK);//color de fondo negro
        puntuaciones.setText("MEJORES PUNTUACIONES");//asignar texto

        JLabel instrucciones = new JLabel();
        instrucciones.setForeground(Color.WHITE);//dar color de letra blanco
        instrucciones.setText("Instrucciones:");//asignar texto

        JLabel left = new JLabel();
        left.setForeground(Color.WHITE);//dar color blanco
        left.setText("VK_LEFT-Mover a la izquierda");//asignar valor de texto

        JLabel right = new JLabel();
        right.setForeground(Color.WHITE);//dar color blanco
        right.setText("VK_RIGHT-Mover a la derecha");//asignar valor de texto

        JLabel up = new JLabel();
        up.setForeground(Color.WHITE);//dar color blanco
        up.setText("VK_UP-----------------Girar pieza");//asignar valor de texto

        JLabel down = new JLabel();
        down.setForeground(Color.WHITE);//dar color blanco
        down.setText("VK_DOWN----------Bajar rapido");//asignar valor de texto

        JLabel p = new JLabel();
        p.setForeground(Color.WHITE);//dar color blanco
        p.setText("p------------------------------Pausa");//asignar valor de texto

        JLabel espacio = new JLabel();
        espacio.setForeground(Color.WHITE);//dar color blanco
        espacio.setText("VK_SPACE-------bajar de golpe");//asignar valor de texto

        tabla.setModel(new javax.swing.table.DefaultTableModel(//formato a la tabla 
                new Object[][]{},
                new String[]{
                    "NICK", "PUNTOS", "Nº"
                }
        ));
        tabla.setBackground(Color.BLACK);//fonde negro
        tabla.setForeground(Color.WHITE);//letra blanca

        JScrollPane barra = new JScrollPane(tabla);//para mostrar los titulos de las columnas
        barra.setForeground(Color.WHITE);//letra blanca
        barra.setBackground(Color.BLACK);//fondo negro
        tabla.setEnabled(false);
        //añadir elementos
        add(score);
        add(nombre);
        add(puntuaciones);
        add(instrucciones);
        add(left);
        add(right);
        add(up);
        add(down);
        add(p);
        add(espacio);
        add(barra);
//dimesnionar
        nombre.setBounds(35, 50, 200, 50);
        puntuaciones.setBounds(50, 455, 200, 20);
        barra.setBounds(25, 475, 200, 70);

        score.setBounds(40, 140, 200, 50);

        instrucciones.setBounds(20, 220, 200, 100);
        left.setBounds(20, 240, 250, 100);
        right.setBounds(20, 260, 250, 100);
        up.setBounds(20, 280, 250, 100);
        down.setBounds(20, 300, 250, 100);
        p.setBounds(20, 320, 250, 100);
        espacio.setBounds(20, 340, 250, 100);

        metodos.CargarTabla.mostrarTablas(tabla);//mostrar datos en la tabla

    }
}
