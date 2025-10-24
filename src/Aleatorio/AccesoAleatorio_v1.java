package Aleatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Locale;

public class AccesoAleatorio_v1 {
    //Ruta del fichero Path
    private static final Path rutaFichero = Path.of("src/Aleatorio/aleatorios.dat"); //ruta relativa al proyecto empezando en src
    //Si fuese una ruta absoluta:
    //private static final Path rutaFichero = Path.of("D:/Users/jorge.viagom/IdeaProjects/demo/src/Aleatorio/aleatorios.dat");

    public static void main(String[] args) {

        escribirRegistros();
        leerRegistros();

    }

    private static void leerRegistros() {

        int id, departamento;
        String apellido;
        double salario;

        try (RandomAccessFile raf = new RandomAccessFile(
                rutaFichero.toFile(), //Convertimos la ruta Path a File
                "r" //Modo de apertura: solo lectura
        )) {
            //Nos colocamos al inicio del fichero
            raf.seek(0); //Colocamos el puntero al inicio del fichero
            //long puntero = 0;
            long puntero = raf.getFilePointer(); //Variable para controlar la posición del puntero en el fichero
            while (puntero != raf.length()) {
                id = raf.readInt();
                apellido = raf.readUTF();
                departamento = raf.readInt();
                salario = raf.readDouble();
                System.out.printf(Locale.US,"ID: %-3d, apellido: %-15s, departamento: %-2d, salario: %10.2f%n", //3 espacios -a alineado a la izquierda %n salto de linea
                        id, apellido, departamento, salario);
                //id = 4b int
                //apellido = cada letra *2 String
                //dept = 4 int
                //salario = 8 double
                puntero = raf.getFilePointer(); //Actualizamos la posicion del puntero

            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirRegistros() {
        String[] apellidos = {"Fernández", "Nuñez", "Gil"}; //String[] crea un array de cadenas "apellidos" y lo inicializa con tres valores: "Fernández", "Nuñez" y "Gil".
        int[] departamentos = {10, 20, 30}; //int[] crea una tabla de int
        double[] salarios = {1000.45, 1100.00, 25000.00};
        try (RandomAccessFile raf = new RandomAccessFile( //Abrir el fichero en modo lectura y escritura
                rutaFichero.toFile(), //Convertimos la ruta Path a File
                "rw" //Modo de apertura: lectura y escritura si utilizamos "rw" // "r" seria solo lectura "w" solo escritura
        )) {
            //Si quiero sobrescribir el fichero, coloco el puntero al inicio
            raf.setLength(0); // "Borramos" el contenido del fichero
            //raf.seek(0); // Colocamos el puntero al inicio del fichero
            for (int i = 0; i < apellidos.length; i++) {
                raf.writeInt(i + 1); //Escribo el ID (entero 4 bytes)
                raf.writeUTF(apellidos[i]); //Escribo el apellido (cadena de longitud variable)
                raf.writeInt(departamentos[i]); //
                raf.writeDouble(salarios[i]);
            }

        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }
    }
}

