import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un numero: ");
        int numero = sc.nextInt();
        System.out.println("El factorial de " + numero + " es " + factorial(numero));

    }

    //CREAMOS EL METODO FACTORIAL
    public static int factorial(int num) {
        if (num == 0) { //CONDICION DE SALIDA
            return 1;
        } else {
            return num * factorial(num-1); //LLAMADA AL MISMO TIEMPO
        }
    }
}
