import java.util.ArrayList;

public class Biblioteca {
    private String nombre;
    private ArrayList<Libro> libros;
    private ArrayList<Autor> autores;
    private ArrayList<Prestamo> prestamosActivos;
    private Horario horario;
    public Biblioteca(String nombre, String diasApertura, String horaApertura, String horaCierre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamosActivos = new ArrayList<>();
        this.horario = new Horario(diasApertura, horaApertura, horaCierre);
        System.out.println(" Biblioteca '" + nombre + "' creada con horario: " + horario);
    }
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println(" Libro '" + libro.getTitulo() + "' agregado a la biblioteca");
    }
    public void agregarAutor(Autor autor) {
        autores.add(autor);
        System.out.println(" Autor '" + autor.getNombre() + "' registrado en la biblioteca");
    }
    public void prestarLibro(Estudiante estudiante, Libro libro, String fechaPrestamo, String fechaDevolucion) {
        Prestamo prestamo = new Prestamo(estudiante, libro, fechaPrestamo, fechaDevolucion);
        prestamosActivos.add(prestamo);
    }

    public void mostrarEstado() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ESTADO DE LA BIBLIOTECA: " + nombre);
        System.out.println("=".repeat(60));

        horario.mostrarHorario();

        System.out.println("\n--- Libros disponibles (" + libros.size() + ") ---");
        for (Libro libro : libros) {
            libro.mostrarInfo();
        }

        System.out.println("\n--- Autores registrados (" + autores.size() + ") ---");
        for (Autor autor : autores) {
            autor.mostrarInfo();
        }

        System.out.println("\n--- Préstamos activos (" + prestamosActivos.size() + ") ---");
        for (Prestamo prestamo : prestamosActivos) {
            prestamo.mostrarInfo();
            System.out.println();
        }
    }

    public void cerrarBiblioteca() {
        System.out.println("\n Cerrando biblioteca " + nombre + "...");
        prestamosActivos.clear();
    }
  

    public String toString() {
        return "Biblioteca: " + nombre + " (" + horario + ")";
    }
}