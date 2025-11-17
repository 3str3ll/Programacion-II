package SegundoExamen121.ej2;

public class MiTeleferico {
    private Linea[] lineas;
    private float cantidadIngresos;

    public MiTeleferico() {
        this.lineas = new Linea[3];
        this.lineas[0] = new Linea("Amarillo");
        this.lineas[1] = new Linea("Rojo");
        this.lineas[2] = new Linea("Verde");
        this.cantidadIngresos = 0;
    }

    public Linea[] getLineas() { return lineas; }
    public float getCantidadIngresos() { return cantidadIngresos; }

    public void agregarPersonaFila(Persona p, String linea) {
        for (Linea l : lineas) {
            if (l.getColor().equals(linea)) {
                l.agregarPersona(p);

            }
        }
    }

    public void agregarCabina(String linea) {
        for (Linea l : lineas) {
            if (l.getColor().equals(linea)) {
                int nuevoNumero = l.getCantidadCabinas() + 1;
                l.agregarCabina(new Cabina(nuevoNumero));

            }
        }
    }

    // a)
    public void agregarPrimeraPersonaACabina(int nroCabina, String linea) {
        for (Linea l : lineas) {
            if (l.getColor().equalsIgnoreCase(linea)) {
                l.agregarPrimeraPersonaACabina(nroCabina);

            }
        }
    }

    // b)
    public boolean verificarReglasTodasLineas() {
        for (Linea linea : lineas) {
            if (!linea.verificarReglasCabinas()) {
                return false;
            }
        }
        return true;
    }

    // c)
    public double calcularIngresoTotal() {
        double total = 0;
        for (Linea linea : lineas) {
            total += linea.calcularIngreso();
        }
        cantidadIngresos = (float) total;
        return total;
    }

    // d)
    public void mostrarLineaConMasIngresoRegular() {
        Linea lineaMax = null;
        double maxIngreso = -1;

        for (Linea linea : lineas) {
            double ingreso = linea.calcularIngresoTarifaRegular();
            if (ingreso > maxIngreso) {
                maxIngreso = ingreso;
                lineaMax = linea;
            }
        }

        if (lineaMax != null) {
            System.out.println("Línea con más ingreso (tarifa regular): " +
                    lineaMax.getColor() + " - Bs. " + maxIngreso);
        }
    }
}
