package Persistencia.class1.ej1;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.*;
public class  class Medico {
    private int idMed;
    private String nombreMed;
    private String apellidoMed;
    private int aniosExperiencia;

    public Medico(int idMed, String nombreMed, String apellidoMed, int aniosExperiencia) {
        this.idMed = idMed;
        this.nombreMed = nombreMed;
        this.apellidoMed = apellidoMed;
        this.aniosExperiencia = aniosExperiencia;
        System.out.println(" Médico '" + nombreMed + " " + apellidoMed + "' registrado");
    }

    public void mostrarInfo() {
        System.out.println("Dr. " + nombreMed + " " + apellidoMed +
                " - ID: " + idMed + " - Experiencia: " + aniosExperiencia + " años");
    }

    public String getNombreCompleto() {
        return nombreMed + " " + apellidoMed;
    }

    // Getters para Gson
    public int getIdMed() { return idMed; }
    public String getNombreMed() { return nombreMed; }
    public String getApellidoMed() { return apellidoMed; }
    public int getAniosExperiencia() { return aniosExperiencia; }

    @Override
    public String toString() {
        return "Dr. " + nombreMed + " " + apellidoMed + " (ID: " + idMed + ")";
    }
}
