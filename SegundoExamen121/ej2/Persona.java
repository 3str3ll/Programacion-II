package SegundoExamen121.ej2;
public class Persona {
    private String nombre;
    private int edad;
    private float pesoPersona;

    public Persona(String nombre, int edad, float peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.pesoPersona = peso;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public float getPesoPersona() { return pesoPersona; }

    public String toString() {
        return nombre + " (" + edad + " años, " + pesoPersona + " kg)";
    }
}








