package practica6;

public class Horario {
    private String diasApertura;
    private String horaApertura;
    private String horaCierre;

    public Horario() {
       
    }

    public Horario(String diasApertura, String horaApertura, String horaCierre) {
        this.diasApertura = diasApertura;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public void mostrarHorario() {
        System.out.println("Horario de atencion: " + diasApertura + " de " +
                horaApertura + " a " + horaCierre);
    }

    public String getDiasApertura() {
        return diasApertura;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setDiasApertura(String diasApertura) {
        this.diasApertura = diasApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String toString() {
        return diasApertura + " " + horaApertura + "-" + horaCierre;
    }
}
