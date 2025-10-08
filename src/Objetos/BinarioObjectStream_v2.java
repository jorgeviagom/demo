package Objetos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class BinarioObjectStream_v2 {

    private static final Path RUTA = Path.of("personas_objetos.dat");

    public static void main(String[] args) {

        escribirPersonas();
        leerPersonas();
        anadirRegistro(new BinariosPersona("Miriam", 32));
        anadirRegistro(new BinariosPersona("Julian", 72));
        System.out.println("\nEstamos despues de añadir 2 personas");
        leerPersonas();
    }

    private static void anadirRegistro(BinariosPersona persona) {
        boolean existe = Files.exists(RUTA);
        try (OutputStream os = Files.newOutputStream(
                RUTA,
                StandardOpenOption.APPEND);
                ObjectOutputStream oos = existe
                        ? new ObjetcOutputStreamSinCabecera(os)
                        : new ObjectOutputStream(os)) {
            oos.writeObject(persona);
            System.out.println(" + añadida persona: " + persona);
        } catch (IOException ioe){
            System.err.println("Error al añadir registro. " + ioe.getMessage());

        }
    }

    private static void leerPersonas() {
        try (InputStream is = Files.newInputStream(RUTA);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            int contador = 1;
            while (true) {
                try {
                    BinariosPersona p = (BinariosPersona) ois.readObject();
                    System.out.printf("Persona => %s%n", contador++, p);
                } catch (EOFException e) {
                    System.out.println("Fin del fichero. ");
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer objetos. " + e.getMessage());;
        }
    }
    private static void escribirPersonas() {
        List<BinariosPersona> personas = new ArrayList<>();
        personas.add(new BinariosPersona("Oscar", 26));
        personas.add(new BinariosPersona("Jorge", 32));
        personas.add(new BinariosPersona("Miguel", 34));
        personas.add(new BinariosPersona("Dani", 33));
        personas.add(new BinariosPersona("Patri", 31));
        personas.add(new BinariosPersona("Antonio", 14));
        personas.add(new BinariosPersona("Maria Jesus", 13));

        try (OutputStream os = Files.newOutputStream(RUTA);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            int contador = 1;

            // RECORREMOS EL ARRAYLIST PARA ESCRIBIR PERSONA A PERSONA
            for (BinariosPersona p : personas) {
                oos.writeObject(p);
                System.out.printf("GRABO LOS DATOS DE LA PERSONA %d %n", contador++); //++contador preincremento del contador
                // equivale a contador = contador + 1 tambien se puede poner en la siguiente linea contador++;
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo objetos: " + e.getMessage());
            ;
        }
    }

    private static class ObjetcOutputStreamSinCabecera extends ObjectOutputStream {

        public ObjetcOutputStreamSinCabecera(OutputStream out) throws IOException {
            super(out);
        }
        @Override
        protected void writeStreamHeader() throws IOException {
            //NO HACEMOS NADA PORQUE NO QUEREMOS CABECERA
        }
    }
    }


