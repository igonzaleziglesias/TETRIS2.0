package tetris;

import excepciones.ExcepcionSql;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static tetris.Menu.crearMenu;

public class App {

    static int INDEX;

    public App() {

        try {
            crearMenu();
        } catch (ExcepcionSql ex) {
            ex.getMessage();
        }

    }

}
