package tetris;

import java.util.Random;
import java.lang.Math;

public class Piezas {

    enum PiezasTetris {//6 piezas relacionadas por forma con letras y una pieza"vacia"
        NoPieza, PiezaZ, PiezaS, PiezaLinea,
        PiezaT, PiezaCuadrado, PiezaL, PiezaLEspejo
    };

    private PiezasTetris PiezaActual;
    private int coords[][];
    private int[][][] coordsTable;//valor del enum de la pieza/cordenada x/ cordenada y

    public Piezas() {
        coords = new int[4][2];
        establecerPieza(PiezasTetris.NoPieza);//por defecto sin pieza

    }

    public void establecerPieza(PiezasTetris pieza) {

        coordsTable = new int[][][]{//la matriz contiene todos los valores de coordenadas posibles de las piezas en una posicion por defecto(posicion inicial). 
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
            {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
            {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
            {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
            {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
            {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
            {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[pieza.ordinal()][i][j];

            }
        }
        PiezaActual = pieza;

    }

    private void setX(int index, int x) {
        coords[index][0] = x;
    }

    private void setY(int index, int y) {
        coords[index][1] = y;
    }

    public int x(int index) {
        return coords[index][0];
    }

    public int y(int index) {
        return coords[index][1];
    }

    public PiezasTetris getPieza() {
        return PiezaActual;
    }

    public void setPìezaAleatoria() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;//numero aleatorio entre 1-7
        switch (x) {
            case 1:
                System.out.println("piezaZ");
                break;
            case 2:
                System.out.println("piezaS");
                break;
            case 3:
                System.out.println("piezaLinea");
                break;
            case 4:
                System.out.println("piezaT");
                break;
            case 5:
                System.out.println("piezaCuadrado");
                break;
            case 6:
                System.out.println("piezaL");
                break;
            case 7:
                System.out.println("piezaLEspejo");
                break;

        }

        PiezasTetris[] values = PiezasTetris.values();//valor de la pieza en el enum
        establecerPieza(values[x]);
    }

    public int minX() {
        int m = coords[0][0];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][0]);
        }
        return m;
    }

    public int minY() {
        int m = coords[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }

    public Piezas Girar() {//girar la pieza
        if (PiezaActual == PiezasTetris.PiezaCuadrado) {
            return this;
        }

        Piezas result = new Piezas();
        result.PiezaActual = PiezaActual;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }

}
