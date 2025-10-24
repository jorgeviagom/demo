package Aleatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;

public class AccesoAleatorio_v2 {

    private static final int LONG_APELLIDO = 20; //CARACTERES
    private static final int BYTES_INT = 4;
    private static final int BYTES_DOUBLE = 8;
    private static final int BYTES_CHAR = 2;
    private static final int TAM_REGISTRO = //PARA CALCULAR EL REGISTRO DE CADA LINEA
            BYTES_INT                                    //BYTES ID              4
                    + (LONG_APELLIDO * BYTES_CHAR)       //BYTES STRING          40
                    + BYTES_INT                          //BYTES DEPARTAMENTO    4
                    + BYTES_DOUBLE;                      //BYTES SALARIO         8
    //56 BYTES EN TOTAL


    //Ruta del fichero Path
    private static final Path rutaFichero = Path.of("src/Aleatorio/ficheros_prueba/aleatorios.dat"); //ruta relativa al proyecto empezando en src
    //Si fuese una ruta absoluta:
    //private static final Path rutaFichero = Path.of("D:/Users/jorge.viagom/IdeaProjects/demo/src/Aleatorio/ficheros_prueba/aleatorios.dat");

    public static void main(String[] args) throws IOException {
        try {

            if (rutaFichero.getParent() != null) Files.createDirectories(rutaFichero.getParent());

            escribirRegistros();

            leerRegistros();

            modificarDepartamento(1);

//            sumarSalarios();
//
//            leerRegistro(5); //Leer directamente el 5º registro y mostrarlo por consola

//            actualizarSalario(3); //Actualizar el salario del empleado 3


            leerRegistros();

        } catch (IOException e) {
            System.out.println("Error de IO en el main");
        }
    }

    private static void modificarDepartamento(int numeroEmpleado) {

        int departamento;
        try (Scanner sc = new Scanner(System.in);
             RandomAccessFile raf = new RandomAccessFile(
                     rutaFichero.toFile(),                                              //Convertimos la ruta Path a File
                     "rw"                                                             //Modo de apertura: lectura y escritura
             )) {
            System.out.print("Introduce el nuevo departamento: ");
            int nuevoDepartamento = sc.nextInt();

            //Coloco el puntero
            long puntero = (long) (numeroEmpleado - 1) * TAM_REGISTRO + (BYTES_INT + (LONG_APELLIDO * BYTES_CHAR)); //(long) casteamos a long
            raf.seek(puntero);
            raf.writeInt(nuevoDepartamento);

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void EscribirCadenaFija(RandomAccessFile raf,String ape, int longitud) throws IOException {

        if (ape == null) ape = "";
        StringBuilder sb = new StringBuilder(ape); //Creamos StringBuilder apartir del String
        //Si el apellido es mayor que el tamaño definido (20 caracteres)
        //Lo modificamo
        if (sb.length() > longitud) {
            sb.setLength(longitud);
        } else { //sino rellenamos con espacios hasta longitud
            while (sb.length() < longitud) {
                sb.append(' ');
            }

        }
        for (int i = 0; i < longitud; i++) {
                raf.writeChar(sb.charAt(i));
        }
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
                System.out.printf(Locale.US, "ID: %-3d, apellido: %-15s, departamento: %-2d, salario: %10.2f%n", //3 espacios -a alineado a la izquierda %n salto de linea
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
        String[] apellidos = {"Fernández", "Nuñez", "Gil", "Viana", "Dominguez", "Adarve"}; //String[] crea un array de cadenas "apellidos" y lo inicializa con tres valores: "Fernández", "Nuñez" y "Gil".
        int[] departamentos = {10, 20, 30, 20, 30, 10}; //int[] crea una tabla de int
        double[] salarios = {1000.45, 1100.00, 25000.00, 17500.75, 12000.00, 14005.05};
        try (RandomAccessFile raf = new RandomAccessFile( //Abrir el fichero en modo lectura y escritura
                rutaFichero.toFile(), //Convertimos la ruta Path a File
                "rw" //Modo de apertura: lectura y escritura si utilizamos "rw" // "r" seria solo lectura "w" solo escritura
        )) {
            //Si quiero sobrescribir el fichero, coloco el puntero al inicio
            raf.setLength(0); // "Borramos" el contenido del fichero
            //raf.seek(0); // Colocamos el puntero al inicio del fichero
            for (int i = 0; i < apellidos.length; i++) {
                raf.writeInt(i + 1); //Escribo el ID (entero 4 bytes)
                EscribirCadenaFija(raf,apellidos[i], LONG_APELLIDO); //Escribo el apellido (cadena de longitud variable)
                raf.writeInt(departamentos[i]); //
                raf.writeDouble(salarios[i]);
            }

        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }
    }
}

