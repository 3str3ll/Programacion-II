package practica6;

import java.util.ArrayList;

public class Libro {
    private String titulo;
    private String isbn;
    private ArrayList<Pagina> paginas;

    public Libro() {
        this.paginas = new ArrayList<>();
    }

    public Libro(String titulo, String isbn, String[] contenidosPaginas) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.paginas = new ArrayList<>();
        if (contenidosPaginas != null) {
            for (int i = 0; i < contenidosPaginas.length; i++) {
                paginas.add(new Pagina(i + 1, contenidosPaginas[i]));
            }
        }
        System.out.println(" Libro '" + titulo + "' creado con " + paginas.size() + " paginas");
    }

    public void leer() {
        System.out.println("\n=== Leyendo libro: " + titulo + " ===");
        for (Pagina pagina : paginas) {
            pagina.mostrarContenido();
        }
    }

    public void mostrarInfo() {
        System.out.println("Libro: " + titulo + " (ISBN: " + isbn + ") - " + paginas.size() + " paginas");
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<Pagina> getPaginas() {
        return paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPaginas(ArrayList<Pagina> paginas) {
        this.paginas = paginas;
    }

    public void agregarPagina(Pagina pagina) {
        paginas.add(pagina);
    }

    public String toString() {
        return titulo + " (" + isbn + ")";
    }
}
