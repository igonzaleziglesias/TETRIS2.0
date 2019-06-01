package niveles;

import javax.swing.JOptionPane;

public class ElegirNivel {

    public static int nivel() {
        int tiempo = 0;
        do {
            String[] elecciones = {"Facil", "Intermedio", "Dificil"};//mensaje final de partida
            //implementar aqui insercion en base de datos
            int eleccion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccionar Nivel  (^▽^)",
                    null,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    elecciones,
                    null
            );

            switch (eleccion) {

                case 0:
                    tiempo = 800;
                    System.out.println(tiempo);
                    break;
                case 1:
                    tiempo = 400;
                    System.out.println(tiempo);
                    break;
                case 2:
                    tiempo = 100;
                    System.out.println(tiempo);
                    break;
            }
        } while (tiempo == 0);
        return tiempo;
    }
}
