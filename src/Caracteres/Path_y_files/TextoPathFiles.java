package Caracteres.Path_y_files;
//uso de buffered I/O en Java para lectura y escritura archivos de texto

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

//Escribir y leer un fichero de texto (Lo guarda en memoria)

public class TextoPathFiles {
    //Cambiamos de String a Path
    private static final Path rutaFichero = Path.of("src/Caracteres/Path_y_files", "ficheroPathyFiles.txt");

    public static void main(String[] args) {

        escribirPathyFiles();
        leerPathyFiles();
    }

    private static void escribirPathyFiles() {
        List<String> lineas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            lineas.add("Fila numero: " + i);
        }
        try {
            Files.write(rutaFichero, lineas);
            System.out.println("Fichero escrito correctamente.");
        } catch (IOException e) {
            System.err.println("IOE generada en escribirPathyFiles: " + e.getMessage());
        }
    }

    private static void leerPathyFiles() {

        try {
            List<String> lineas = Files.readAllLines(rutaFichero);

            for (String elemento : lineas) {
                System.out.println(elemento);
            }
        } catch (IOException e) {
            System.err.println("IOE generada en escribirPathyFiles: " + e.getMessage());
        }
    }
}

