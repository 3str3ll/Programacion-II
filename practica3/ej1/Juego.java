package practica3.ej1;

 public class Juego {
    protected int numeroDeVidas;
    protected int record;
    public Juego() {
        this.numeroDeVidas = 0;
        this.record = 0;
    }
    public void reiniciaPartida() {

    }
    public void actualizaRecord() {
        if (numeroDeVidas > record) {
            record = numeroDeVidas;
            System.out.println("¡Nuevo record!! "  );
        }
    }
    public boolean quitaVida() {
        numeroDeVidas--;
        return numeroDeVidas > 0;
    }
    public int getNumeroDeVidas() {
        return numeroDeVidas;
    }
    public void setNumeroDeVidas(int vidas) {
        this.numeroDeVidas = vidas;
    }
    public int getRecord() {
        return record;
    }
}
