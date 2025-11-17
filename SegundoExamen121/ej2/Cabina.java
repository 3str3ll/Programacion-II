package SegundoExamen121.ej2;
import java.util.Scanner;
public class    Cabina {
    private int nroCabina;
    private Persona[] personasAbordo;

    public Cabina(int nroCabina) {
        this.nroCabina = nroCabina;
        this.personasAbordo = new Persona[10];
    }

    public int getNroCabina() { return nroCabina; }
    public Persona[] getPersonasAbordo() { return personasAbordo; }

    public boolean agregarPersona(Persona p) {
        for (int i = 0; i < personasAbordo.length; i++) {
            if (personasAbordo[i] == null) {
                if (calcularPesoActual() + p.getPesoPersona() > 850) {
                    return false;
                }
                personasAbordo[i] = p;
                return true;
            }
        }
        return false;
    }

    private float calcularPesoActual() {
        float pesoTotal = 0;
        for (Persona persona : personasAbordo) {
            if (persona != null) {
                pesoTotal = pesoTotal+ persona.getPesoPersona();
            }
        }
        return pesoTotal;
    }

    public int getCantidadPersonas() {
        int c = 0;
        for (Persona persona : personasAbordo) {
            if (persona != null) c++;
        }
        return c;
    }

    public boolean cumpleReglas() {
        return getCantidadPersonas() <= 10 && calcularPesoActual() <= 850;
    }

    public double calcularIngreso() {
        double total = 0;
        for (Persona persona : personasAbordo) {
            if (persona != null) {
                if (persona.getEdad() <= 25 || persona.getEdad() >= 60) {
                    total =total+ 1.5;
                } else {
                    total = total + 3.0;
                }
            }
        }
        return total;
    }

    public double calcularIngresoTarifaRegular() {
        double total = 0;
        for (Persona persona : personasAbordo) {
            if (persona != null && persona.getEdad() > 25 && persona.getEdad() < 60) {
                total =total+ 3.0;
            }
        }
        return total;
    }
}





