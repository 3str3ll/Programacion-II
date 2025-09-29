package practica4.ej1;

public abstract class Empleado {
    protected String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public abstract double calcularSalarioMensual();

    @Override
    public String toString() {
        return "Nombre: " + nombre;
    }
    public String getNombre() {
        return nombre;
    }
}