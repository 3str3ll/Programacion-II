package practica6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestamo {
    private String fechaPrestamo;
    private String fechaDevolucion;
    private Estudiante estudiante;
    private Libro libro;
    private boolean devuelto; 
    
    public Prestamo() {
        this.devuelto = false;
    }
    
    public Prestamo(Estudiante estudiante, Libro libro, String fechaPrestamo, String fechaDevolucion) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = false;
        System.out.println(" Prestamo creado: " + estudiante.getNombre() + " - " + libro.getTitulo());
    }
    
    public void marcarDevuelto() {
        this.devuelto = true;
        this.fechaDevolucion = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    
    public boolean isDevuelto() {
        return devuelto;
    }
    
    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
    
    public String getFechaPrestamo() { return fechaPrestamo; }
    public String getFechaDevolucion() { return fechaDevolucion; }
    public Estudiante getEstudiante() { return estudiante; }
    public Libro getLibro() { return libro; }
    
    public void setFechaPrestamo(String fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public void setFechaDevolucion(String fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public void setLibro(Libro libro) { this.libro = libro; }
    
    public void mostrarInfo() {
        System.out.println("=== Informacion del Prestamo ===");
        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Estudiante: " + estudiante.getNombre());
        System.out.println("Fecha prestamo: " + fechaPrestamo);
        System.out.println("Fecha devolucion: " + fechaDevolucion);
        System.out.println("Estado: " + (devuelto ? "DEVUELTO" : "PRESTADO"));
    }
    
    public String toString() {
        String estado = devuelto ? "DEVUELTO" : "PRESTADO";
        return estudiante.getNombre() + " - " + libro.getTitulo() + 
               " (" + fechaPrestamo + " a " + fechaDevolucion + ") - " + estado;
    }
}
