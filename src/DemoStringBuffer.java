public class DemoStringBuffer {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer("Me encanta");
        for (int i = 0; i < 100000; i++) {
            sb.append(" Acceso a Datos ");
        }
        System.out.println("DemoStringBuffer: " + (System.currentTimeMillis() - startTime) + "ms");


        //
        startTime = System.currentTimeMillis();
        StringBuilder sbuilder = new StringBuilder("Me encanta");
        for (int i = 0; i < 100000; i++) {
            sbuilder.append(" Acceso a Datos ");
        }
        System.out.println("StringBuilder: " + (System.currentTimeMillis() - startTime) + "ms");



        startTime = System.currentTimeMillis();
        String cadena = "Me encanta";
        for (int i = 0; i < 100000; i++) {
            cadena += "Acceso a Datos";
        }
        System.out.println("StringCadena: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}




