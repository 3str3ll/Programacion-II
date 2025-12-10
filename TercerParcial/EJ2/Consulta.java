package TercerParcial.EJ2;
import com.google.gson.Gson;
import java.io.*;

public class Consulta {
    private int ci;
    private String nombrePaciente;
    private String apellidoPaciente;
    private int idMed;
    private int dia;
    private String mes;
    private int anio;

    public Consulta(int ci, String nombrePaciente, String apellidoPaciente,
                    int idMed, int dia, String mes, int anio) {
        this.ci = ci;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.idMed = idMed;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public Consulta() {
        this.ci = -1;
        this.nombrePaciente = "";
        this.apellidoPaciente = "";
        this.idMed = -1;
        this.dia = -1;
        this.mes = "";
        this.anio = -1;
    }
    public void alta(String rutaCarpeta) {
        String ruta = rutaCarpeta + "/consulta_" + ci + ".json";
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(this, writer);
            System.out.println("Consulta " + ci + " guardada");
        } catch (IOException e) {
            System.out.println("Error al guardar consulta: " + e.getMessage());
        }
    }
    public void cargar(String rutaArchivo) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            Consulta temp = gson.fromJson(reader, Consulta.class);
            this.ci = temp.ci;
            this.nombrePaciente = temp.nombrePaciente;
            this.apellidoPaciente = temp.apellidoPaciente;
            this.idMed = temp.idMed;
            this.dia = temp.dia;
            this.mes = temp.mes;
            this.anio = temp.anio;
        } catch (IOException e) {
            System.out.println("Error al cargar consulta: " + e.getMessage());
        }
    }
    public void baja(String rutaCarpeta) {
        String ruta = rutaCarpeta + "/consulta_" + ci + ".json";
        File archivo = new File(ruta);
        if (archivo.delete()) {
            System.out.println("Consulta " + ci + " eliminada");
        }
    }
    public int getCi() { return ci; }
    public void setCi(int ci) { this.ci = ci; }
    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }
    public String getApellidoPaciente() { return apellidoPaciente; }
    public void setApellidoPaciente(String apellidoPaciente) { this.apellidoPaciente = apellidoPaciente; }
    public int getIdMed() { return idMed; }
    public void setIdMed(int idMed) { this.idMed = idMed; }
    public int getDia() { return dia; }
    public void setDia(int dia) { this.dia = dia; }
    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    @Override
    public String toString() {
        return "Consulta #" + ci + ": " + nombrePaciente + " " + apellidoPaciente +
                " | Médico ID: " + idMed + " | Fecha: " + dia + "/" + mes + "/" + anio;
    }
}





























