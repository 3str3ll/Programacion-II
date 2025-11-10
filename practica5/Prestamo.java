public class Prestamo {
    private String fechaPrestamo;
    private String fechaDevolucion;
    private Estudiante estudiante; // ASOCIACIÓN
    private Libro libro; // ASOCIACIÓN

    public Prestamo(Estudiante estudiante, Libro libro, String fechaPrestamo, String fechaDevolucion) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        System.out.println("✓ Préstamo creado: " + estudiante.getNombre() + " - " + libro.getTitulo());
    }

    public void mostrarInfo() {
        System.out.println("=== Información del Préstamo ===");
        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Estudiante: " + estudiante.getNombre());
        System.out.println("Fecha préstamo: " + fechaPrestamo);
        System.out.println("Fecha devolución: " + fechaDevolucion);
    }