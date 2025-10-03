import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinariosFiles {
    public static void main(String[] args) {
        Path ruta = Path.of("fichBytesFile.dat");

        int i;


        try (
                OutputStream flujoSalida = Files.newOutputStream(ruta);
                InputStream flujoEntrada = Files.newInputStream(ruta)) {

            //Escribimos los numeros del 1 al 100 como bytes FICHBytes

            for (i = 1; i <= 100; i++) {
                flujoSalida.write(i);
            }
            while ((i = flujoEntrada.read()) != -1) {
                System.out.println(i);
            }

        } catch (IOException ioe) {
            System.out.println("Error de lectura " + ioe.getMessage());

        }
    }
}
