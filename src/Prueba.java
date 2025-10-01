import java.io.File;
import java.io.IOException;

public class Prueba {
    public static void main(String[] args) {
        System.out.println("!Hello World!");
        File fichero = new File("d:\\Prueba\\miFichero.docx");
        try{
            if(fichero.createNewFile()){
                System.out.println("El fichero se ha creado correctamente.");
            }else{
                System.out.println("El fichero ya existe");
            }
        } catch(IOException ioe){
            System.out.println("Se ha producido un error ");
        }
    }
}
