public class SistemaBiblioteca {
    public static void main(String[] args) {
        System.out.println("SISTEMA BIBLIOTECA \n");

        Biblioteca biblioteca = new Biblioteca("Biblioteca Central", "L-V", "08:00", "20:00");

        Autor autor1 = new Autor("Gabriel García Márquez", "Colombiano");
        Autor autor2 = new Autor("Jorge Luis Borges", "Argentino");
        String[] paginasLibro1 = {"Página 1 contenido...", "Página 2 contenido..."};
        String[] paginasLibro2 = {"Página 1 ficción...", "Página 2 ficción..."};

        Libro libro1 = new Libro("Cien Años de Soledad", "978-001", paginasLibro1);
        Libro libro2 = new Libro("Ficciones", "978-002", paginasLibro2);

        Estudiante est1 = new Estudiante("2023001", "María González");
        Estudiante est2 = new Estudiante("2023002", "Carlos Pérez");

        biblioteca.agregarAutor(autor1);
        biblioteca.agregarAutor(autor2);
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.prestarLibro(est1, libro1, "2025-01-15", "2025-02-15");
        biblioteca.prestarLibro(est2, libro2, "2025-01-16", "2025-02-16");
        biblioteca.mostrarEstado();
        System.out.println("\n--- DEMOSTRACIÓN RELACIONES ---");
        libro1.leer();
        System.out.println(" Autor existe fuera: " + autor1.getNombre());
        System.out.println(" Libro existe fuera: " + libro1.getTitulo());

        biblioteca.cerrarBiblioteca();
    }
}