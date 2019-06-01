package sonido;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SonidoFondo extends Thread {

    Clip sonido;
    boolean estado = true;

    public void run() {
        while (estado) {
            try {
                sonido = AudioSystem.getClip();
                File a = new File("C:\\Users\\Usuario\\Desktop\\tetris.wav");//pc casa
//            File a = new File("/home/local/DANIELCASTELAO/igonzaleziglesias/Escritorio/tetris.wav");//pc clase
                sonido.open(AudioSystem.getAudioInputStream(a));
                sonido.start();
                Thread.sleep(5000); // (30segundos)
                sonido.close();
            } catch (Exception tipoError) {
                System.out.println("" + tipoError);
            }
        }
    }

    public void cambiarEstado() {
        estado = false;
        sonido.close();
    }

}
