public class Autor {
    private String nombre;
    private String nacionalidad;

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

    public String toString() {
        return nombre + " (" + nacionalidad + ")";
    }
}