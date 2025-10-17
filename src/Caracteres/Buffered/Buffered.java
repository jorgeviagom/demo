package Caracteres.Buffered;

import java.io.*;

public class Buffered {

    private static final String rutaFichero = "ficheroBuffered.txt";

    public static void main(String[] args) {

        escribirBuffered();
        leerBuffered();

    }

    private static void escribirBuffered() {

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(rutaFichero))){
            for (int i = 1; i <= 10; i++) {
                bfw.write("Fila numero: " + i + "\n");
                //bfw.nextline;
            }
        } catch(IOException e) {
            System.err.println("IOE generada en escribirBuffered: " + e.getMessage());
        }
    }
    private static void leerBuffered() {

        try (BufferedReader bfr = new BufferedReader(new FileReader(rutaFichero))){
            String linea;
            while((linea = bfr.readLine()) != null) {
                System.out.println(linea);
            }
        } catch(Exception e){
            System.err.println("IOE generada en escribirBuffered: " + e.getMessage());
        }
    }
}




