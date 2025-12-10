package Persistencia.class1.ej1;
import java.io.*;

public class Medico {
    private int idMed;
    private String nombreMed;
    private String apellidoMed;
    private int aniosExperiencia;

    public Medico(int idMed, String nombreMed, String apellidoMed, int aniosExperiencia) {
        this.idMed = idMed;
        this.nombreMed = nombreMed;
        this.apellidoMed = apellidoMed;
        this.aniosExperiencia = aniosExperiencia;
    }

    public Medico() {
        this.idMed = -1;
        this.nombreMed = "";
        this.apellidoMed = "";
        this.aniosExperiencia = -1;
    }
    public void alta(String rutaCarpeta) {
        String ruta = rutaCarpeta + "/medico_" + idMed + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(String.valueOf(idMed));
            writer.newLine();
            writer.write(nombreMed);
            writer.newLine();
            writer.write(apellidoMed);
            writer.newLine();
            writer.write(String.valueOf(aniosExperiencia));
            System.out.println("Médico " + idMed + " guardado");
        } catch (IOException e) {
            System.out.println("Error al guardar médico: " + e.getMessage());
        }
    }
    public void cargar(String rutaArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            this.idMed = Integer.parseInt(reader.readLine());
            this.nombreMed = reader.readLine();
            this.apellidoMed = reader.readLine();
            this.aniosExperiencia = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error al cargar médico: " + e.getMessage());
        }
    }

    public void baja(String rutaCarpeta) {
        String ruta = rutaCarpeta + "/medico_" + idMed + ".txt";
        File archivo = new File(ruta);
        if (archivo.delete()) {
            System.out.println("Médico " + idMed + " eliminado");
        }
    }
    public int getIdMed() { return idMed; }
    public void setIdMed(int idMed) { this.idMed = idMed; }
    public String getNombreMed() { return nombreMed; }
    public void setNombreMed(String nombreMed) { this.nombreMed = nombreMed; }
    public String getApellidoMed() { return apellidoMed; }
    public void setApellidoMed(String apellidoMed) { this.apellidoMed = apellidoMed; }
    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }

    @Override
    public String toString() {
        return "Médico #" + idMed + ": Dr. " + nombreMed + " " + apellidoMed +
                " | Experiencia: " + aniosExperiencia + " años";
    }
}
