
package libreria;

import javax.swing.JOptionPane;

public class Pedir {//Libreria

    public static int pedirInt(String msg) {
        return Integer.parseInt(JOptionPane.showInputDialog(msg));
    }

    public static float pedirFloat(String msg) {
        return Float.parseFloat(JOptionPane.showInputDialog(msg));
    }

    public static String pedirString(String msg) {
        return JOptionPane.showInputDialog(msg);
    }

}
