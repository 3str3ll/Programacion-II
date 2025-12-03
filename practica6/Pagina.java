package practica6;

public class Pagina {
    private int numero;
    private String contenido;

    public Pagina() {
       
    }

    public Pagina(int numero, String contenido) {
        this.numero = numero;
        this.contenido = contenido;
    }

    public void mostrarContenido() {
        System.out.println("Pagina " + numero + ": " + contenido);
    }

    public int getNumero() {
        return numero;
    }

    public String getContenido() {
        return contenido;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String toString() {
        return "Pág. " + numero + ": " + contenido.substring(0, Math.min(20, contenido.length())) + "...";
    }
}
