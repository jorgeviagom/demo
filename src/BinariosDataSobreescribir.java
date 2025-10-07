import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class BinariosDataSobreescribir {

    private static final Path RUTA = Path.of("personas.dat");

    public static void main(String[] args) {

        escribirPersonas();
        leerPersonas();
        añadirRegistro("Antonio",14);
        añadirRegistro("Maria Jesus", 13);
        leerPersonas();


        }

    private static void añadirRegistro(String nombre, int edad) {
        try (OutputStream flujoEscritura = Files.newOutputStream(
                RUTA,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
             DataOutputStream dos = new DataOutputStream(flujoEscritura)) {

                dos.writeUTF(nombre);
                dos.writeInt(edad);

            System.out.printf("Añadido => nombre: %s, edad: %d%n", nombre, edad);

        } catch (IOException ioe) {
            System.err.println("Error al añadir registro. " + ioe.getMessage());
        }
    }

    private static void leerPersonas() {
            try(InputStream flujoLectura = Files.newInputStream(RUTA);
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

        private static void escribirPersonas() {
            //Creamos una tabla de String "nombres" y  int "edades" con valores dentro
            String[] nombres = {"Oscar", "Jorge", "Miguel", "Dani", "Patri"};
            int [] edades = {26, 32, 34, 33,31};

            try (OutputStream flujoEscritura = Files.newOutputStream(RUTA);
                 DataOutputStream dos = new DataOutputStream(flujoEscritura)) {
                for (int i = 0; i < nombres.length; i++) {
                    dos.writeUTF(nombres[i]);
                    dos.writeInt(edades[i]);
                }

                System.out.println("Datos escritos correctamente en: " + RUTA.toAbsolutePath());

            } catch (IOException e) {
                System.err.println("Error de escritura. " + e.getMessage());
            }
        }
    }


