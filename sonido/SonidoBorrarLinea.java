
package sonido;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SonidoBorrarLinea extends Thread {

    public void run() {
        try {
            Clip sonido = AudioSystem.getClip();
            File a = new File("C:\\Users\\Usuario\\Desktop\\Balloon.wav.wav");//pc casa
//            File a = new File("/home/local/DANIELCASTELAO/igonzaleziglesias/Escritorio/Balloon.wav");//pc clase
            sonido.open(AudioSystem.getAudioInputStream(a));
            sonido.start();
            Thread.sleep(200); // 200 milisegundos (0.2segundos)
            sonido.close();
        } catch (Exception tipoError) {
            System.out.println("" + tipoError);
        }
    }
}
