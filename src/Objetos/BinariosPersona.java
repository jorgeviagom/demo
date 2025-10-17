package Objetos;

import java.io.Serializable;

public class BinariosPersona implements Serializable {

    //ESTO ES PARA QUE NO DE ERROR AL SERIALIZAR LA CLASE (IMPLEMENTAR SERIALIZABLE)

    private static final long serialVersionUID = 1L;

    //ATRIBUTOS
    private final String nombre;
    private final int edad;

    //CONSTRUCTOR
    public BinariosPersona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    //GETTERS

    @Override
    public String toString() {
        return "DataStreams.BinariosPersona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
