package Aleatorio;

public class Empleado_v2 {

    private int id;
    private String apellido;
    private int depto;
    private double salario;

    public Empleado_v2(int id, String apellido, int depto, double salario) {
        this.id = id;
        this.apellido = apellido;
        this.depto = depto;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado_v2-> " +
                "id: " + id +
                ", apellido='" + apellido + '\'' +
                ", depto=" + depto +
                ", salario=" + salario;
    }

}
