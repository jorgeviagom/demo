import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        countdown(10);

        }

        //CREAMOS EL METODO COUNTDOWN cuenta atras
        public static void countdown(int num){
        if (num == 0 ){
            System.out.println("¡Tiempo!");
        }else {
            System.out.println(num);
            countdown(num-1);
        }
    }
}
