package tetris;

import easterEgg.Egg;
import excepciones.ExcepcionSql;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import libreria.Pedir;
import sonido.SonidoFondo;
import static tetris.App.INDEX;
import static tetris.Menu.crearMenu;
import tetris.Piezas.PiezasTetris;

public class Ventana extends JPanel implements ActionListener {

    int contador = 0;
    final int anchoTablero = 10;
    final int altoTablero = 25;

    Timer timer;// Timer es una clase de java que nos permite asignar acciones periodicamente
    boolean finalizoQuitarFilas = false;
    boolean empezada = false;
    boolean pausa = false;
    int numLineasBorradas = 0;
    int posicionX = 0;
    int posicionY = 0;
    JLabel marcador;
    JLabel estado;
    Piezas piezaActual;
    PiezasTetris[] piezas;
    int velocidad;
SonidoFondo son = new SonidoFondo();
    
    public Ventana(CrearEntorno juego) {//define la ventana de juego
        setBounds(0, 0, 250, 600);
        setFocusable(true);
        piezaActual = new Piezas();
        velocidad = niveles.ElegirNivel.nivel();
        
         son.start();
        timer = new Timer(velocidad, this);
        timer.start();

        marcador = juego.getScore();
        estado = juego.getEstado();
        piezas = new PiezasTetris[anchoTablero * altoTablero];
        addKeyListener(new InteraccionTeclas());
        clear();

    }

    public String getMarcador() {
        return marcador.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (finalizoQuitarFilas) {
            finalizoQuitarFilas = false;
            nuevaPieza();
        } else {
            BajarPiezaRapido();
        }
    }

    int anchoPieza() {//dar ancho
        return (int) getSize().getWidth() / anchoTablero;
    }

    int alturaPieza() {//dar altura
        return (int) getSize().getHeight() / altoTablero;
    }

    PiezasTetris dimensionar(int x, int y) {
        return piezas[(y * anchoTablero) + x];
    }

    public void startGame() {
        if (pausa) {
            return;
        }

        empezada = true;
        finalizoQuitarFilas = false;
        numLineasBorradas = 0;
        clear();

        nuevaPieza();
        timer.start();
    }

