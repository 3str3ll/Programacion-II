class Pagina {
    private int numero;
    private String contenido;

    public Pagina(int numero, String contenido) {
        this.numero = numero;
        this.contenido = contenido;
    }

    public void mostrarContenido() {
        System.out.println("Página " + numero + ": " + contenido);
    }

    public String toString() {
        return "Pág. " + numero + ": " + contenido.substring(0, Math.min(20, contenido.length())) + "...";
    }
}