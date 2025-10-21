package Caracteres.PrintWritter_Scanner;
//uso de buffered I/O en Java para lectura y escritura archivos de texto

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class TextoPrintWritterScanner {
    //Cambiamos de String a Path
    private static final Path rutaFichero = Path.of("src/Caracteres/PrintWritter_Scanner", "ficheroPrintWritter.txt");

    public static void main(String[] args) {
        escribirPrintWritter();
        leerConScanner();
    }

    private static void escribirPrintWritter() {

        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(rutaFichero))) {
            for (int i = 1; i <= 10; i++) {
//                pw.write("Fila numero: " + i + "\n");
//                pw.newLine();
                pw.println("Fila numero: " + i);
            }
            System.out.println("Fichero escrito correctamente.");
        } catch (IOException e) {
            System.err.println("IOE generada en escribirPrintWritterescribirPathBuffered: " + e.getMessage());
        }
    }

    private static void leerConScanner() {

        try (Scanner sc = new Scanner(Files.newBufferedReader(
                rutaFichero,
                StandardCharsets.UTF_8
        ))) {
            String linea;
            while (sc.hasNextLine()) {
                linea = sc.nextLine();
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.err.println("IOE generada en escribirPrintWritterescribirPathBuffered: " + e.getMessage());
        }
    }
}