    private void pauseGame() {
        if (!empezada) {
            return;
        }

        pausa = !pausa;
        if (pausa) {
            timer.stop();
            son.cambiarEstado();
            estado.setText("Pausa");
        } else {
            timer.start();
            son = new SonidoFondo();
            son.start();
            estado.setText("En juego");
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) { //clase para dar color
        super.paint(g);
        Color col = new Color(255, 255, 255);//color gris
        g.drawLine(0, 20, 245, 20);//pinta una linea en la parte superior de la ventana
        Dimension size = getSize();//encapsula el tamñao de un objeto
        int parteSuperiorTablero = (int) size.getHeight() - altoTablero * alturaPieza();

        for (int i = 0; i < altoTablero; ++i) {
            for (int j = 0; j < anchoTablero; ++j) {
                PiezasTetris pieza = dimensionar(j, altoTablero - i - 1);
                if (pieza != PiezasTetris.NoPieza) {
                    pintarPiezas(g, 0 + j * anchoPieza(),
                            parteSuperiorTablero + i * alturaPieza(), pieza);
                }
            }
        }

        if (piezaActual.getPieza() != PiezasTetris.NoPieza) {
            for (int i = 0; i < 4; ++i) {
                int x = posicionX + piezaActual.x(i);
                int y = posicionY - piezaActual.y(i);
                pintarPiezas(g, 0 + x * anchoPieza(),
                        parteSuperiorTablero + (altoTablero - y - 1) * alturaPieza(),
                        piezaActual.getPieza());
            }
        }
    }

    private void BajarPiezaAlInstante() {//desplaza la pieza automaticamente a la parte inferior del tablero
        int newY = posicionY;
        while (newY > 0) {
            if (!Mover(piezaActual, posicionX, newY - 1)) {
                break;
            }
            --newY;
        }
        BajarPieza1posicion();
    }

    private void BajarPiezaRapido() {//baja la pieza a mas velocidad a la parte inferior del tablero
        if (!Mover(piezaActual, posicionX, posicionY - 1)) {
            BajarPieza1posicion();
        }
    }

    private void clear() {//dejar la pieza vacia
        for (int i = 0; i < altoTablero * anchoTablero; ++i) {
            piezas[i] = PiezasTetris.NoPieza;
        }
    }

    private void BajarPieza1posicion() {//hace que la pieza se desplace una posicion hacia abajo
        for (int i = 0; i < 4; ++i) {
            int x = posicionX + piezaActual.x(i);
            int y = posicionY - piezaActual.y(i);
            piezas[(y * anchoTablero) + x] = piezaActual.getPieza();
        }

        BorrarLineas();

        if (!finalizoQuitarFilas) {
            nuevaPieza();
        }
    }

    private void nuevaPieza() {
        contador++;
        System.out.println(contador);
        if (velocidad > 400) {
            if ((contador == 10) && (velocidad - 200 > 0)) {
                timer.stop();
                velocidad = velocidad - 200;
                timer = new Timer(velocidad, this);
                System.out.println(velocidad);
                timer.start();
            }
            if ((contador == 20) && (velocidad - 200 > 0)) {
                timer.stop();
                velocidad = velocidad - 200;
                timer = new Timer(velocidad, this);
                System.out.println(velocidad);
                timer.start();
            }
        }
        if (velocidad > 200) {
            if ((contador == 30) && (velocidad - 200 > 0)) {
                timer.stop();
                velocidad = velocidad - 200;
                timer = new Timer(velocidad, this);
                System.out.println(velocidad);
                timer.start();
            }
        }
        //muy rapido
        if ((contador == 100) && (velocidad - 100 > 0)) {
            timer.stop();
            velocidad = velocidad - 100;
            timer = new Timer(velocidad, this);
            System.out.println(velocidad);
            timer.start();
        }
//        //Demasiado RAPIDO
//        if ((contador == 110) && (velocidad - 50 > 0)) {
//            timer.stop();
//            velocidad = velocidad - 50;
//            timer = new Timer(velocidad, this);
//            System.out.println(velocidad);
//            timer.start();
//        }

        piezaActual.setPìezaAleatoria();
        posicionX = anchoTablero / 2;
        posicionY = altoTablero - 1 + piezaActual.minY();

        if (!Mover(piezaActual, posicionX, posicionY)) {
            piezaActual.establecerPieza(PiezasTetris.NoPieza);
            timer.stop();
            empezada = false;

            baseDatos.Insert insertar = new baseDatos.Insert();
            String nick;
            son.cambiarEstado();
            if (marcador.getText() != "0") {

                do {
                    nick = Pedir.pedirString("Game Over\nNick: ");
                } while ((nick.equalsIgnoreCase(" ")) || (nick.equalsIgnoreCase("")) || (nick.equalsIgnoreCase("  ")) || (nick.equalsIgnoreCase("   ")));
                try {
                    insertar.insert(INDEX, nick, marcador.getText());//insertar puntuacion en la base de datos
                } catch (ExcepcionSql e) {

                }
                metodos.CargarTabla.vaciarTabla(Interfaz.tabla);
                metodos.CargarTabla.mostrarTablas(Interfaz.tabla);
            } else {
                JOptionPane.showMessageDialog(null, "PARA QUE JUEGAS? (--_--)");
            }

            estado.setText("Game over");
//            
//        try {
//            crearMenu();
//        } catch (excepcionSql ex) {
//            ex.getMessage();
//        }
//            
        }
    }

    private boolean Mover(Piezas pieza, int newX, int newY) {
//mueve la pieza a la izquierda o a la derecha dependiendo de la tecla pulsada, suma o resta 1 a la posicion en x
        for (int i = 0; i < 4; ++i) {
            int x = newX + pieza.x(i);
            int y = newY - pieza.y(i);//evitamos poder "congelar" la pieza en el aire haciendola girar todo el rato
            if (x < 0 || x >= anchoTablero || y < 0 || y >= altoTablero) {
                return false;
            }
            if (dimensionar(x, y) != PiezasTetris.NoPieza) {
                return false;
            }
        }

        piezaActual = pieza;
        posicionX = newX;
        posicionY = newY;
        repaint();
        return true;
    }

    private void BorrarLineas() {//falta comentar
        int numeroLineasEnterasPorTurnos = 0;

        for (int i = altoTablero - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < anchoTablero; ++j) {
                if (dimensionar(j, i) == PiezasTetris.NoPieza) {
                    lineIsFull = false;//linea no completa
                    break;
                }
            }

            if (lineIsFull) {//linea completa
                ++numeroLineasEnterasPorTurnos;
                for (int k = i; k < altoTablero - 1; ++k) {
                    for (int j = 0; j < anchoTablero; ++j) {
                        piezas[(k * anchoTablero) + j] = dimensionar(j, k + 1);
                    }
                }
            }
        }

        if (numeroLineasEnterasPorTurnos > 0) {//si hay mas de una linea borra varias y suma el numero de lineas borradas a la puntuacion
            numLineasBorradas += numeroLineasEnterasPorTurnos;
            marcador.setText(String.valueOf(numLineasBorradas * 10));
            if ((velocidad <= 400) && (velocidad > 100)) {
                marcador.setText(String.valueOf(numLineasBorradas * 20));
            }
            if ((velocidad == 100)) {
                marcador.setText(String.valueOf(numLineasBorradas * 30));
            }
            finalizoQuitarFilas = true;
//            System.out.println(numeroLineasEnterasPorTurnos);
            if (numeroLineasEnterasPorTurnos==4){
//                pauseGame();
                Egg huevo = new Egg();
            }
            piezaActual.establecerPieza(PiezasTetris.NoPieza);
            repaint();

            sonido.SonidoBorrarLinea son = new sonido.SonidoBorrarLinea();
            son.start();
        }
    }

