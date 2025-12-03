package practica6;
import java.util.ArrayList;

public class Biblioteca {
    private String nombre;
    private ArrayList<Libro> libros;
    private ArrayList<Autor> autores;
    private ArrayList<Prestamo> prestamos; 
    private ArrayList<Prestamo> historialPrestamos; 
    private Horario horario;

    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.historialPrestamos = new ArrayList<>();
    }
    
    public Biblioteca(String nombre, String diasApertura, String horaApertura, String horaCierre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.historialPrestamos = new ArrayList<>();
        this.horario = new Horario(diasApertura, horaApertura, horaCierre);
    }

    
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }
    
    public void editarLibro(int index, Libro libroEditado) {
        if (index >= 0 && index < libros.size()) {
            libros.set(index, libroEditado);
        }
    }
    
    public void eliminarLibro(int index) {
        if (index >= 0 && index < libros.size()) {
           
            if (!estaLibroPrestado(libros.get(index))) {
                libros.remove(index);
            }
        }
    }
    public boolean estaLibroDisponible(Libro libro) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().equals(libro) && !prestamo.isDevuelto()) {
                return false; 
            }
        }
        return true; 
    }
    
    public boolean estaLibroPrestado(Libro libro) {
        return !estaLibroDisponible(libro);
    }
    
    
    public ArrayList<Libro> getLibrosDisponibles() {
        ArrayList<Libro> disponibles = new ArrayList<>();
        for (Libro libro : libros) {
            if (estaLibroDisponible(libro)) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }
 
    public ArrayList<Libro> getLibrosPrestados() {
        ArrayList<Libro> prestados = new ArrayList<>();
        for (Libro libro : libros) {
            if (estaLibroPrestado(libro)) {
                prestados.add(libro);
            }
        }
        return prestados;
    }
    
    // Buscar libro por título
    public ArrayList<Libro> buscarLibrosPorTitulo(String busqueda) {
        ArrayList<Libro> resultados = new ArrayList<>();
        String busquedaLower = busqueda.toLowerCase();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(busquedaLower)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }
    
    
    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }
    
    public void editarAutor(int index, Autor autorEditado) {
        if (index >= 0 && index < autores.size()) {
            autores.set(index, autorEditado);
        }
    }
    
    public void eliminarAutor(int index) {
        if (index >= 0 && index < autores.size()) {
            autores.remove(index);
        }
    }
    
   
    
    public void prestarLibro(Estudiante estudiante, Libro libro, String fechaPrestamo, String fechaDevolucion) {
        if (estaLibroDisponible(libro)) {
            Prestamo prestamo = new Prestamo(estudiante, libro, fechaPrestamo, fechaDevolucion);
            prestamos.add(prestamo);
            historialPrestamos.add(prestamo);
        }
    }
    
    public void devolverLibro(Prestamo prestamo) {
        prestamo.marcarDevuelto();
        // Se mantiene en prestamos para historial, pero marcado como devuelto
    }
    
    public ArrayList<Prestamo> getPrestamosActivos() {
        ArrayList<Prestamo> activos = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            if (!prestamo.isDevuelto()) {
                activos.add(prestamo);
            }
        }
        return activos;
    }
    
    public ArrayList<Prestamo> getHistorialPrestamos() {
        return new ArrayList<>(historialPrestamos);
    }
    
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public ArrayList<Libro> getLibros() { return libros; }
    public void setLibros(ArrayList<Libro> libros) { this.libros = libros; }
    
    public ArrayList<Autor> getAutores() { return autores; }
    public void setAutores(ArrayList<Autor> autores) { this.autores = autores; }
    
    public ArrayList<Prestamo> getPrestamos() { return prestamos; }
    public void setPrestamos(ArrayList<Prestamo> prestamos) { this.prestamos = prestamos; }
    
    public ArrayList<Prestamo> getHistorialPrestamosList() { return historialPrestamos; }
    public void setHistorialPrestamos(ArrayList<Prestamo> historialPrestamos) { this.historialPrestamos = historialPrestamos; }
    
    public Horario getHorario() { return horario; }
    public void setHorario(Horario horario) { this.horario = horario; }
    
    public String toString() {
        return "Biblioteca: " + nombre + " (" + horario + ")";
    }
}