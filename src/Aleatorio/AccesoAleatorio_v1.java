package Aleatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class AccesoAleatorio_v1 {

    private static final Path rutaFichero = Path.of("aleatorios.dar");

    public static void main(String[] args) {

        escribirRegistros();
        leerRegistros();

    }

    private static void leerRegistros() {
    }

    private static void escribirRegistros() throws FileNotFoundException {
        String[] apellidos = {"Fernández", "Nuñez", "Gil"};
        int[] departamentos = {10, 20, 30};
        double[] salarios = {1000.45, 1100, 00, 25000, 00};

        try (RandomAccessFile raf = new RandomAccessFile(
                rutaFichero.toFile(),
                "rw"            //Modo de apertura: lectura y escritura si utilizamos "rw"
        )) {
            //Si quiero sobrescribir el fichero, coloco el puntero al inicio

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
