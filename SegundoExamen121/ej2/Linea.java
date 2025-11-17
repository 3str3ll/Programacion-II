package SegundoExamen121.ej2;

public class Linea {
    private String color;
    private Persona[] filaPersonas;
    private Cabina[] cabinas;
    private int cantidadCabinas;

    public Linea(String color) {
        this.color = color;
        this.filaPersonas = new Persona[100];
        this.cabinas = new Cabina[20];
        this.cantidadCabinas = 0;
    }

    public String getColor() { return color; }
    public Persona[] getFilaPersonas() {
        return filaPersonas; }
    public Cabina[] getCabinas() {
        return cabinas; }
    public int getCantidadCabinas() {
        return cantidadCabinas; }

    public void agregarPersona(Persona p) {
        for (int i = 0; i < filaPersonas.length; i++) {
            if (filaPersonas[i] == null) {
                filaPersonas[i] = p;
                break;
            }
        }
    }

    public void agregarCabina(Cabina cabina) {
        if (cantidadCabinas < cabinas.length) {
            cabinas[cantidadCabinas] = cabina;
            cantidadCabinas++;
        }
    }

    // a)
    public boolean agregarPrimeraPersonaACabina(int nroCabina) {
        Persona primeraPersona = null;
        int primeraPosicion = -1;

        for (int i = 0; i < filaPersonas.length; i++) {
            if (filaPersonas[i] != null) {
                primeraPersona = filaPersonas[i];
                primeraPosicion = i;
                break;
            }
        }

        if (primeraPersona == null) {
            return false;
        }
        Cabina cabina = null;
        for (int i = 0; i < cantidadCabinas; i++) {
            if (cabinas[i].getNroCabina() == nroCabina) {
                cabina = cabinas[i];
                break;
            }
        }

        if (cabina == null) {
            return false;
        }
        boolean agregado = cabina.agregarPersona(primeraPersona);
        if (agregado) {
            filaPersonas[primeraPosicion] = null;
            for (int i = primeraPosicion; i < filaPersonas.length - 1; i++) {
                filaPersonas[i] = filaPersonas[i + 1];
            }
            filaPersonas[filaPersonas.length - 1] = null;
        }
        return agregado;
    }

    public boolean verificarReglasCabinas() {
        for (int i = 0; i < cantidadCabinas; i++) {
            if (!cabinas[i].cumpleReglas()) {
                return false;
            }
        }
        return true;
    }

    public double calcularIngreso() {
        double total = 0;
        for (int i = 0; i < cantidadCabinas; i++) {
            total += cabinas[i].calcularIngreso();
        }
        return total;
    }

    public double calcularIngresoTarifaRegular() {
        double total = 0;
        for (int i = 0; i < cantidadCabinas; i++) {
            total += cabinas[i].calcularIngresoTarifaRegular();
        }
        return total;
    }
}
