import com.sun.jdi.PathSearchingVirtualMachine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinariosData {
    public static void main(String[] args) {
        //Si no pongo barra empieza desde la raiz del proyecto "demo"
    Path ruta = Path.of("personas.dat");

    escribirPersonas(ruta);
    leerPersonas(ruta);
    }

    private static void leerPersonas(Path ruta) {
        try(InputStream flujoLectura = Files.newInputStream(ruta);
            DataInputStream dis = new DataInputStream(flujoLectura)){
            while(true){
                try{
                    String nombres = dis.readUTF();
                    int edades = dis.readInt();
                                                            // %n hace un salto de linea o \n
                    System.out.printf("Nombre: %s, edad: %d%n", nombres, edades);

                }catch (EOFException ioe){
                    break;
                }
            }
        } catch (Exception ioe) {
            System.err.println("Error en leer " + ioe.getMessage());
        }

    }

    private static void escribirPersonas(Path ruta) {
        //Creamos una tabla de String "nombres" y  int "edades" con valores dentro
        String[] nombres = {"Oscar", "Jorge", "Miguel", "Dani", "Patri"};
        int [] edades = {26, 32, 34, 33,31};

    try (OutputStream flujoEscritura = Files.newOutputStream(ruta);
         DataOutputStream dos = new DataOutputStream(flujoEscritura)) {
        for (int i = 0; i < nombres.length; i++) {
            dos.writeUTF(nombres[i]);
            dos.writeInt(edades[i]);
        }

        System.out.println("Datos escritos correctamente en: " + ruta.toAbsolutePath());

    } catch (IOException e) {
        System.err.println("Error de escritura. " + e.getMessage());;
    }
    }


}
