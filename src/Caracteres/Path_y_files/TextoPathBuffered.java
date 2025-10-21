package Caracteres.Path_y_files;
//uso de buffered I/O en Java para lectura y escritura archivos de texto

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextoPathBuffered {
    //Cambiamos de String a Path
    private static final Path rutaFichero = Path.of("src/Caracteres/Path_y_files", "ficheroPathBuffered.txt");

    public static void main(String[] args) {
        escribirPathBuffered();
        leerPathBuffered();
    }

    private static void escribirPathBuffered() {

        try (BufferedWriter bfw = Files.newBufferedWriter(rutaFichero)) {
            for (int i = 1; i <= 10; i++) {
                bfw.write("Fila numero: " + i + "\n");
                bfw.newLine();
            }
        } catch (IOException e) {
            System.err.println("IOE generada en escribirPathBuffered: " + e.getMessage());
        }
    }

    private static void leerPathBuffered() {

        try (BufferedReader bfr = Files.newBufferedReader(rutaFichero)) {
            String linea;
            while ((linea = bfr.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.err.println("IOE generada en escribirPathBuffered: " + e.getMessage());
        }
    }
}