/*package sistemaMedico;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// ==================== CLASE CONSULTA ====================
class Consulta {
    private int ci;
    private String nombrePaciente;
    private String apellidoPaciente;
    private int idMed;
    private int dia;
    private String mes;
    private int anio;

    // Constructor completo
    public Consulta(int ci, String nombrePaciente, String apellidoPaciente,
                   int idMed, int dia, String mes, int anio) {
        this.ci = ci;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.idMed = idMed;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    // Constructor vacío
    public Consulta() {
        this.ci = -1;
        this.nombrePaciente = "";
        this.apellidoPaciente = "";
        this.idMed = -1;
        this.dia = -1;
        this.mes = "";
        this.anio = -1;
    }

    // ---------- MÉTODOS DE ARCHIVO ----------
    public void alta(String rutaCarpeta) {
        String ruta = rutaCarpeta + "/consulta_" + ci + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(this, writer);
            System.out.println("✅ Consulta " + ci + " registrada para " + nombrePaciente);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar consulta: " + e.getMessage());
        }
    }

    public void cargar(String rutaArchivo) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            Consulta temp = gson.fromJson(reader, Consulta.class);
            this.ci = temp.ci;
            this.nombrePaciente = temp.nombrePaciente;
            this.apellidoPaciente = temp.apellidoPaciente;
            this.idMed = temp.idMed;
            this.dia = temp.dia;
            this.mes = temp.mes;
            this.anio = temp.anio;
        } catch (IOException e) {
            System.out.println("❌ Error al cargar consulta: " + e.getMessage());
        }
    }

    public void baja(String rutaCarpeta) {
        String ruta = rutaCarpeta + "/consulta_" + ci + ".json";
        File archivo = new File(ruta);
        if (archivo.exists() && archivo.delete()) {
            System.out.println("🗑️ Consulta " + ci + " eliminada");
        }
    }

    // ---------- GETTERS Y SETTERS ----------
    public int getCi() { return ci; }
    public void setCi(int ci) { this.ci = ci; }
    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }
    public String getApellidoPaciente() { return apellidoPaciente; }
    public void setApellidoPaciente(String apellidoPaciente) { this.apellidoPaciente = apellidoPaciente; }
    public int getIdMed() { return idMed; }
    public void setIdMed(int idMed) { this.idMed = idMed; }
    public int getDia() { return dia; }
    public void setDia(int dia) { this.dia = dia; }
    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    @Override
    public String toString() {
        return String.format("Cita #%d | Paciente: %s %s | Médico ID: %d | Fecha: %d/%s/%d",
                ci, nombrePaciente, apellidoPaciente, idMed, dia, mes, anio);
    }
}

// ==================== CLASE MÉDICO ====================
class Medico {
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

    // ---------- MÉTODOS DE ARCHIVO ----------
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
            System.out.println("✅ Médico " + nombreMed + " " + apellidoMed + " registrado");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar médico: " + e.getMessage());
        }
    }

    public void cargar(String rutaArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            this.idMed = Integer.parseInt(reader.readLine());
            this.nombreMed = reader.readLine();
            this.apellidoMed = reader.readLine();
            this.aniosExperiencia = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("❌ Error al cargar médico: " + e.getMessage());
        }
    }

    public void baja(String rutaCarpeta) {
        String ruta = rutaCarpeta + "/medico_" + idMed + ".txt";
        File archivo = new File(ruta);
        if (archivo.exists() && archivo.delete()) {
            System.out.println("🗑️ Médico " + nombreMed + " " + apellidoMed + " eliminado");
        }
    }

    // ---------- GETTERS Y SETTERS ----------
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
        return String.format("Médico #%d: Dr. %s %s | Experiencia: %d años",
                idMed, nombreMed, apellidoMed, aniosExperiencia);
    }
}

// ==================== CLASE CONSULTORIO ====================
class Consultorio {
    private String consultas;  // Ruta de carpeta de consultas
    private String medicos;    // Ruta de carpeta de médicos

    public Consultorio(String consultas, String medicos) {
        this.consultas = consultas;
        this.medicos = medicos;
        // Crear carpetas si no existen
        new File(consultas).mkdirs();
        new File(medicos).mkdirs();
    }

    // ============ MÉTODOS AUXILIARES ============
    private ArrayList<Medico> cargarTodosMedicos() {
        ArrayList<Medico> lista = new ArrayList<>();
        File carpeta = new File(medicos);
        File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".txt"));

        if (archivos != null) {
            for (File archivo : archivos) {
                Medico medico = new Medico();
                medico.cargar(archivo.getAbsolutePath());
                lista.add(medico);
            }
        }
        return lista;
    }

    private ArrayList<Consulta> cargarTodasConsultas() {
        ArrayList<Consulta> lista = new ArrayList<>();
        File carpeta = new File(consultas);
        File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".json"));

        if (archivos != null) {
            for (File archivo : archivos) {
                Consulta consulta = new Consulta();
                consulta.cargar(archivo.getAbsolutePath());
                lista.add(consulta);
            }
        }
        return lista;
    }

    // ============ a) ALTA DE 3 MÉDICOS Y 9 CONSULTAS ============
    public void ejercicioA() {
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("a) DAR DE ALTA 3 MÉDICOS Y 9 CONSULTAS");
        System.out.println("════════════════════════════════════════════");

        // Crear 3 médicos
        Medico[] medicosArray = {
            new Medico(1, "Ana", "García", 12),
            new Medico(2, "Luis", "Martínez", 8),
            new Medico(3, "Carmen", "Rodríguez", 20)
        };

        for (Medico medico : medicosArray) {
            medico.alta(this.medicos);
        }

        // Crear 9 consultas (3 por cada médico)
        int contadorConsulta = 100;

        // Consultas para médico 1 (Ana García)
        new Consulta(contadorConsulta++, "Juan", "Pérez", 1, 24, "Diciembre", 2024).alta(consultas);
        new Consulta(contadorConsulta++, "María", "López", 1, 15, "Enero", 2025).alta(consultas);
        new Consulta(contadorConsulta++, "Carlos", "Sánchez", 1, 25, "Diciembre", 2024).alta(consultas); // Navidad

        // Consultas para médico 2 (Luis Martínez)
        new Consulta(contadorConsulta++, "Sofía", "Fernández", 2, 1, "Enero", 2025).alta(consultas); // Año nuevo
        new Consulta(contadorConsulta++, "Pedro", "Gómez", 2, 10, "Febrero", 2025).alta(consultas);
        new Consulta(contadorConsulta++, "Laura", "Díaz", 2, 31, "Diciembre", 2024).alta(consultas);

        // Consultas para médico 3 (Carmen Rodríguez)
        new Consulta(contadorConsulta++, "Miguel", "Ruiz", 3, 25, "Diciembre", 2024).alta(consultas); // Navidad
        new Consulta(contadorConsulta++, "Elena", "Torres", 3, 1, "Enero", 2025).alta(consultas); // Año nuevo
        new Consulta(contadorConsulta++, "Jorge", "Hernández", 3, 14, "Marzo", 2025).alta(consultas);

        System.out.println("✅ Se crearon 3 médicos y 9 consultas exitosamente");
    }

    // ============ b) BAJA DE MÉDICO POR NOMBRE Y APELLIDO ============
    public void ejercicioB(String nombreX, String apellidoY) {
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("b) DAR DE BAJA AL MÉDICO: " + nombreX + " " + apellidoY);
        System.out.println("════════════════════════════════════════════");

        ArrayList<Medico> medicosLista = cargarTodosMedicos();
        int idMedicoAEliminar = -1;
        boolean encontrado = false;

        // Buscar médico por nombre y apellido
        for (Medico medico : medicosLista) {
            if (medico.getNombreMed().equalsIgnoreCase(nombreX) &&
                medico.getApellidoMed().equalsIgnoreCase(apellidoY)) {
                idMedicoAEliminar = medico.getIdMed();
                medico.baja(this.medicos);
                encontrado = true;
                System.out.println("⚠️  Médico encontrado: " + medico);
                break;
            }
        }

        if (!encontrado) {
            System.out.println("❌ No se encontró al médico " + nombreX + " " + apellidoY);
            return;
        }

        // Eliminar consultas asociadas a este médico
        ArrayList<Consulta> consultasLista = cargarTodasConsultas();
        int consultasEliminadas = 0;

        for (Consulta consulta : consultasLista) {
            if (consulta.getIdMed() == idMedicoAEliminar) {
                consulta.baja(this.consultas);
                consultasEliminadas++;
            }
        }

        System.out.println("🗑️  Se eliminaron " + consultasEliminadas + " consultas asociadas");
    }

    // ============ c) CAMBIAR DÍA DE CONSULTAS EN NAVIDAD O AÑO NUEVO ============
    public void ejercicioC() {
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("c) CAMBIAR DÍA DE CONSULTAS (NAVIDAD/AÑO NUEVO)");
        System.out.println("════════════════════════════════════════════");

        ArrayList<Consulta> consultasLista = cargarTodasConsultas();
        int cambios = 0;

        for (Consulta consulta : consultasLista) {
            boolean esNavidad = consulta.getDia() == 25 && consulta.getMes().equalsIgnoreCase("Diciembre");
            boolean esAnoNuevo = consulta.getDia() == 1 && consulta.getMes().equalsIgnoreCase("Enero");

            if (esNavidad || esAnoNuevo) {
                // Guardar datos originales
                int ciOriginal = consulta.getCi();
                int diaOriginal = consulta.getDia();

                // Cambiar a 2 días después (evitar festivos)
                consulta.setDia(consulta.getDia() + 2);

                // Eliminar archivo original
                String rutaOriginal = consultas + "/consulta_" + ciOriginal + ".json";
                new File(rutaOriginal).delete();

                // Crear nuevo archivo con fecha modificada
                consulta.alta(this.consultas);

                cambios++;
                System.out.println("↪️  Cita #" + ciOriginal + " reprogramada: " +
                                 diaOriginal + " → " + consulta.getDia() + " " + consulta.getMes());
            }
        }

        if (cambios == 0) {
            System.out.println("ℹ️  No hay consultas en Navidad o Año Nuevo");
        } else {
            System.out.println("✅ Se reprogramaron " + cambios + " consultas");
        }
    }

    // ============ d) OPCIONAL: PACIENTES EN DÍA DE CUMPLEAÑOS ============
    public void ejercicioD(int diaCumple, String mesCumple) {
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("d) PACIENTES ATENDIDOS EN MI CUMPLEAÑOS (" +
                          diaCumple + " de " + mesCumple + ")");
        System.out.println("════════════════════════════════════════════");

        ArrayList<Consulta> consultasLista = cargarTodasConsultas();
        boolean hayPacientes = false;

        for (Consulta consulta : consultasLista) {
            if (consulta.getDia() == diaCumple &&
                consulta.getMes().equalsIgnoreCase(mesCumple)) {

                // Buscar nombre del médico
                String nombreMedico = "Desconocido";
                for (Medico medico : cargarTodosMedicos()) {
                    if (medico.getIdMed() == consulta.getIdMed()) {
                        nombreMedico = medico.getNombreMed() + " " + medico.getApellidoMed();
                        break;
                    }
                }

                System.out.println("🎂 Paciente: " + consulta.getNombrePaciente() + " " +
                                 consulta.getApellidoPaciente() + " | Médico: " + nombreMedico);
                hayPacientes = true;
            }
        }

        if (!hayPacientes) {
            System.out.println("😊 No hay pacientes atendidos en tu cumpleaños");
        }
    }

    // ============ MOSTRAR ESTADO ACTUAL ============
    public void mostrarEstado() {
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("ESTADO ACTUAL DEL CONSULTORIO");
        System.out.println("════════════════════════════════════════════");

        System.out.println("\n👨‍⚕️  MÉDICOS REGISTRADOS:");
        ArrayList<Medico> medicosLista = cargarTodosMedicos();
        if (medicosLista.isEmpty()) {
            System.out.println("   No hay médicos registrados");
        } else {
            for (Medico medico : medicosLista) {
                System.out.println("   • " + medico);
            }
        }

        System.out.println("\n📅 CONSULTAS REGISTRADAS:");
        ArrayList<Consulta> consultasLista = cargarTodasConsultas();
        if (consultasLista.isEmpty()) {
            System.out.println("   No hay consultas registradas");
        } else {
            for (Consulta consulta : consultasLista) {
                System.out.println("   • " + consulta);
            }
        }

        System.out.println("\n📊 ESTADÍSTICAS:");
        System.out.println("   Total médicos: " + medicosLista.size());
        System.out.println("   Total consultas: " + consultasLista.size());
    }
}

// ==================== MAIN ====================
public class MainSistemaMedicoCompleto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear sistema
        Consultorio consultorio = new Consultorio("consultas", "medicos");

        System.out.println("🏥 SISTEMA DE GESTIÓN MÉDICA");
        System.out.println("==============================");

        // Ejecutar todos los ejercicios
        consultorio.ejercicioA();  // Alta de médicos y consultas

        consultorio.mostrarEstado();

        // Ejercicio B: Eliminar médico "Luis Martínez"
        consultorio.ejercicioB("Luis", "Martínez");

        consultorio.mostrarEstado();

        // Ejercicio C: Cambiar consultas en fechas festivas
        consultorio.ejercicioC();

        consultorio.mostrarEstado();

        // Ejercicio D: Pacientes en cumpleaños (ejemplo: 15 de Marzo)
        System.out.print("\n¿Quieres ver pacientes en tu cumpleaños? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Día de tu cumpleaños (número): ");
            int dia = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            System.out.print("Mes de tu cumpleaños (ej: Enero): ");
            String mes = scanner.nextLine();

            consultorio.ejercicioD(dia, mes);
        }

        scanner.close();
        System.out.println("\n✨ Programa ejecutado exitosamente");
    }
}*/
