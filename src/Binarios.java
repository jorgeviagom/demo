
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class Binarios {
    public static void main(String[] args) {
    //ESCRIBIRFICHEROBYTES

    //RUTA
    Path ruta = Path.of("fichBytes.dat");

    int i;


    try(FileOutputStream flujoSalida = new FileOutputStream(ruta.toFile());
        FileInputStream flujoEntrada = new FileInputStream(ruta.toFile())){

        //Escribimos los numeros del 1 al 100 como bytes FICHBytes

        for (i = 1; i <= 100; i++){
            flujoSalida.write(i);
        }
        while((i = flujoEntrada.read()) != -1){
            System.out.println(i);
        }

    } catch (IOException ioe) {
        System.out.println("Error de lectura " + ioe.getMessage());

    }


        //Escribimos los numeros del 1 al 100 como bytes FICHBytesClaseFiles
        /*    Path ruta = Path.of("fichBytes.dat");

    int i;


    try(OutputStream flujoSalida = Files.newOutputStream(ruta);
        InputStream flujoEntrada = Files.newInputStream(ruta)){

        //Escribimos los numeros del 1 al 100 como bytes FICHBytes

        for (i = 1; i <= 100; i++){
            flujoSalida.write(i);
        }
        while((i = flujoEntrada.read()) != -1){
            System.out.println(i);
        }

    } catch (IOException ioe) {
        System.out.println("Error de lectura " + ioe.getMessage());

    }*/
    }
}