    private void pintarPiezas(Graphics g, int x, int y, PiezasTetris pieza) {
        Color colors[] = {//definimos todos los colores, cada color se corresponde a una pieza (mismo orden que en el enum)
            new Color(0, 0, 0),
            new Color(230, 0, 0),//rojo->rojo mas intenso Z
            new Color(60, 185, 50),//verde -> verde mas intenso s
            new Color(40, 230, 225),//azul claro->azul mas claro I
            new Color(165, 45, 135),//rosa -> morado T
            new Color(255, 215, 0),//azul oscuro->amarillo cuadrado
            new Color(60, 75, 195),//amarillo->ozul oscuro L
            new Color(255, 150, 0)//naranja-> naranja mas intensolespejo
        };

        Color color = colors[pieza.ordinal()];//obtenermos el color de la pieza

        g.setColor(color);
        g.fillRect(x + 1, y + 1, anchoPieza() - 2, alturaPieza() - 2);//fillRect permite pintar de color un rectangulo 

        g.setColor(color.brighter());//version mas brillante del color
        g.drawLine(x, y + alturaPieza() - 1, x, y);//Dibuja una línea, usando el color actual, entre los puntos (x1, y1) y (x2, y2)
        g.drawLine(x, y, x + anchoPieza() - 1, y);

        g.setColor(color.darker());//version mas oscura del color
        g.drawLine(x + 1, y + alturaPieza() - 1,
                x + anchoPieza() - 1, y + alturaPieza() - 1);
        g.drawLine(x + anchoPieza() - 1, y + alturaPieza() - 1,
                x + anchoPieza() - 1, y + 1);
    }

    class InteraccionTeclas extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (!empezada || piezaActual.getPieza() == PiezasTetris.NoPieza) {
                return;
            }

            int keycode = e.getKeyCode();

            if (keycode == 'p' || keycode == 'P') {
                pauseGame();//poner juego en pausa
                return;
            }

            if (pausa) {
                return;
            }

            switch (keycode) {
                case KeyEvent.VK_LEFT://izquierda
                    Mover(piezaActual, posicionX - 1, posicionY);
                    break;
                case KeyEvent.VK_RIGHT://derecha
                    Mover(piezaActual, posicionX + 1, posicionY);
                    break;
                case KeyEvent.VK_DOWN://bajar rapido
                    BajarPiezaRapido();
                    break;
                case KeyEvent.VK_UP://girar
                    Mover(piezaActual.Girar(), posicionX, posicionY);
                    break;
                case KeyEvent.VK_SPACE://bajar de golpe
                    BajarPiezaAlInstante();
                    break;
            }

        }
    }
}
