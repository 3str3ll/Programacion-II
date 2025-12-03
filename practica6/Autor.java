package practica6;
import java.util.ArrayList;

public class Autor {
    private String nombre;
    private String nacionalidad;

    public Autor() {
       
    }

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public void mostrarInfo() {
        System.out.println("Autor: " + nombre + " - Nacionalidad: " + nacionalidad);
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String toString() {
        return nombre + " (" + nacionalidad + ")";
    }
}
